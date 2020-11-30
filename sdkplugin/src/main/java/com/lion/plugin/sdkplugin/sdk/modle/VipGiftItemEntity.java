package com.lion.plugin.sdkplugin.sdk.modle;

/**
 * Vip礼包实体类
 * Created by eleven
 * on 2017/9/8 上午10:41.
 */

public class VipGiftItemEntity {

    public int gift_id;
    public String vip_level;
    public String gift_type;            //"upgrade". 升级礼包，"week". 周礼包
    public String get_status;          //是否已领取
    public String gift_title;           //礼包名称（写明是否是周礼包）
    public String end_date;             //截止时间
    public String start_time;           //开始时间
    public boolean is_platform_send;    //是否平台赠送
}
