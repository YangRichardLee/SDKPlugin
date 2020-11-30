package com.lion.plugin.sdkplugin.sdk.manager;

import android.util.Log;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.constants.LogEvents;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.modle.CheckResultEntity;
import com.lion.plugin.sdkplugin.sdk.modle.LoginTypeEntity;
import com.lion.plugin.sdkplugin.sdk.network.HttpProxy;
import com.lion.plugin.sdkplugin.sdk.precenter.UserModel;
import com.lion.plugin.sdkplugin.sdk.uitils.DeviceUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.GlobalUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录网络请求层
 * Created by eleven
 * on 2016/12/21 下午2:24.
 */

public class LoginManager {

    private static final String TAG = LoginManager.class.getName();

    //登录功能
    public static void doPostLogin(String phone, String pwd, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.login");
        params.put("phone_mob", phone);
        params.put("password", pwd);
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("device_no", GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("guid", DeviceUtil.getGuid());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                Log.e("doPostLogin: ",data.toString());
                callback.onSuccess(data);

                UserCenterManager.logClickEvent(LogEvents.PASSWORD_LOGIN_SUCCESS);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
                UserCenterManager.logClickEvent(LogEvents.PASSWORD_LOGIN_FAIL);
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);

                callback.onFail(msg);
            }
        });
    }

    //快速游戏接口请求
    public static void doPostFastLogin(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.fastlogin");
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("mac", GlobalUtil.getLocalMacAddress(PluginPlatform.getInstance().getActivity()));
        params.put("imei", GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("guid", DeviceUtil.getGuid());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                Log.e("doPostFastLogin: ",data.toString());
                callback.onSuccess(data);

                UserCenterManager.logClickEvent(LogEvents.FAST_LOGIN_SUCCESS);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);

                UserCenterManager.logClickEvent(LogEvents.FAST_LOGIN_FAIL);
            }
        });
    }

    //快速游戏接口请求
    public static void doPostFastLoginWithUid(String uid,final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.fastlogin");
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("mac", GlobalUtil.getLocalMacAddress(PluginPlatform.getInstance().getActivity()));
        params.put("imei", GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("guid", DeviceUtil.getGuid());
        params.put("uid", uid);

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                Log.e("doPostFastLogin: ",data.toString());
                callback.onSuccess(data);

                UserCenterManager.logClickEvent(LogEvents.FAST_LOGIN_SUCCESS);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);

                UserCenterManager.logClickEvent(LogEvents.FAST_LOGIN_FAIL);
            }
        });
    }


    //根据qq返回信息登录
    public static void loginByQQ(String result, final HttpRequestCallback callback) {
        ULogUtil.d(TAG,"loginByQQ....");
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.connect");
        params.put("open_code", "qq");
        params.put("open_data", result);
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("guid", DeviceUtil.getGuid());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }







    //根据token自动登录
    public static void loginByToken(String token, final HttpRequestCallback callback) {
        ULogUtil.d(TAG,"loginByToken....");
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.login_by_token");
        params.put("token", token);
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("guid", DeviceUtil.getGuid());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }


    //根据guid自动登录
    public static void loginByGuid(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.login_by_otp");
        params.put("guid", DeviceUtil.getGuid());
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("phone_mob", DeviceUtil.getPhoneNumber());
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
//        params.put("password", UserData.getTmpPwd());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }


    //老的一键登录的用户根据guid自动登录 3.2.0版本新增
    public static void loginByGuidNew(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.login_by_guid");
        params.put("old_guid", DeviceUtil.getOldGuid());
        params.put("guid", DeviceUtil.getGuid());
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("phone_mob", DeviceUtil.getPhoneNumber());
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
//        params.put("password", UserData.getTmpPwd());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }

    public static void loginByGuidAndPassw(String passw, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.login_by_guid");
        params.put("old_guid", DeviceUtil.getOldGuid());
        params.put("guid", DeviceUtil.getGuid());
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("phone_mob", DeviceUtil.getPhoneNumber());
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("password", passw);

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }


    //根据Guid和随机密码登录
    public static void checkRegisterByGuid(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.check_reg_by_guid");
        params.put("guid", DeviceUtil.getGuid());
//        params.put("password", UserData.tmpPwd);

        HttpProxy.post(params, CheckResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }


    //根据Guid和随机密码登录
    public static void checkRegisterByOldGuid(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.check_reg_by_guid");
        params.put("guid", DeviceUtil.getOldGuid());
//        params.put("password", UserData.tmpPwd);

        HttpProxy.post(params, CheckResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }


    //解析第三方登录的返回结果
    public static void parseThirdUserInfo(JSONObject response, HttpRequestCallback callback) {
        ULogUtil.d(TAG,"[parseThirdUserInfo]..   response :   "+response.toString());
        if (response != null) {
            try {
                boolean status = response.getBoolean("status");
                if (status) {
                    User userBean = new User();
                    userBean.setSid(response.getString("sid"));
                    userBean.setUid(response.getString("uid"));
                    userBean.setNickName(response.getString("nickname"));
                    ULogUtil.d(TAG,"[parseThirdUserInfo....]   Uid : " + response.getString("uid"));
                    if (response.has("token")) userBean.setToken(response.getString("token"));

                    if (callback != null) {
                        callback.onSuccess(userBean);
                        if (response.getString("login_type").equals("third_party_qq")){
                            UserCenterManager.logClickEvent(LogEvents.QQ_LOGIN_SUCCESS);
                        }else if (response.getString("login_type").equals("third_party_weibo")){
                            UserCenterManager.logClickEvent(LogEvents.WEIBO_LOGIN_SUCCESS);
                        }
                    }


                } else {
                    if (callback != null) {
                        callback.onFail(response.getString("errortext"));

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("UserModel", "parseThirdUserInfo : jsonexception");
                if (callback != null) {
                    callback.onFail("登录失败");
                }
            }
        } else {
            callback.onFail("登录失败");
        }
    }

    //动态验证码登录
    public static void loginByVCode(String phone, String vCode, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.login_by_verifycode");
        params.put("verifycode", vCode);
        params.put("phone_mob", phone);
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("guid", DeviceUtil.getGuid());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
                UserCenterManager.logClickEvent(LogEvents.CODE_LOGIN_SUCCESS);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
                UserCenterManager.logClickEvent(LogEvents.CODE_LOGIN_FAIL);
            }
        });
    }

    //通过手机号检查是否已注册过账号
    public static void checkPhoneHasRegister(String phone, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.check_reg_by_phone");
        params.put("phone_mob", phone);

        HttpProxy.post(params, CheckResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }

    //通过设备号检查是否曾经试玩过且当前还没有绑定过手机的
    public static void checkDeviceHasPlayed(String deviceNo, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.check_reg_by_device");
        params.put("device_no", deviceNo);

        HttpProxy.post(params, CheckResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }

    //通过SMS方式登录
    public static void loginBySMS(String phone, String pwd, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.login_by_otp");
        params.put("password", pwd);
        params.put("phone_mob", phone);
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("guid", DeviceUtil.getGuid());


        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }

    //获取登录类型是什么
    public static void getLoginType(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.get_login_type");
        params.put("token", UserModel.getInstance().getToken());
        params.put("game_id", Config.getGameId());
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("old_guid", DeviceUtil.getOldGuid());
        params.put("guid", DeviceUtil.getGuid());
        params.put("phone_mob", DeviceUtil.getPhoneNumber());

        HttpProxy.post(params, LoginTypeEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }


}
