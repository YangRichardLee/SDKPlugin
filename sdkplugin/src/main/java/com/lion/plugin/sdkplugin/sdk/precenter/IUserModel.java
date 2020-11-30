package com.lion.plugin.sdkplugin.sdk.precenter;

import android.content.Context;

import com.game.sdk.reconstract.model.User;

/**
 * 用户操作接口
 * Created by Eleven on 16/4/23.
 */
public interface IUserModel {
    User getUser();

    void saveUserPassword(String pwd);

    String getPhone(Context context);

    String getPassword(Context context);

    void saveToken(String token);

    String getToken();

    void saveNickName(Context context, String nickName);

    void clearSP(Context context);

    void saveUserCoins(String coins);

    String getUserPromotePoint();
}
