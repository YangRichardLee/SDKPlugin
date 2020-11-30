package com.lion.plugin.sdkplugin.sdk.uitils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.plugin.sdkplugin.R;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;
import com.lion.plugin.sdkplugin.sdk.manager.PesudoUniqueIDMananger;
import com.lion.plugin.sdkplugin.sdk.modle.CountTipsEntity;
import com.lion.plugin.sdkplugin.sdk.modle.CountTipsEntityHolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.path;


/**
 * 全局的帮助类
 * Created by Eleven on 2016/8/28.
 */
public class GlobalUtil {

    private static final String TAG = GlobalUtil.class.getName();

    private static String mPesudoUniqueID="";

    public static void testParams(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return;
        }
        for (String key : params.keySet()) {
            ULogUtil.d(TAG, "key:" + key + "   vlaue:" + params.get(key));
        }
    }

    //获取当前的Token
    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }


    public static void shortToast(String text) {
        if (text == null){
            return;
        }
        Toast.makeText(PluginPlatform.getInstance().getActivity(),text,Toast.LENGTH_LONG).show();
    }

    public static final int NET_TYPE_NO = 0; //无网络
    public static final int NET_TYPE_WIFI = 1; //wifi网络
    public static final int NET_TYPE_MOBILE = 2; //手机网络

    /**
     * 获取本机的mac地址
     *
     * @param context
     * @return
     */
    public static String getLocalMacAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                if (wifiInfo != null) {
                    String mac = wifiInfo.getMacAddress();
                    return mac;
                }
            }
            return "";
        } catch (Exception ex) {
            return " 获取本机mac地址出错,请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
    }


    /**
     * 获得国际移动设备身份码
     *
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getIMEI(Context context) {
        return String.valueOf(System.currentTimeMillis());
//        if (!SharedPreferencesUtil.getString(SPConstants.GM_USER_ONLYID_PESUDOUNIQUEID, "").isEmpty()) {
//            return SharedPreferencesUtil.getString(SPConstants.GM_USER_ONLYID_PESUDOUNIQUEID, "");
//        }
//        if (!PesudoUniqueIDMananger.getInstance().getPesudoUniqueID().isEmpty()) {
//            return PesudoUniqueIDMananger.getInstance().getPesudoUniqueID();
//        }
//        if (android.os.Build.VERSION.SDK_INT >= 29) {
//            if (!android.provider.Settings.Secure.getString(Platform.getInstance().getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID).isEmpty()
//                    && !android.provider.Settings.Secure.getString(Platform.getInstance().getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID).equals("null")) {
//                return android.provider.Settings.Secure.getString(Platform.getInstance().getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//            }
//        } else {
//            try {
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//                    return String.valueOf(((TelephonyManager) (context.getSystemService(Context.TELEPHONY_SERVICE))).getDeviceId());
//                }
//            } catch (Exception e) {
//                if (!android.provider.Settings.Secure.getString(Platform.getInstance().getActivity().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID).isEmpty()
//                        && !android.provider.Settings.Secure.getString(Platform.getInstance().getActivity().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID).equals("null")) {
//                    return android.provider.Settings.Secure.getString(Platform.getInstance().getActivity().getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
//                }else {
//                    return getUniquePsuedoID();
//                }
//            }
//        }
//        return getUniquePsuedoID();
    }

    public static boolean isChineseName(String name) {
        final String regEx = "^([\u4e00-\u9fa5]{2,10}+$)";
        final Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(name);
        return matcher.find();
    }

    //获得独一无二的Psuedo ID
    @SuppressWarnings("TryWithIdenticalCatches")
    public static String getUniquePsuedoID() {
        if (!mPesudoUniqueID.isEmpty()) {
            return mPesudoUniqueID;
        } else {
            if (!SharedPreferencesUtil.getString(SPConstants.GM_USER_ONLYID_PESUDOUNIQUEID, "").isEmpty()) {
                return SharedPreferencesUtil.getString(SPConstants.GM_USER_ONLYID_PESUDOUNIQUEID, "");
            }
            if (!PesudoUniqueIDMananger.getInstance().getPesudoUniqueID().isEmpty()) {
                return PesudoUniqueIDMananger.getInstance().getPesudoUniqueID();
            }
            String serial = null;
            String m_szDevIDShort = "35" +
                    android.os.Build.BOARD.length() % 10 + android.os.Build.BRAND.length() % 10 +
                    android.os.Build.CPU_ABI.length() % 10 + android.os.Build.DEVICE.length() % 10 +
                    android.os.Build.DISPLAY.length() % 10 + android.os.Build.HOST.length() % 10 +
                    android.os.Build.ID.length() % 10 + android.os.Build.MANUFACTURER.length() % 10 +
                    android.os.Build.MODEL.length() % 10 + android.os.Build.PRODUCT.length() % 10 +
                    android.os.Build.TAGS.length() % 10 + android.os.Build.TYPE.length() % 10 +
                    android.os.Build.USER.length() % 10; //13 位
            try {
                serial = android.os.Build.class.getField("SERIAL").get(null).toString();
                mPesudoUniqueID = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
                saveUniquePsuedoID(new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString());
                return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
            } catch (Exception exception) {
                //serial需要一个初始化
                serial = id(PluginPlatform.getInstance().getActivity()); // 随便一个初始化
            }
            //使用硬件信息拼凑出来的15位号码
            mPesudoUniqueID = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
            saveUniquePsuedoID(new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString());
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        }
    }

    private static void saveUniquePsuedoID(String mPesudoUniqueID) {
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_ONLYID_PESUDOUNIQUEID, mPesudoUniqueID);
        PesudoUniqueIDMananger.getInstance().savePesudoUniqueID(mPesudoUniqueID);
    }


    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION";

    public synchronized static String id(Context context) {
        if (sID == null) {
            try {
                File installation = new File(context.getFilesDir(), INSTALLATION);
                if (!installation.exists())
                    writeInstallationFile(installation);
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                sID = "INSTALLATION";
            }
        }
        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();

    }


    /**
     * 判断身份证格式
     *
     * @param idNum
     * @return
     */
    public static boolean isIdNum(String idNum) {

        // 中国公民身份证格式：长度为15或18位，最后一位可以为字母,且只能为答谢的X
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9X])|(\\d{17}[0-9X])");

        // 格式验证
        if (!idNumPattern.matcher(idNum).matches())
            return false;

        // 合法性验证

        int year = 0;
        int month = 0;
        int day = 0;

        if (idNum.length() == 15) {

            // 一代身份证

            System.out.println("一代身份证：" + idNum);

            // 提取身份证上的前6位以及出生年月日
            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{2})(\\d{2})(\\d{2}).*");

            Matcher birthDateMather = birthDatePattern.matcher(idNum);

            if (birthDateMather.find()) {

                year = Integer.valueOf("19" + birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));

            }

        } else if (idNum.length() == 18) {

            // 二代身份证

            System.out.println("二代身份证：" + idNum);

            // 提取身份证上的前6位以及出生年月日
            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");

            Matcher birthDateMather = birthDatePattern.matcher(idNum);

            if (birthDateMather.find()) {

                year = Integer.valueOf(birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));
            }

        }

        // 年份判断，100年前至今

        Calendar cal = Calendar.getInstance();

        // 当前年份
        int currentYear = cal.get(Calendar.YEAR);

        if (year <= currentYear - 100 || year > currentYear)
            return false;

        // 月份判断
        if (month < 1 || month > 12)
            return false;

        // 日期判断

        // 计算月份天数

        int dayCount = 31;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dayCount = 31;
                break;
            case 2:
                // 2月份判断是否为闰年
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    dayCount = 29;
                    break;
                } else {
                    dayCount = 28;
                    break;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                dayCount = 30;
                break;
        }

        System.out.println(String.format("生日：%d年%d月%d日", year, month, day));

        System.out.println(month + "月份有：" + dayCount + "天");

        if (day < 1 || day > dayCount)
            return false;

        return true;
    }


    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 保存图片到系统相册
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        saveImageToGallery(context, bmp, "怪猫游戏", "怪猫游戏公众号");
    }


    /**
     * 保存图片到系统相册
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp, String title, String desc) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "guaimaopic");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, title);
            values.put(MediaStore.Images.Media.DESCRIPTION, desc);
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis()); // DATE HERE
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());


            context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        GlobalUtil.shortToast("保存成功~");
    }


    //检查某个app是否已安装
    public static boolean hasApplication(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

        //获取系统中安装的应用包的信息
        List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < listPackageInfo.size(); i++) {
            if (listPackageInfo.get(i).packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;

    }


    //检查是否有安装怪猫app
    public static boolean hasInstallGMApp() {
        return hasApplication(PluginPlatform.getInstance().getActivity(), "com.gm88.game");
    }

    //下载apk文件
    public static void goToDownLoadApk(Context context, String downloadUrl) {
//        String downloadUrl = url;
        downloadApkAndInstall(context, downloadUrl);
        GlobalUtil.shortToast("下载中~下载完成之后将会自动打开进行安装~");
    }

    //下载apk文件
    public static void goToDownLoadGMApk() {
        GlobalUtil.shortToast("暂无游戏地址");
//        downloadApkAndInstall(Platform.getInstance().getActivity(), "https://url.gm88.com/cQJF8");
    }

    private static void downloadApkAndInstall(Context context, String url) {
//        DownloadManagerHelper.getInstance().init(context).startDownLoad(url);
    }

    public static void saveReadActivityId(int id) {
        String hasReadActivityIds = SharedPreferencesUtil.getString(SPConstants.GM_HAS_READ_ACTIVITY_LIST, "");
        List<String> ids = new ArrayList<>();
        if (hasReadActivityIds.contains(",")) {
            ids = Arrays.asList(hasReadActivityIds.split(","));
        }
        for (String s : ids) {
            if (s.equals(String.valueOf(id)))
                return;
        }
        hasReadActivityIds += id + ",";
        SharedPreferencesUtil.saveString(SPConstants.GM_HAS_READ_ACTIVITY_LIST, hasReadActivityIds);
    }

    //保存登录的用户的uid
    public static void saveLoginUserId(String uid) {
        String hasReadActivityIds = SharedPreferencesUtil.getString(SPConstants.GM_LOGIN_UID_GUAIMAO, "");
        List<String> ids = new ArrayList<>();
        if (hasReadActivityIds.contains(",")) {
            ids = Arrays.asList(hasReadActivityIds.split(","));
        }
        for (String s : ids) {
            if (s.equals(String.valueOf(uid)))
                return;
        }
        hasReadActivityIds += uid + ",";
        SharedPreferencesUtil.saveString(SPConstants.GM_LOGIN_UID_GUAIMAO, hasReadActivityIds);
        SharedPreferencesUtil.saveLong(SPConstants.GM_USER_CENTER_DISPLAY_TIME, 0);
    }

    //保存登录的用户的uid
    public static void saveUnreadActivityId(String activityId) {
        String hasReadActivityIds = SharedPreferencesUtil.getString(SPConstants.GM_USER_UNREAD_ACTIVITY_IDS, "");
        List<String> ids = new ArrayList<>();
        if (hasReadActivityIds.contains(",")) {
            ids = Arrays.asList(hasReadActivityIds.split(","));
        }
        for (String s : ids) {
            if (s.equals(String.valueOf(activityId)))
                return;
        }
        hasReadActivityIds += activityId + ",";
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_UNREAD_ACTIVITY_IDS, hasReadActivityIds);
    }

    //移除掉已阅读的活动
    public static void removeUnReadActivityId(String activityId) {
        String hasReadActivityIds = SharedPreferencesUtil.getString(SPConstants.GM_USER_UNREAD_ACTIVITY_IDS, "");
        if (!TextUtils.isEmpty(hasReadActivityIds)) {
            if (hasReadActivityIds.contains(activityId + ",")) {
                hasReadActivityIds = hasReadActivityIds.replace(activityId + ",", "");
            }
        }
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_UNREAD_ACTIVITY_IDS, hasReadActivityIds);
    }

    public static boolean shouldShowActivityDot() {
        String unReadActivityIds = SharedPreferencesUtil.getString(SPConstants.GM_USER_UNREAD_ACTIVITY_IDS, "");
        List<String> ids = new ArrayList<>();
        if (unReadActivityIds.contains(",")) {
            ids = Arrays.asList(unReadActivityIds.split(","));
        }
        int unReadCount = 0;
        for (String id : ids) {
            if (!TextUtils.isEmpty(id))
                unReadCount++;
        }
        if (unReadCount == 0) {
            SharedPreferencesUtil.saveString(SPConstants.GM_USER_UNREAD_ACTIVITY_IDS, "");
        }

        String hasReadActivityIds = SharedPreferencesUtil.getString(SPConstants.GM_HAS_READ_ACTIVITY_LIST, "");
        List<String> readIds = new ArrayList<>();
        if (hasReadActivityIds.contains(",")) {
            readIds = Arrays.asList(hasReadActivityIds.split(","));
        }
        int hasReadCount = 0;
        for (String id : readIds) {
            if (!TextUtils.isEmpty(id))
                hasReadCount++;
        }
        if (hasReadCount == 0) {
            SharedPreferencesUtil.saveString(SPConstants.GM_HAS_READ_ACTIVITY_LIST, "");
        }
        return unReadCount > 0 || hasReadCount == 0;
    }

    //检查该用户之前是否登录过
    public static boolean checkUidHasLoginBefore(String uid) {
        String hasReadActivityIds = SharedPreferencesUtil.getString(SPConstants.GM_LOGIN_UID_GUAIMAO, "");
        List<String> ids = new ArrayList<>();
        if (hasReadActivityIds.contains(",")) {
            ids = Arrays.asList(hasReadActivityIds.split(","));
        }
        for (String s : ids) {
            if (s.equals(String.valueOf(uid)))
                return true;
        }
        return false;
    }

    //跳转到App客服
    public static void goToAppOnlineCS(Context context) {
        goToAppSpecificViewNew(context, 0, "", 0, "", "com.gm88.game.ui.user.ServiceActivity");
    }

    //跳转到优惠券界面
    public static void goToAppCouponActivity(Context context) {
        goToAppSpecificViewNew(context, 0, "", 0, "", "com.gm88.game.ui.coupon.CouponMineActivity");
    }

    //游戏详情礼包
    public static void goToAppGameInfoActivity(Context context, String giftId) {
//        goToAppSpecificView(context, 0, giftId, 2, "", "com.gm88.game.ui.gameinfo.GameInfoActivity");
        goToAppSpecificViewNew(context, 0, giftId, 2, "", "com.gm88.game.ui.gameinfo.GameInfoActivity");
    }


    //跳转到vip礼包界面   0:周礼包，3:升级礼包
    public static void goToAppVipWeekGift(Context context) {
        goToAppGiftListView(context, 0, "");
    }

    //跳转到vip礼包界面   0:周礼包，3:升级礼包
    public static void goToAppVipUpdateGift(Context context, String vipLevel) {
        goToAppGiftListView(context, 3, vipLevel);
    }

    private static void goToAppGiftListView(Context context, int type, String vipLevel) {
//        goToAppSpecificView(context, type, "", 0, vipLevel, "com.gm88.game.ui.user.UserVipActivity");
        goToAppSpecificViewNew(context, type, "", 0, vipLevel, "com.gm88.game.ui.user.UserVipActivity");
    }

    private static void goToAppSpecificView(Context context, int type, String giftId, int tabIdnex, String vipLevel, String target) {
        Intent intent;
        if (isGMServiceOpen()) {
            intent = new Intent("GMSDKTOGMAPP");
        } else
            intent = PluginPlatform.getInstance().getActivity().getPackageManager()
                    .getLaunchIntentForPackage("com.gm88.game");
        String extString = "this is ext string";
        if (!TextUtils.isEmpty(giftId))
            extString = "{" + "\"gift_id\":\"" + giftId + "\"}";
        intent.putExtra("fromSDK", "sdk");
//        intent.putExtra("token", UserModel.getInstance().getToken());
//        intent.putExtra("user_name", UserModel.getInstance().getUser().getNickName());
//        intent.putExtra("uid", UserModel.getInstance().getUser().getUid());
        intent.putExtra("ext", extString);
        intent.putExtra("open_gift_type", type);
        intent.putExtra("gameId", Config.getGameId());
        intent.putExtra("tabIdnex", tabIdnex);
        intent.putExtra("back_package", Config.getPackageName());
        intent.putExtra("back_gameid", Config.getGameId());
        intent.putExtra("open_gift_viplevel", vipLevel);
        intent.putExtra("action", target);

        goToAppWithIntent(intent);
    }


    private static void goToAppSpecificViewNew(Context context, int type, String giftId, int tabIdnex, String vipLevel, String target) {
        Intent intent;
        intent = PluginPlatform.getInstance().getActivity().getPackageManager()
                .getLaunchIntentForPackage("com.gm88.game");
        String extString = "this is ext string";
        if (!TextUtils.isEmpty(giftId))
            extString = "{" + "\"gift_id\":\"" + giftId + "\"}";
        intent.putExtra("fromSDK", "sdk");
//        intent.putExtra("token", UserModel.getInstance().getToken());
//        intent.putExtra("user_name", UserModel.getInstance().getUser().getNickName());
//        intent.putExtra("uid", UserModel.getInstance().getUser().getUid());
        intent.putExtra("ext", extString);
        intent.putExtra("open_gift_type", type);
        intent.putExtra("gameId", Config.getGameId());
        intent.putExtra("tabIdnex", tabIdnex);
        intent.putExtra("back_package", Config.getPackageName());
        intent.putExtra("back_gameid", Config.getGameId());
        intent.putExtra("open_gift_viplevel", vipLevel);
        intent.putExtra("action", target);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void goToAppWithIntent(Intent intent) {
        if (isGMServiceOpen()) {
            PluginPlatform.getInstance().getActivity().sendBroadcast(intent);
        } else {
            PluginPlatform.getInstance().getActivity().startActivity(intent);
        }
    }


    public static String accuracy(double num, double total, int scale) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = num / total * 100;
        return df.format(accuracy_num);
    }

    public static boolean hasWX() {
        final PackageManager packageManager = PluginPlatform.getInstance().getActivity().getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * shot the current screen ,with the status but the status is trans *
     *
     * @param ctx current activity
     */
    public static Bitmap shotActivity(Activity ctx) {

        View view = ctx.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bp = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(),
                view.getMeasuredHeight());

        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();

        return bp;
    }


    private static boolean isGMServiceOpen() {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) PluginPlatform.getInstance().getActivity()
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(100);

        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals("com.gm88.game.GMService") == true) {
                isRunning = true;
            }
        }
        return isRunning;
    }

    public static boolean shouldShowVipTips() {
        return Integer.valueOf(SharedPreferencesUtil.getString(SPConstants.GM_WAIT_TO_GET_UPGRADE_VIP_GIFT, "0")) > 0
                || Integer.valueOf(SharedPreferencesUtil.getString(SPConstants.GM_WAIT_TO_GET_VIP_GIFT, "0")) > 0
                || Integer.valueOf(SharedPreferencesUtil.getString(SPConstants.GM_WAIT_TO_GET_WEEK_VIP_GIFT, "0")) > 0;
    }

    public static boolean shouldShowFloatIconCouponSendTips() {
        return SharedPreferencesUtil.getBoolean(SPConstants.GM_USER_HAS_NEW_PLATFORM_GIFT_OR_COUPON, false);
    }

    public static boolean shouldShowFloatCouponTips() {
        return Integer.valueOf(SharedPreferencesUtil.getString(SPConstants.GM_HAS_WAIT_TO_GET_COUPON, "0")) > 0;
    }

    public static boolean shouldShowFloatMsgTips() {
        return SharedPreferencesUtil.getBoolean(SPConstants.GM_USER_MESSAGE_HAS_UPDATE, false);
    }


    public static boolean shouldShowFloatKfTips() {
        return SharedPreferencesUtil.getBoolean(SPConstants.GM_USER_KF_HAS_UPDATE, false);
    }

    public static boolean shouldShowAccountDot() {
        return Integer.valueOf(SharedPreferencesUtil.getString(SPConstants.GM_WAIT_TO_GET_VIP_GIFT, "0")) > 0
                || shouldShowFloatCouponTips();
    }

    public static void clearFloatDots() {
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_PLATFORM_GIFT_OR_COUPON, false);
        SharedPreferencesUtil.saveString(SPConstants.GM_HAS_WAIT_TO_GET_COUPON, "0");
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_MESSAGE_HAS_UPDATE, false);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_KF_HAS_UPDATE, false);
    }

    public static void saveAllItemCounts(CountTipsEntity entity) {
        if (entity != null) {
            CountTipsEntityHolder.setEntity(entity);
            SharedPreferencesUtil.saveString(SPConstants.GM_INVALID_COUPON_COUNT, entity.invalid_coupon_count);
            SharedPreferencesUtil.saveString(SPConstants.GM_HAS_WAIT_TO_GET_COUPON, entity.wait_to_get_coupon);
            SharedPreferencesUtil.saveString(SPConstants.GM_WAIT_TO_GET_VIP_GIFT, entity.wait_to_get_vip_gift);
            SharedPreferencesUtil.saveString(SPConstants.GM_WAIT_TO_GET_WEEK_VIP_GIFT, entity.wait_to_get_week_vip_gift);
            SharedPreferencesUtil.saveString(SPConstants.GM_WAIT_TO_GET_UPGRADE_VIP_GIFT, entity.wait_to_get_upgrade_vip_gift);
            SharedPreferencesUtil.saveString(SPConstants.GM_UNREAD_MSG_COUNT, entity.unread_msg_count);
            SharedPreferencesUtil.saveString(SPConstants.GM_AVALID_COUPON_COUNT, entity.avalid_coupon_count);
            SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_GIFT, entity.has_new_gift_box);
            SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_MESSAGE_HAS_UPDATE, entity.message_has_update);
            SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_GIFT_HAS_UPDATE, entity.gift_has_update);

            SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_ENTER_MESSAGE_LIST_FRAGMENT, !entity.message_has_update);
            SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_ENTER_GIFT_LIST_VIEW, !entity.gift_has_update);
        }
    }

    public static boolean inMainProcess(Context context) {
        String mainProcessName = context.getApplicationInfo().processName;
        String processName = getProcessName();
        return TextUtils.equals(mainProcessName, processName);
    }

    /**
     * 获取当前进程名
     */
    private static String getProcessName() {
        BufferedReader reader = null;
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            reader = new BufferedReader(new FileReader(file));
            return reader.readLine().trim();
        } catch (IOException e) {
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void PwdHide(EditText pwds, ImageView hides, ImageView shows){
        hides.setVisibility(View.VISIBLE);
        shows.setVisibility(View.INVISIBLE);

        //隐藏密码方法一
        PasswordTransformationMethod method1 = PasswordTransformationMethod.getInstance();
        pwds.setTransformationMethod(method1);
        //切换后将EditText光标置于末尾
        CharSequence charSequence = pwds.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
    public static void PwdShow(EditText pwds, ImageView hides, ImageView shows) {
        hides.setVisibility(View.INVISIBLE);
        shows.setVisibility(View.VISIBLE);


        //显示密码方法一
        HideReturnsTransformationMethod method2 = HideReturnsTransformationMethod.getInstance();
        pwds.setTransformationMethod(method2);
        // 切换后将EditText光标置于末尾
        CharSequence charSequence1 = pwds.getText();
        if (charSequence1 instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence1;
            Selection.setSelection(spanText, charSequence1.length());
        }
    }

}
