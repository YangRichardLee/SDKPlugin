package com.lion.plugin.sdkplugin.sdk.precenter;

import android.content.Context;

import java.util.Set;

/**
 * 用户界面接口定义
 * Created by Eleven on 16/4/27.
 */
public interface IUserView {
    Context getContext();

    void showLoading(String msg);

    void dismissLoading();

    String getPhone();

    String getPassword();

    String getVCode();

    String getNickName();

    void closeActivity();

    void showToast(String msg);

    void loginSuccess(boolean isRegister);

    void setHistoryUser(Set<String> userList);

    void sendCodeResult(boolean result, String msg);

    void autoLoginFail();

    void resetPwdSuccess(String msg);

    boolean isVerticalScreen();

    void onBindPhoneSuccess();

    void loginFail();
}
