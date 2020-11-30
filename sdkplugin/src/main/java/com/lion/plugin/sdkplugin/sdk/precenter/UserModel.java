package com.lion.plugin.sdkplugin.sdk.precenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;
import com.lion.plugin.sdkplugin.sdk.uitils.SharedPreferencesUtil;

/**
 * 用户Model
 * Created by Eleven on 16/4/23.
 */
public class UserModel implements IUserModel {
    private static UserModel instance;
    private User userBean;
    private int userState = 0;


    public static UserModel getInstance() {
        if (instance == null) {
            instance = new UserModel();
        }
        return instance;
    }

    public boolean isTrialLogin() {
        return isLogin() && userState == UserPresenter.TRIAL;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    private UserModel() {

    }

    public String getLoginAccount() {
        if (userBean != null && userBean.account != null) {
            return userBean.account;
        } else
            return "";
    }

    public String getLoginPwd() {
        if (userBean != null && userBean.password != null) {
            return userBean.password;
        } else
            return "";
    }

    //是否弹出过实名认证的界面
    public boolean hasPromptIdBindView() {
        return SharedPreferencesUtil.getBoolean(SPConstants.GM_USER_HAS_PROMPT_ID_BIND_VIEW, false);
    }

    //设置是否弹出过
    public void setHasPromptIdBindView(boolean hasPrompt) {
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_PROMPT_ID_BIND_VIEW, hasPrompt);
    }

    //获取上次的Id绑定类型
    public int getLastIdBindType() {
        return SharedPreferencesUtil.getInt(SPConstants.GM_USER_LAST_ID_BIND_TYPE, 0);
    }

    //设置上次的绑定类型
    public void setLastIdBindType(int type) {
        SharedPreferencesUtil.saveInt(SPConstants.GM_USER_LAST_ID_BIND_TYPE, type);
    }

    public String getIdCardName() {
        if (userBean != null && userBean.name != null)
            return userBean.name;
        else
            return "";
    }

    public void setIdCardName(String name) {
        if (userBean != null) {
            userBean.name = name;
            SharedPreferencesUtil.saveString(SPConstants.GM_USER_ID_CARD_NAME, name);
        }
    }

    public String getIdCardNo() {
        if (userBean != null && userBean.idNo != null)
            return userBean.idNo;
        else
            return "";
    }

    public void setIdNo(String idNo) {
        if (userBean != null) {
            userBean.name = idNo;
            SharedPreferencesUtil.saveString(SPConstants.GM_USER_ID_CARD_NO, idNo);
        }
    }

    public boolean isNeedBindIdCardInfo() {
        return userBean != null && userBean.isNeedBindIdCardInfo();
    }

    public void saveIdCardInfo(boolean needBind) {
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_NEED_BIND_ID_CARD_INFO, needBind);
    }

    public boolean userNeedBind() {
        return userBean != null && userBean.isNeed_bind();
    }

    @Override
    public User getUser() {
        if (null == userBean)
            return new User();
        return userBean;
    }

    public boolean isLogin() {
        if (userBean == null) {
            return false;
        }

        if (TextUtils.isEmpty(userBean.getSid())) {
            return false;
        }

        if (TextUtils.isEmpty(userBean.getUid())) {
            return false;
        }

//        //FIXME 2018-2-7   逻辑判断需要更改
//        if (!FileUserInfoManager.getInstance().fileIsExists()){
//            return false;
//        }

        return true;
    }

    public void logout() {
        userBean = null;
    }


    public void saveUserPhone(String phone) {
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PHONE, phone);
    }

    /**
     * 保存用户登录的密码
     *
     * @param pwd
     */
    @Override
    public void saveUserPassword(String pwd) {
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PASSWORD, pwd);
    }

    @Override
    public String getUserPromotePoint() {
        if (userBean != null)
            return userBean.getPromote_coin();
        return "0.00";
    }


    @Override
    public void saveToken(String token) {
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_TOKEN, token);
    }

    @Override
    public String getToken() {
//        if (userBean != null)
//            return userBean.getToken();
//        else
//            return SharedPreferencesUtil.getString(SPConstants.GM_USER_TOKEN, "");
        if (userBean != null) {
            return userBean.getToken();
        } else {
            return SharedPreferencesUtil.getString(SPConstants.GM_USER_TOKEN, "");
        }
    }

    @Override
    public String getPhone(Context context) {
        return SharedPreferencesUtil.getString(SPConstants.GM_USER_PHONE, "");
    }

    @Override
    public String getPassword(Context context) {
        return SharedPreferencesUtil.getString(SPConstants.GM_USER_PASSWORD, "");
    }

    @Override
    public void saveNickName(Context context, String nickName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nickName", nickName);
        editor.commit();
    }

    @Override
    public void clearSP(Context context) {
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_SID, "");
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_UID, "");
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_NICK_NAME, "");
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_FACE, "");
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_NEED_BIND, false);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_COINS, "");
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PROMOTE_COIN, "");
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PAY_COUPON_COUNT, "");
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_TOKEN, "");
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_MESSAGE, false);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_GIFT, false);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_KF_MSG, false);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_ACTIVITY, false);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_NEED_BIND_ID_CARD_INFO, false);

        SharedPreferencesUtil.saveBoolean(SPConstants.GM_IS_LOGOUT_BY_USER, true);
    }

    @Override
    public void saveUserCoins(String coins) {
        if (userBean != null)
            userBean.setCoins(coins);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_COINS, coins);
    }

    //更新用户的点券数量
    public void saveUserPromoteCoin(String promoteCoin) {
        if (userBean != null)
            userBean.setPromote_coin(promoteCoin);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PROMOTE_COIN, promoteCoin);
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;

        SharedPreferencesUtil.saveString(SPConstants.GM_USER_SID, userBean.sid);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_UID, userBean.uid);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_NICK_NAME, userBean.nickname);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_FACE, userBean.face);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_NEED_BIND, userBean.need_bind);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_COINS, userBean.coins);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PROMOTE_COIN, userBean.promote_coin);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PAY_COUPON_COUNT, userBean.pay_coupon_count);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_TOKEN, userBean.token);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_MESSAGE, userBean.has_new_msg);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_GIFT, userBean.has_new_giftbox);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_ACTIVITY, userBean.has_discount);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PAY_COUPON_COUNT_ALL, userBean.pay_coupon_count_all);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_NEED_BIND_ID_CARD_INFO, userBean.needBindIdCardInfo);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_ID_CARD_NAME, userBean.name);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_ID_CARD_NO, userBean.idNo);
