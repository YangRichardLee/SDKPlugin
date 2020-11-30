package com.lion.plugin.sdkplugin.sdk.manager;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.EditText;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.pluginlibs.AppStatus;
import com.lion.plugin.sdkplugin.sdk.base.BaseResult;
import com.lion.plugin.sdkplugin.sdk.callback.CallBackManager;
import com.lion.plugin.sdkplugin.sdk.constants.BundleConstants;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.precenter.IUserView;
import com.lion.plugin.sdkplugin.sdk.precenter.UserModel;
import com.lion.plugin.sdkplugin.sdk.uitils.AppInfoUtils;
import com.lion.plugin.sdkplugin.sdk.uitils.GlobalUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;

public class LoginPresenter {
    private static final String TAG = LoginPresenter.class.getName();

    IUserView userView;

    MyHandler handler = new MyHandler();

    public LoginPresenter(IUserView userView) {
        this.userView = userView;
    }


    //登录逻辑
    public void login() {
//        if (RegisterPresenter.checkPassport(userView.getPassword()) && !TextUtils.isEmpty(userView.getPhone())) {
//            //    userView.showLoading("正在登录");
//            LoginManager.doPostLogin(userView.getPhone(), userView.getPassword(), new HttpRequestCallback() {
//                @Override
//                public void onSuccess(Object data) {
//                    super.onSuccess(data);
//                    userView.dismissLoading();
//                    User user = (User) data;
//                    loginSuccessAndSave(userView.getPhone(), userView.getPassword(), user);
//                    userView.loginSuccess(false);
//                }
//
//                @Override
//                public void onFail(String msg) {
//                    super.onFail(msg);
//                    userView.dismissLoading();
//                    toastOrSelNum(msg);
//                }
//            });
//        }
    }

    //登录逻辑
    public void login(final String phone, final String pwd) {
//        if (RegisterPresenter.checkPassport(pwd)) {
//            if (userView != null) {
//                //      userView.showLoading("正在登录");
//            }
//            LoginManager.doPostLogin(phone, pwd, new HttpRequestCallback() {
//                @Override
//                public void onSuccess(Object data) {
//                    super.onSuccess(data);
//                    userView.dismissLoading();
//                    User user = (User) data;
//                    loginSuccessAndSave(phone, pwd, user);
//                    userView.loginSuccess(false);
//                    userView.closeActivity();
//                }
//
//                @Override
//                public void onFail(String msg) {
//                    super.onFail(msg);
//                    userView.dismissLoading();
//                    toastOrSelNum(msg);
//                    userView.loginFail();
//                }
//
//                @Override
//                public void onResponse(Object data) {
//                    super.onResponse(data);
//                }
//
//                @Override
//                public void onError(String msg) {
//                    super.onError(msg);
//                }
//            });
//
//
//        }
    }

    //通过短信的方式登录
    public void loginBySms(final String passw) {
//        LoginManager.loginBySMS(DeviceUtil.getPhoneNumber(), passw,
//                new HttpRequestCallback() {
//                    @Override
//                    public void onSuccess(Object data) {
//                        super.onSuccess(data);
//                        User user = (User) data;
//                        userView.dismissLoading();
//                        loginSuccessAndSave(DeviceUtil.getPhoneNumber(), passw, user);
//                        userView.loginSuccess(false);
//                        UserCenterManager.logClickEvent(LogEvents.ONE_KEY_LOGIN_SUCCESS);
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//                        super.onFail(msg);
//                        userView.dismissLoading();
//                        toastOrSelNum(msg);
//                        userView.loginFail();
//                        UserCenterManager.logClickEvent(LogEvents.ONE_KEY_LOGIN_FAIL);
//                    }
//                });
    }

