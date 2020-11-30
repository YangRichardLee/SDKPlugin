package com.lion.plugin.sdkplugin.sdk.manager;

import android.text.TextUtils;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.base.BaseResult;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.modle.BindPhoneResultEntity;
import com.lion.plugin.sdkplugin.sdk.modle.UserInfoEntity;
import com.lion.plugin.sdkplugin.sdk.network.HttpProxy;
import com.lion.plugin.sdkplugin.sdk.precenter.UserModel;
import com.lion.plugin.sdkplugin.sdk.uitils.DeviceUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.GlobalUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录、注册、绑定账号的请求层
 * Created by eleven
 * on 2016/12/20 下午3:25.
 */

public class RegisterManager {

    //绑定的账号，绑定手机
    public static void bindPhone(String phone, String pwd, String vCode, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.bind");
        params.put("game_id", Config.getGameId());
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("device_no", GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("phone_mob", phone);
        params.put("verifycode", vCode);
        if (!TextUtils.isEmpty(pwd)){
            params.put("password", pwd);
        }

        HttpProxy.post(params, BindPhoneResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }


    //注册功能
    public static void postDoRegister(String phone, String pwd, String vCode, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.reg");
        params.put("phone_mob", phone);
        params.put("verifycode", vCode);
        params.put("password", pwd);
        params.put("promote", Config.getPromote());
        params.put("game_id", Config.getGameId());
        params.put("device_no", GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("mac", GlobalUtil.getLocalMacAddress(PluginPlatform.getInstance().getActivity()));
        params.put("imei", GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
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
        });
    }

    //换绑验证旧手机
    public static void postDoChangeBind(String vCode, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.change_bind");
        params.put("token", UserModel.getInstance().getToken());
        params.put("old_verifycode", vCode);

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

    //换绑验证新手机
    public static void postDoChangeBindFinal(String oldvCode, String newVcode, String newPhone,  final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.change_bind");
        params.put("token", UserModel.getInstance().getToken());
        params.put("old_verifycode", oldvCode);
        params.put("phone_mob", newPhone);
        params.put("verifycode", newVcode);
        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }


    //忘记密码
    public static void postDoForgetPassword(String phone, String pwd, String vCode, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.forgetpass");
        params.put("phone_mob", phone);
        params.put("verifycode", vCode);
        params.put("newpass", pwd);
        params.put("step", "2");

        HttpProxy.post(params, UserInfoEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

    //获取验证码（注册和手机绑定使用）
    public static void postGetVerifyCode(String phone, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.send_captcha");
        params.put("phone_mob", phone);
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));

        HttpProxy.post(params, BaseResult.class, new HttpRequestCallback() {
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

    //为重置密码获取验证码
    public static void postGetVerifyCodeForForgetPwd(String phone, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.forgetpass");
        params.put("phone_mob", phone);

        HttpProxy.post(params, BaseResult.class, new HttpRequestCallback() {
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

    //获取验证码（注册和手机绑定使用）
    public static void postGetVerifyCodeByType(String phone, final HttpRequestCallback callback) {
        postGetVerifyCodeByType(phone, "idCard", callback);
    }

    //获取验证码（更换绑定使用）
    public static void postGetVerifyCodeChangeBind(String phone, final HttpRequestCallback callback) {
        postGetVerifyCodeByType(phone, "changeBind", callback);
    }


    //获取验证码（注册和手机绑定使用）
    public static void postGetVerifyCodeByType(String phone, String forType, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.send_captcha");
        params.put("phone_mob", phone);
        params.put("device_no",GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("for", forType);

        HttpProxy.post(params, BaseResult.class, new HttpRequestCallback() {
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
    //获取验证码（更换手机绑定使用）
    public static void postGetVerifyCodeChangeBind(String phone, String forType, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.send_captcha");
        params.put("phone_mob", phone);
        params.put("token", UserModel.getInstance().getToken());
        params.put("for", forType);

        HttpProxy.post(params, BaseResult.class, new HttpRequestCallback() {
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