//        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_PROMPT_ID_BIND_VIEW, false);

        SharedPreferencesUtil.saveBoolean(SPConstants.GM_IS_LOGOUT_BY_USER, false);
    }


    public void setUserInfo(User userBean) {
        this.userBean.sid = userBean.sid;
        this.userBean.uid = userBean.uid;
        this.userBean.nickname = userBean.nickname;
        this.userBean.face = userBean.face;
        this.userBean.coins = userBean.coins;
        this.userBean.need_bind = userBean.need_bind;
        this.userBean.user_point = userBean.user_point;
        this.userBean.promote_coin = userBean.promote_coin;
        this.userBean.pay_coupon_count = userBean.pay_coupon_count;
//        this.userBean.pay_coupon_count_all = userBean.pay_coupon_count_all;
//        this.userBean.has_new_msg = userBean.has_new_msg;
//        this.userBean.has_new_giftbox = userBean.has_new_giftbox;
//        this.userBean.has_discount = userBean.has_discount;
        this.userBean.needBindIdCardInfo = userBean.needBindIdCardInfo;
        this.userBean.name = userBean.name;
        this.userBean.idNo = userBean.idNo;
        this.userBean.phone = userBean.phone;
        this.userBean.vip_level = userBean.vip_level;
        this.userBean.new_user = userBean.new_user;
        this.userBean.account = userBean.account;
        this.userBean.password = userBean.password;

        //快手注册事件
//        if (userBean != null && userBean.new_user) {
//            KSSDKUtils.onRegister();
//        }
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_SID, userBean.sid);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_UID, userBean.uid);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_NICK_NAME, userBean.nickname);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_FACE, userBean.face);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_COINS, userBean.coins);

        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_NEED_BIND, userBean.need_bind);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_USER_POINT, userBean.user_point);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PROMOTE_COIN, userBean.promote_coin);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_PAY_COUPON_COUNT, userBean.pay_coupon_count);
     //   SharedPreferencesUtil.saveString(SPConstants.GM_USER_PAY_COUPON_COUNT_ALL, userBean.pay_coupon_count_all);
      //  SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_MESSAGE, userBean.has_new_msg);
     //   SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_GIFT, userBean.has_new_giftbox);
     //   SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_DISCOUNT, userBean.has_discount);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_NEED_BIND_ID_CARD_INFO, userBean.needBindIdCardInfo);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_ID_CARD_NAME, userBean.name);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_ID_CARD_NO, userBean.idNo);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_VIP_LEVEL, userBean.vip_level);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_IS_NEW_USER, userBean.new_user);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_NEW_USER_ACCOUNT, userBean.account);
        SharedPreferencesUtil.saveString(SPConstants.GM_USER_NEW_USER_PASSWORD, userBean.password);

    }

    //清除所有用户的阅读状态
    public void clearUserReadStatus() {
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_CENTER_MESSAGE_NEW_TIPS_HAS_READ, false);
        SharedPreferencesUtil.saveInt(SPConstants.GM_USER_CENTER_MESSAGE_COUNT, 0);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_CENTER_ACTIVITY_NEW_TIPS_HAS_READ, false);
        SharedPreferencesUtil.saveInt(SPConstants.GM_USER_CENTER_ACTIVITY_COUNT, 0);
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_CENTER_GIFT_NEW_TIPS_HAS_READ, false);
        SharedPreferencesUtil.saveInt(SPConstants.GM_USER_CENTER_GIFT_COUNT, 0);
    }

    public String getSid() {
        return userBean.getSid();
    }

    public String getCoin() {
        return userBean.getCoins();
    }
}
