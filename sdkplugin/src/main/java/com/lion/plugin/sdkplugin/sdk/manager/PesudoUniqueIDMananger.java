package com.lion.plugin.sdkplugin.sdk.manager;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PesudoUniqueIDMananger {

    private static final String TAG = PesudoUniqueIDMananger.class.getName();

    private static PesudoUniqueIDMananger mInstance;

    private PesudoUniqueIDMananger() {
    }

    public static PesudoUniqueIDMananger getInstance() {
        if (mInstance == null) {
            synchronized (PesudoUniqueIDMananger.class) {
                if (mInstance == null) {
                    mInstance = new PesudoUniqueIDMananger();
                }
            }
        }
        return mInstance;
    }

    private static final String ACCOUNT_FILE_NAME = SPConstants.GM_USER_ONLYID_PESUDOUNIQUEID_FILE;

    private String mFilePath;

    private String pesudouniqueID = "";

    public void savePesudoUniqueID(String id) {
        saveIdToFile(id);
    }

    public String getPesudoUniqueID(){
        if (pesudouniqueID.isEmpty()){
            pesudouniqueID = getIdFromFile();
        }
        return pesudouniqueID;

    }

    private String getIdFromFile() {
        File file = new File(getFilePath());
        String id = "";
        String contentBase64 = null;
        BufferedReader reader = null;

        if (!file.exists()) {
            Log.w(TAG, "file account is emtpy !!!");
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            if ((line = reader.readLine()) != null) {
                sb.append(line);
                while ((line = reader.readLine()) != null) {
                    sb.append(System.getProperty("line.separator")).append(line);
                }
            }
            reader.close();
            contentBase64 = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        if (TextUtils.isEmpty(contentBase64)) {
            Log.w(TAG, "file account is emtpy !!!");
            return "";
        }
        try {
            byte[] base64 = Base64.decode(contentBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64);
            ObjectInputStream objs = new ObjectInputStream(bais);
            Object object = objs.readObject();
            if (object instanceof String) {
                id = (String) object;
            } else {
                Log.w(TAG, "[getIdFromFile] read base64 can not convert to list !!!");
            }
        } catch (Exception e) {
            Log.e(TAG, "[getIdFromFile] failed,", e);
        }

        return id;
    }

    private void saveIdToFile(String id) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(id);
            String contentBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));

            File file = new File(getFilePath());
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            bw.write(contentBase64);
            bw.close();

        } catch (Exception e) {
            Log.e(TAG, "[saveIdToFile] error,", e);
        }
    }

    private String getFilePath() {
        if (TextUtils.isEmpty(mFilePath)) {
            mFilePath = getSDCardPath() + ACCOUNT_FILE_NAME;
        }
        return mFilePath;
    }

    //获取SD卡根目录
    private static String getSDCardPath() {
        boolean exist = isSDCardExist();
        if (exist) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "/sdcard/";
    }

    private static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
