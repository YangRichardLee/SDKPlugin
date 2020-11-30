package com.lion.plugin.sdkplugin.sdk.precenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lion.plugin.pluginlibs.AppStatus;
import com.lion.plugin.sdkplugin.MainActivity;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.callback.CallBackManager;
import com.lion.plugin.sdkplugin.sdk.constants.BundleConstants;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.ui.NewRegisterFragment;
import com.lion.plugin.sdkplugin.sdk.uitils.GlobalUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;

public class UserPresenter {

    public final static int TRIAL = 2;
    private static final String TAG = UserPresenter.class.getName();

    public final static int LOGIN = 1;
    public final static int USER_CENTER = 3;
    /**
     * 启动登录
     *
     * @param activity
     */
    public static void startLogin(final Activity activity) {
        UserCenterPresenter.getCheckVersion(new HttpRequestCallback(){
            @Override
            public void onSuccess(Object data) {
                ULogUtil.e(TAG, "[requestCheckVersion] success..." + data);
                showLoginActivity(activity);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                ULogUtil.d(TAG, "[requestCheckVersion] failed... ");
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                ULogUtil.d(TAG, "[requestCheckVersion] failed... ");
            }

        });
    }

    /**
     * 启动登录界面
     *
     * @param activity
     */
    public static void showLoginActivity(Activity activity) {
        if (!UserModel.getInstance().isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putInt(BundleConstants.DATA_FOR_USER_ACTIVITY_VIEW_TYPE, LOGIN);
            Config.startPluginFragment(activity, NewRegisterFragment.class.getName(),bundle);



        } else {
            CallBackManager.makeCallBack(AppStatus.LOGIN_SUCCESS,UserModel.getInstance().getUser());
            //显示主界面
//            showUserCenterActivity(activity);
        }
    }

    /**
     * 启动登录界面
     *
     * @param activity
     */
    public static void showLoginView(Activity activity) {
        Config.startPluginActivity(activity, MainActivity.class.getName());
    }


    public static void logout(){
        //logout

        //clear userinfo

    }

    public static void showUserCenterActivity(Activity mContext) {
        GlobalUtil.shortToast("显示主界面");
    }
}
