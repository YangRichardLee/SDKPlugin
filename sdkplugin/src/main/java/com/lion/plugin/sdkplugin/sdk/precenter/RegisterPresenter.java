package com.lion.plugin.sdkplugin.sdk.precenter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.game.sdk.reconstract.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eleven
 * on 2016/12/20 下午3:25.
 */

public class RegisterPresenter implements IRegisterPresenter {

    private static final String TAG = RegisterPresenter.class.getName();

    private IUserView userView;

    public RegisterPresenter(IUserView userView) {
        this.userView = userView;
    }

    @Override
    public void doBindPhone() {
        bindPhone(userView.getPhone(), userView.getPassword(), userView.getVCode(), true);
    }


    private void bindPhone(final String phone, String pwd, String vCode, final boolean shouldToast) {
//    RegisterManager.bindPhone(phone, pwd, vCode,
//            new HttpRequestCallback() {
//              @Override
//              public void onSuccess(Object data) {
//                super.onSuccess(data);
//                ULogUtil.d(TAG, "bindphone on Succ...    data:" + data);
//                userView.dismissLoading();
//                if (data != null) {
//                  //设置用户不再需要进行绑定（本地控制，可能有问题）
//                  UserModel.getInstance().getUser().setNeed_bind(false);
//                  BindPhoneResultEntity bindPhoneResultEntity = (BindPhoneResultEntity) data;
//                  UserModel.getInstance().getUser().phone = phone;
//                  UserModel.getInstance().getUser().token = bindPhoneResultEntity.token;
//
//                  //更新用户信息至文件存储
//                  FileUserInfoManager.getInstance().saveUser(UserModel.getInstance().getUser());
//
//                }
//                ULogUtil.d(TAG, "call  onBindPhoneSuccess...");
//                userView.onBindPhoneSuccess();
//              }
//
//              @Override
//              public void onFail(String msg) {
//                super.onFail(msg);
//                ULogUtil.w(TAG, "bindphone failed, error info :" + msg);
//                userView.dismissLoading();
//                GlobalUtil.shortToast(msg);
//              }
//            });
    }

    @Override
    public void doRegister() {
//        RegisterManager.postDoRegister(userView.getPhone(), userView.getPassword(),
//                userView.getVCode(), new HttpRequestCallback() {
//                    @Override
//                    public void onSuccess(Object data) {
//                        super.onSuccess(data);
//                        userView.dismissLoading();
//                        User user = (User) data;
//                        UserModel.getInstance().setUserBean(user);
//                        UserModel.getInstance().saveUserPhone(userView.getPhone());
//                        UserModel.getInstance().saveUserPassword(userView.getPassword());
//                        FileUserInfoManager.getInstance().saveUser(UserModel.getInstance().getUser());
//                        if (CallbackManager.getLoginCallback() != null) {
//                            CallbackManager.getLoginCallback().loginSuccess(user);
//                            BannerConfig.getInstance().saveUserLoginTime(user.uid);
//                            Platform.getInstance().requestBannerInfo();
//                        }
//                        userView.loginSuccess(true);
//                        Tracking.setRegisterWithAccountID(user.getUid());
//                        LoginPresenter.loginSuccessAndSave(userView.getPhone(), userView.getPassword(), user);
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//                        super.onFail(msg);
//                        userView.dismissLoading();
//                        GlobalUtil.shortToast(msg);
//                        if (CallbackManager.getLoginCallback() != null) {
//                            CallbackManager.getLoginCallback().loginFail(msg);
//                        }
//                    }
//                });
    }

    @Override
    public void doForgetPassword() {
//        RegisterManager.postDoForgetPassword(userView.getPhone(), userView.getPassword(), userView.getVCode(), new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//                userView.dismissLoading();
//                userView.resetPwdSuccess("");
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                userView.dismissLoading();
//                GlobalUtil.shortToast(msg);
//            }
//        });
    }

    public void doGetVerifyCodeChangeBind(final EditText editText) {
//        RegisterManager.postGetVerifyCodeChangeBind(userView.getPhone(), "changeBind", new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//                userView.sendCodeResult(true, "");
//                GlobalUtil.shortToast("验证码发送成功，请注意查收~");
//                BaseResult baseResult = (BaseResult) data;
//                AppInfoUtils.setETHindAndFocusable(editText, baseResult.firstNum);
//
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                GlobalUtil.shortToast(msg);
//            }
//        });
    }


    @Override
    public void doGetVerifyCode(final EditText editText) {
//        RegisterManager.postGetVerifyCode(userView.getPhone(), new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//                userView.sendCodeResult(true, "");
//                GlobalUtil.shortToast("验证码发送成功，请注意查收~");
//                BaseResult baseResult = (BaseResult) data;
//                AppInfoUtils.setETHindAndFocusable(editText, baseResult.firstNum);
//
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                GlobalUtil.shortToast(msg);
//            }
//        });
    }

    @Override
    public void doGetVerifyCodeForForgetPwd(final EditText editText) {
//        RegisterManager.postGetVerifyCodeForForgetPwd(userView.getPhone(), new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//                GlobalUtil.shortToast("验证码发送成功，请注意查收~");
//                userView.sendCodeResult(true, "");
//                BaseResult baseResult = (BaseResult) data;
//                AppInfoUtils.setETHindAndFocusable(editText, baseResult.firstNum);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                GlobalUtil.shortToast(msg);
//            }
//        });
    }

    @Override
    public void doBindPhoneForPurchase() {
        bindPhone(userView.getPhone(), "", userView.getVCode(), true);
    }

    @Override
    public void doBindPhone(boolean shouldToast) {
        bindPhone(userView.getPhone(), "", userView.getVCode(), false);
    }


    /**
     * 检查手机号是否有效
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
//        if (TextUtils.isEmpty(phone)) {
//            GlobalUtil.shortToast("请输入手机号");
//            return false;
//        }
//        String pattern = "^[\\d]{11}";
//        Pattern p = Pattern.compile(pattern);
//        Matcher m = p.matcher(phone);
//        if (!m.matches()) {
//            GlobalUtil.shortToast("手机号格式不正确");
//        }
//        return m.matches();
      return true;
    }

    /**
     * 检查密码是否有效
     *
     * @param passport
     * @return
     */
    public static boolean checkPassport(String passport) {
//        if (TextUtils.isEmpty(passport) || passport.length() < 6 || passport.length() > 14) {
//            GlobalUtil.shortToast("请输入6-14位密码");
//            return false;
//        }
        return true;
    }

    public static boolean checkResetPwdValid(String pwd, String repeatPwd) {
        Log.i("GameSDK", "开始校验密码和重复密码是否一致");
        boolean isValid = false;
//        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(repeatPwd)) {
//            GlobalUtil.shortToast("密码或确认密码为空");
//            isValid = false;
//        }
//        if (!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(repeatPwd)) {
//            if (pwd.equals(repeatPwd))
//                isValid = true;
//            else {
//                GlobalUtil.shortToast("两次输入的密码不一致~");
//                isValid = false;
//            }
//        }
        return isValid;
    }

}