    //快速游戏逻辑
    public void fastLogin() {
        //   userView.showLoading("正在登录");
//        LoginManager.doPostFastLogin(new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//                ULogUtil.d(TAG, "fast gm_new_login succ..");
//                SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_FIRST_FAST_LOGIN, true);
//                // GlobalUtil.newtopToast(FileUserInfoManager.getInstance().getLastUser().nickname + " , " + "欢迎进入游戏");
//                userView.dismissLoading();
//                User user = (User) data;
//                UserModel.getInstance().setUserBean(user);
//                loginSuccessAndSave("", "", user);
//                userView.loginSuccess(false);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                userView.dismissLoading();
//                toastOrSelNum(msg);
//                userView.loginFail();
//            }
//        });
    }

    public void getVerifyCodeForLogin(String phone, final EditText editText) {
        RegisterManager.postGetVerifyCodeByType(phone, "login", new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                userView.sendCodeResult(true, "");
                BaseResult baseResult = (BaseResult) data;
                String firstNum = baseResult.firstNum;
                GlobalUtil.shortToast("验证码发送成功，请注意查收~");
                AppInfoUtils.setETHindAndFocusable(editText, firstNum);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                GlobalUtil.shortToast(msg);
            }
        });
    }

    public void loginByVCode(final String phone, String vCode) {
        LoginManager.loginByVCode(phone, vCode, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                userView.dismissLoading();
                User user = (User) data;
//                GlobalUtil.shortToast("登录成功，当前的电话号码是：" + phone);
                loginSuccessAndSave(phone, "", user);
//                getUserInfo();
                userView.loginSuccess(false);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                userView.dismissLoading();
                if (msg.contains("封号")) {
//                    GameInfoManager.roleCaveatOrSelNumDialog("封号通知", msg);
                } else {
                    GlobalUtil.shortToast("登录状态失效，请重新登录");
                }
                userView.loginFail();
            }
        });
    }

    public void loginByToken(String token) {
        LoginManager.loginByToken(token, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                userView.dismissLoading();
                User user = (User) data;
                loginSuccessAndSave(user);
                userView.loginSuccess(false);
                userView.closeActivity();
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                userView.dismissLoading();
                if (msg.contains("封号")) {
//                    GameInfoManager.roleCaveatOrSelNumDialog("封号通知", msg);
                } else {
                    GlobalUtil.shortToast("登录状态失效，请重新登录");
                }
                userView.loginFail();
            }
        });
    }

    public static void loginSuccessAndSave(User user) {
        loginSuccessAndSave("", "", user);
    }

    public static void loginSuccessAndSave(final String phone, final String pwd, final User user) {
        ULogUtil.d(TAG, "loginSuccessAndSave..");
        if (user != null) {
            setLoginSucc(phone, pwd, user);
        }
    }


    public static void setLoginSucc(String phone, String pwd, User user) {
//        Platform.doSomething();
        CallBackManager.makeCallBack(AppStatus.LOGIN_SUCCESS, user);
        UserModel.getInstance().setUserBean(user);
        UserModel.getInstance().saveUserPhone(user.getPhone());
        UserModel.getInstance().saveUserPassword(pwd);
        if (!TextUtils.isEmpty(user.token)) {
            UserModel.getInstance().saveToken(user.token);
        }
        UserModel.getInstance().setUserInfo(user);
        FileUserInfoManager.getInstance().saveUser(user);
//        BannerConfig.getInstance().saveUserLoginTime(user.uid);
//        Platform.getInstance().requestBannerInfo();
//        UserCenterManager.getUserRealNameInfo(new HttpRequestCallback());
    }

    @SuppressLint("HandlerLeak")
    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                User user = (User) msg.getData().getSerializable(BundleConstants.DATA_FOR_LOGIN_THIRD_LOGIN_ENTITY);
                if (user != null && user.token != null) {
                    loginByToken(user.token);
                }
            }
        }
    }

//    public static void toastOrSelNum(String msg) {
//        if (msg == null) {
//            return;
//        }
//        if (msg.contains("封号")) {
//            GameInfoManager.roleCaveatOrSelNumDialog("封号通知", msg);
//        } else {
//            GlobalUtil.shortToast(msg);
//        }
//    }
}