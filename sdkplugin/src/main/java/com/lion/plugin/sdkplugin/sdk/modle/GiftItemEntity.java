package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * 礼包实体类
 * Created by eleven
 * on 2016/11/13 下午5:12.
 */

public class GiftItemEntity implements Serializable {

    public String gift_id;           //礼包码
    public String game_name;         //适用游戏名称（目前的逻辑是只会返回当前游戏的礼包码）
    public String game_icon;         //使用游戏的icon
    public String gift_name;         //礼包名称
    public String gift_content;      //礼包说明
    public String gift_use_type;      //礼包使用类型说明
    public String gift_end_time;      //礼包有效期
    public boolean geted;           //是否领取过
    public String gift_code;         //礼包码
    public String gift_sended_count;  //礼包总的已领取的次数
    public String gift_count;        //礼包总数
    public int gift_active_per;       //礼包剩余百分比
    public int user_point;           //用户积分
    public String coupon_sn;        //礼包码


    //新增字段
    public int gift_type;    //1. APP专享，2. VIP专享，0. 普通礼包

}
