package com.lion.plugin.sdkplugin.sdk.uitils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * 设备信息工具类
 */
public class DeviceUtil {

    /**
     * 获得应用类型 android, ios
     *
     * @return android
     */
    public static String getDeviceType() {
        return "android";
    }


    /**
     * 获得国际移动设备身份码
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        if (context == null) {
            return "";
        } else {
            if (ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            if (((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId().isEmpty()) {
                return "";
            } else {
                return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            }
        }
    }

    /**
     * 获得国际移动用户识别码
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        if (ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
    }

    /**
     * 获得设备屏幕矩形区域范围
     *
     * @param context
     * @return
     */
    public static Rect getScreenRect(Context context) {
        if (context == null) {
            return new Rect(0, 0, 0, 0);
        }
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        return new Rect(0, 0, w, h);
    }

    public static int getScreenHeight(Context context) {
        if (context == null) {
            return 800;
        }
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int h = display.getHeight();
        return h;
    }

    public static int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int w = display.getWidth();
        return w;
    }

    public static float getScreenRate(Context context) {
        return (getScreenHeight(context) + 0.00f) / (getScreenWidth(context) + 0.00f);

    }

    /**
     * 获得设备屏幕密度
     */
    public static float getScreenDensity(Context context) {
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        return metrics.density;
    }

    public static int getScreenDensityDpi(Context context) {
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        return (int) (metrics.density * 160);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param spValue
     * @return sp转为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String getIpLongString() {
        return ipToLong(getLocalIpAddress());
    }

    //将ip转换成long类型
    public static String ipToLong(String ipAddress) {
        //将目标IP地址字符串strIPAddress转换为数字
        String[] arrayIP = ipAddress.split("\\.");
        int sip1 = Integer.valueOf(arrayIP[0]);
        int sip2 = Integer.valueOf(arrayIP[1]);
        int sip3 = Integer.valueOf(arrayIP[2]);
        int sip4 = Integer.valueOf(arrayIP[3]);

        long r1 = sip1 * 256 * 256 * 256;
        long r2 = sip2 * 256 * 256;
        long r3 = sip3 * 256;
        long r4 = sip4;

        long result = r1 + r2 + r3 + r4;
        return String.valueOf(result);
    }


    public static String getLocalIpAddress() {
        String hostIp = "";
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;
    }


    //获取本机手机号：暂时没有办法获取两个卡号
    public static String getPhoneNumber() {
        TelephonyManager tm = (TelephonyManager) PluginPlatform.getInstance().getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), "android.permission.READ_PHONE_NUMBERS") != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        String tel = String.valueOf(tm.getLine1Number());//手机号码
        if (tel.startsWith("+86"))
            tel = tel.substring(3);
        return "null".equals(tel) ? "" : tel;
    }


    public static String getProvidersName() {
        String ProvidersName = null;
        // 返回唯一的用户ID;就是这张卡的编号神马的
        TelephonyManager tm = (TelephonyManager) PluginPlatform.getInstance().getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        String IMSI = String.valueOf(tm.getSubscriberId());
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        System.out.println(IMSI);
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            ProvidersName = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            ProvidersName = "中国联通";
        } else if (IMSI.startsWith("46003")) {
            ProvidersName = "中国电信";
        } else {
            ProvidersName = "";
        }
        return ProvidersName;
    }

    //获取老的guid
    public static String getOldGuid() {
        String guid = SharedPreferencesUtil.getString(SPConstants.GM_DEVICE_UNIQUE_0LD_ID, null);
        if (guid == null) {
            final TelephonyManager tm = (TelephonyManager) PluginPlatform.getInstance().getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial, androidId;
            if (ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                guid = "";
            } else {
                tmDevice = "" + tm.getDeviceId();

                tmSerial = "" + tm.getSimSerialNumber();
                androidId = "" + android.provider.Settings.Secure.getString(PluginPlatform.getInstance().getActivity().getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
                UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
                guid = deviceUuid.toString();
            }
//            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32));
        }
        SharedPreferencesUtil.saveString(SPConstants.GM_DEVICE_UNIQUE_0LD_ID, guid);
        Log.e("GUID", guid);
        return guid;
    }


    //获取设备唯一id
    public static String getGuid() {
        String guid = SharedPreferencesUtil.getString(SPConstants.DEVICE_UNIQUE_ID, null);
        if (guid == null) {
            final TelephonyManager tm = (TelephonyManager) PluginPlatform.getInstance().getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial, androidId;
            if (ActivityCompat.checkSelfPermission(PluginPlatform.getInstance().getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                guid = "";
            }else {
                tmDevice = "" + tm.getDeviceId();
//            tmSerial = "" + tm.getSimSerialNumber();
                androidId = "" + android.provider.Settings.Secure.getString(PluginPlatform.getInstance().getActivity().getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);

//            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
                UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32));
                guid = deviceUuid.toString();
            }
        }
        SharedPreferencesUtil.saveString(SPConstants.DEVICE_UNIQUE_ID, guid);
        Log.e("GUID", guid);
        return guid;
    }


    public static String getDeviceModelName() {
        String modelName = "设备x";
        modelName = Build.MODEL;
        return modelName;
    }

    public static String getDeviceManufacturerName() {
        String manufacturer = "手机厂商x";
        manufacturer = Build.MANUFACTURER;
        return manufacturer;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName() {
        int versionName = 0;
        versionName = Build.VERSION.SDK_INT;
        return String.valueOf(versionName);
    }

    //根据IP获取本地Mac
    public static String getLocalMacAddressFromIp() {
        String mac_s= "";
        try {
            byte[] mac;
            NetworkInterface ne=NetworkInterface.getByInetAddress(InetAddress.getByName(getLocalIpAddress()));
            mac = ne.getHardwareAddress();
            mac_s = byte2hex(mac);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mac_s;
    }

    public static  String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer(b.length);
        String stmp = "";
        int len = b.length;
        for (int n = 0; n < len; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs.append("0").append(stmp);
            else {
                hs = hs.append(stmp);
            }
        }
        return String.valueOf(hs);
    }
}
