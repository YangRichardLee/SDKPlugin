package com.lion.plugin.sdkplugin.sdk.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.pluginlibs.AppStatus;
import com.lion.plugin.pluginlibs.BaseFragment;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.callback.CallBackManager;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.manager.ConfigManager;
import com.lion.plugin.sdkplugin.sdk.manager.UserCenterManager;
import com.lion.plugin.sdkplugin.sdk.modle.GetIdCardInfoEty;
import com.lion.plugin.sdkplugin.sdk.modle.RequestNewEntity;
import com.lion.plugin.sdkplugin.sdk.precenter.IUserView;
import com.lion.plugin.sdkplugin.sdk.precenter.UserModel;
import com.lion.plugin.sdkplugin.sdk.uitils.GlobalUtil;

import java.util.Set;

/**
 * 用户类型的Fragment基类
 * Created by Eleven on 16/5/7.
 */
public class UserBaseFragment extends BaseFragment implements IUserView {

    private LocalBroadcastManager localBroadcastManager;

    private static final String TAG = UserBaseFragment.class.getName();

    @Override
    public Context getContext() {
        return PluginPlatform.getInstance().getActivity();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getVCode() {
        return null;
    }

    @Override
    public String getNickName() {
        return null;
    }

    @Override
    public void closeActivity() {
//        baseActivity.finish();
    }

    @Override
    public void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(PluginPlatform.getInstance().getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHistoryUser(Set<String> userList) {

    }

    @Override
    public void loginSuccess(boolean isRegister) {
        if (isRegister)
            GlobalUtil.shortToast("恭喜恭喜，注册成功了！");
        goLogin();
    }


    public void checkShouldGoToBind() {
        UserCenterManager.getIdCardInfo(new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                GetIdCardInfoEty entity = (GetIdCardInfoEty) data;
                ConfigManager.getInstance().setmGetIdCardInfoEty(entity);
                checkIdCardShow();
            }

            @Override
            public void onFail(String msg) {
                GlobalUtil.shortToast(msg);
                closeActivity();
            }
        });
    }


    public void refreshIdCardBind() {
        UserCenterManager.getIdCardInfo(new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                GetIdCardInfoEty getIdCardInfoEty = (GetIdCardInfoEty) data;
                ConfigManager.getInstance().setmGetIdCardInfoEty(getIdCardInfoEty);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
            }
        });
//        UserCenterManager.getUserRealNameInfo(null);
    }

    public void refreshUserInfo() {
        UserCenterManager.requestGetUserInfo(new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                User user = (User) data;
                UserModel.getInstance().setUserInfo(user);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
            }
        });
    }

    public void checkIdCardShow() {
        GetIdCardInfoEty ety = ConfigManager.getInstance().getmGetIdCardInfoEty();
        if (UserModel.getInstance().isNeedBindIdCardInfo()) {
            if (ety.getShowType() == 1) {
                goToBindIdCardInfo();
            } else {
                finishActivity();
            }
        } else {
            finishActivity();
        }
    }


    public void goToBindIdCardInfo() {
//        if (baseActivity != null) {
//            Intent intent = new Intent();
//            intent.setClass(baseActivity, BindIdCardActivity.class);
//            baseActivity.startActivity(intent);
//        }
        finishActivity();
    }
    private void setDot(RequestNewEntity count) {
        if (count.activity > 0 || count.gifts > 0 || count.news > 0) {
//            FloatWindow.getInstance(Platform.getInstance().getContext()).showRedDot();
        }
    }

    private void goLogin() {
//        if (!TextUtils.isEmpty(FileUserInfoManager.getInstance().getLastUser().phone)) {
//            GlobalUtil.newtopToast(FileUserInfoManager.getInstance().getLastUser().phone + " , " + "欢迎进入游戏");
//        } else if (!TextUtils.isEmpty(FileUserInfoManager.getInstance().getLastUser().nickname)) {
//            GlobalUtil.newtopToast(FileUserInfoManager.getInstance().getLastUser().nickname + " , " + "欢迎进入游戏");
//        }
//
//
//        if (UserModel.getInstance().getUser().new_user) {
//            PluginDataUtils.getInstance().registerSucc("gm", System.currentTimeMillis());
//        }
//        if (ConfigManager.getInstance().isJrttPay()){
//            PluginDataUtils.getInstance().paySucc("gm",6, "金砖");
//        }
//        Tracking.setLoginSuccessBusiness(UserModel.getInstance().getUser().getUid());
//        UserCenterManager.requestNewStatus(new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//                RequestNewResult entity = (RequestNewResult) data;
//                if (entity.count != null) {
//                    setDot(entity.count);
//                }
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//            }
//        });
//
//        UserCenterManager.requestPlatformGiftInfo(false, new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//            }
//        });
//        checkShouldGoToBind();
    }


//    private void goToAccountInfoShotFragment() {
//        LoginAccountInfoShotFragment accountInfoShotFragment = LoginAccountInfoShotFragment
//                .getFragmentByName(getActivity(),
//                        LoginAccountInfoShotFragment.class);
//        redirectFragmentCantGoBack(accountInfoShotFragment);
//    }
//
//    private void goToPlatformGiftTipsView() {
//        PlatformGiftFragment giftFragment = PlatformGiftFragment.getFragmentByName(getActivity(), PlatformGiftFragment.class);
//        redirectFragmentCantGoBack(giftFragment);
//    }

    @Override
    public void autoLoginFail() {

    }

    @Override
    public void resetPwdSuccess(String msg) {

    }

    @Override
    public boolean isVerticalScreen() {
        Configuration mConfiguration = PluginPlatform.getInstance().getActivity().getResources().getConfiguration();//获取配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        return ori == Configuration.ORIENTATION_PORTRAIT;
    }

    @Override
    public void onBindPhoneSuccess() {
    }

    @Override
    public void loginFail() {
    }

    @Override
    public void sendCodeResult(boolean result, String msg) {

    }

    public void finishActivity(){
        CallBackManager.makeCallBack(AppStatus.FINISH_PLUGIN);
    }

    public void logMyPageName(String pageName,String enterOrLeave) {
        UserCenterManager.logPage(pageName,enterOrLeave);
    }

    public void logMyClickEvent(String event){
        UserCenterManager.logClickEvent(event);
    }
}
