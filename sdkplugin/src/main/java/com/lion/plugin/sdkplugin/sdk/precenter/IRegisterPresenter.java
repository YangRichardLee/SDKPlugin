package com.lion.plugin.sdkplugin.sdk.precenter;

import android.widget.EditText;

public interface IRegisterPresenter {

    void doBindPhone();

    void doRegister();

    void doForgetPassword();

    void doGetVerifyCode(EditText editText);

    void doGetVerifyCodeForForgetPwd(EditText editText);

    void doBindPhoneForPurchase();

    void doBindPhone(boolean shouldToast);
}
