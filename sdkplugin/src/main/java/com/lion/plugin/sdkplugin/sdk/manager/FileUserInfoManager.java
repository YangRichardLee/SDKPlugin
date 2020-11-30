package com.lion.plugin.sdkplugin.sdk.manager;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;

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
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyang
 * @date 2017/12/13
 */

public class FileUserInfoManager {

    private static final String TAG = FileUserInfoManager.class.getName();

    private static FileUserInfoManager mInstance;
    private static Object object = new Object();

    private FileUserInfoManager() {
    }

    public static FileUserInfoManager getInstance() {
        if (mInstance == null) {
            synchronized (object) {
                if (mInstance == null) {
                    mInstance = new FileUserInfoManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 存储的最大用户数量
     */
    private static final int MAX_SAVE_USER = 10;
    /**
     * NOTE:暂时不会用到
     * 显示的最大用户数量
     */
    private static final int MAX_SHOW_USER = 10;
    /**
     * 用户信息存储文件
     */
    private static final String ACCOUNT_FILE_NAME = "/guaimao/account";
    /**
     * 用户信息存储文件路径
     */
    private String mFilePath;
    /**
     * 用于存储用户列表信息
     */
    private List<User> mAccoutList;

    /**
     * 获取最近登录的用户信息
     *
     * @return
     */
    public User getLastUser() {
        if (mAccoutList != null && mAccoutList.size() > 0) {
            return mAccoutList.get(0);
        }
        return null;
    }

    /**
     * 存储用户信息
     *
     * @param user
     */
    public void saveUser(User user) {
        if (getUserList() == null) {
            mAccoutList = new ArrayList<>();
        }

        int index = getUserIndex(user, mAccoutList);
        ULogUtil.d(TAG, "save user index:" + index);
        if (index == -1) {//用户不在用户列表中
            if (mAccoutList.size() > MAX_SAVE_USER) {
                mAccoutList.remove(MAX_SAVE_USER - 1);
            }
        } else {//当前用户在列表中，则删除老的对象
            mAccoutList.remove(index);
        }
        mAccoutList.add(0, user);
        saveUserListToFile(mAccoutList);
    }

    /**
     * 删除指定用户
     *
     * @param user
     */
    public void removeUser(User user) {
        mAccoutList.remove(user);
        saveUserListToFile(mAccoutList);
    }

    /**
     * 清空所有用户信息
     */
    public void clearUser() {
        mAccoutList = null;
        File file = new File(getFilePath());
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     *
     * @return
     */
    public boolean fileIsExists() {
        try {
            File f = new File(getSDCardPath() + ACCOUNT_FILE_NAME);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    public List<User> getUserList() {
        if (mAccoutList == null) {
            mAccoutList = getUserListFromFile();
        }
        if (mAccoutList == null) {
            mAccoutList = new ArrayList<>();
        }
        return mAccoutList;
    }

    /**
     * 获取指定用户在用户列表中的位置
     *
     * @param user
     * @param list
     * @return
     */
    private int getUserIndex(User user, List<User> list) {
        ULogUtil.d(TAG, "user nickName：" + user.nickname + "   user phone:" + user.phone);
        if (user == null || list == null) {
            ULogUtil.w(TAG, "[getUserIndex] user is null or list is null !!!");
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            ULogUtil.d(TAG, "list user,  nickName:" + list.get(i).getNickName() + "     phone:" + list.get(i).phone);
            if (!TextUtils.isEmpty(user.nickname) && user.nickname.equals(list.get(i).getNickName())) {
                return i;
            }
            if (!TextUtils.isEmpty(user.phone) && user.phone.equals(list.get(i).phone)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 从文件内读取用户列表
     *
     * @return
     */
    private List<User> getUserListFromFile() {
        File file = new File(getFilePath());
        if (file == null || !file.exists()) {
            ULogUtil.w(TAG, "file account is not exists !!!");
            return null;
        }

        String contentBase64 = null;
        BufferedReader reader = null;
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
            return null;
        }

        if (TextUtils.isEmpty(contentBase64)) {
            ULogUtil.w(TAG, "file account is emtpy !!!");
            return null;
        }
        List<User> list = null;
        try {
            byte[] base64 = Base64.decode(contentBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64);
            ObjectInputStream objs = new ObjectInputStream(bais);
            Object object = objs.readObject();
            if (object instanceof List) {
                list = (List<User>) object;
            } else {
                ULogUtil.w(TAG, "[getUserListFromFile] raed base64 can not convert to list !!!");
            }
        } catch (Exception e) {
            ULogUtil.e(TAG, "[getUserListFromFile] failed,", e);
        }

        ULogUtil.d(TAG, "[getUserListFromFile] user list size: " + (list == null ? "0" : list.size()));

        return list;
    }

    /**
     * 保存用户列表信息至文件
     *
     * @param list
     */
    private void saveUserListToFile(List<User> list) {
        if (list == null) {
            ULogUtil.w(TAG, "[saveUserListToFile]  user list is null !!!");
            return;
        }
        ULogUtil.d(TAG, "user list size:" + list.size());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(list);
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
            ULogUtil.e(TAG, "[saveUserListToFile] error,", e);
        }
    }

    /**
     * 创建文件存储文件，并存储文件路径
     */
    public String getFilePath() {
        if (TextUtils.isEmpty(mFilePath)) {
            mFilePath = getSDCardPath() + ACCOUNT_FILE_NAME;
        }
        return mFilePath;
    }

    //获取SD卡根目录
    public static String getSDCardPath() {
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
