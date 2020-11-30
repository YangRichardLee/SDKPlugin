package com.lion.plugin.sdkplugin.sdk.modle;


import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

/**
 * Created by eleven
 * on 2017/9/12 下午3:24.
 */

public class CountTipsEntity extends BaseResult {

    public boolean has_new_msg;             //是否有新消息
    public boolean has_new_gift_box;        //是否有新的礼包
    public int vip_level;                   //当前用户vip等级
    public boolean has_new_activity;        //是否有新的活动
    public boolean has_new_vip_tips;        //是否有新的vip相关的活动
    public boolean has_new_account_tips;    //是否有新的账户相关的活动可查看
    public String invalid_coupon_count;        //即将过期的优惠券数量
    public String wait_to_get_vip_gift;        //可领取的vip礼包数量
    public String wait_to_get_coupon;     //待领取的优惠券数量
    public String wait_to_get_week_vip_gift;        //待领取的vip周礼包数量
    public String wait_to_get_upgrade_vip_gift;     //待领取的vip升级礼包数量
    public String avalid_coupon_count;              //有效的礼包数量
    public String unread_msg_count;                 //未读消息数量


    public boolean gift_has_update;             //礼包是否有更新
    public boolean message_has_update;          //消息是否有更新

}
