package com.game.sdk.reconstract.model;

import android.text.TextUtils;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

import java.io.Serializable;

/**
 * 用户实体类，全局一个
 * Created by Eleven on 16/4/23.
 */
public class User extends BaseResult implements Serializable {
    private static final long serialVersionUID = 2107120701L;
    public String sid;              //sid
    public String uid;                 //uid
    public String nickname;         //昵称
    public String face;             //头像url
    public boolean need_bind;       //是否需要绑定
    public String coins;             //猫币数量
    public String promote_coin;      //点券数量
    public String user_point;          //用户积分
    public String pay_coupon_count;    //支付优惠券数量
    public String pay_coupon_count_all; //新的支付优惠券的数量
    public String token;
    public String vip_level;               //vip等级

    public boolean has_new_msg;         //是否有新消息
    public boolean has_new_giftbox;         //是否有礼包
    public boolean has_discount;            //是否有活动

    public boolean needBindIdCardInfo;      //是否需要绑定身份证信息
    public String name;                     //身份证的名字
    public String idNo;                     //身份证号

    public String phone;                    //用户绑定的手机号
    public boolean isRegister;              //是否为注册
    public String account;                  //账号
    public String password;                 //密码
    public boolean new_user;                //是否新用户；
    public String getVip_level() {
        if (vip_level == null || TextUtils.isEmpty(vip_level))
            return "0";
        return vip_level;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setVip_level(String vip_level) {
        this.vip_level = vip_level;
    }
    public String getPhone() {
        if (TextUtils.isEmpty(phone))
            return "";
        return phone;
    }

    public String getEncryptPhone() {
        if (TextUtils.isEmpty(phone)) {
            return "";
        } else {
            if (phone.length() >= 11) {
                return phone.substring(0, 3) + "****" + phone.substring(7);
            } else
                return phone;
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isNeedBindIdCardInfo() {
        return needBindIdCardInfo;
    }

    public void setNeedBindIdCardInfo(boolean needBindIdCardInfo) {
        this.needBindIdCardInfo = needBindIdCardInfo;
    }

    public String getSid() {
        if (TextUtils.isEmpty(sid))
            return "";
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickName) {
        this.nickname = nickName;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getCoins() {
        if (TextUtils.isEmpty(coins))
            return "0.00";
        return coins;
    }

    public String getAvatarUrl() {
        return face;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.face = avatarUrl;
    }

    public void setNeed_bind(boolean need_bind) {
        this.need_bind = need_bind;
    }

    public void setPromote_coin(String promote_coin) {
        this.promote_coin = promote_coin;
    }

    public void setUser_point(String user_point) {
        this.user_point = user_point;
    }

    public void setPay_coupon_count(String pay_coupon_count) {
        this.pay_coupon_count = pay_coupon_count;
    }

    public boolean isNeed_bind() {
        return need_bind;
    }

    public String getPromote_coin() {
        if (TextUtils.isEmpty(user_point))
            return "0";
        return promote_coin;
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return idNo;
    }

    public String getUser_point() {
        return user_point;
    }

    public String getPay_coupon_count() {
        return pay_coupon_count;
    }

    public String getPay_coupon_count_all() {
        return pay_coupon_count_all;
    }

    public String getToken() {
            return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public interface UserListener {
        void onSuccess(User userBean);

        void onFail(String msg);
    }

    public boolean hasNewMessage() {
        return has_new_msg;
    }

    public boolean hasNewGiftbox() {
        return has_new_giftbox;
    }

    public boolean hasDiscount() {
        return has_discount;
    }


}
