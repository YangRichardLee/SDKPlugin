package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

/**
 * 用户信息实体类
 * Created by eleven
 * on 2016/12/19 下午6:42.
 */

public class UserInfoEntity extends BaseResult {
    public String sid;              //sid
    public String uid;                 //uid
    public String nickname;         //昵称
    public String face;             //头像url
    public boolean need_bind;       //是否需要绑定
    public String coins;             //猫币数量
    public String promote_coin;      //点券数量
    public String user_point;          //用户积分
    public String pay_coupon_count;    //支付优惠券数量
}
