package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * Created by zhewang on 2020/5/18.
 */
public class MyGiftEntity implements Serializable {
    public String gift_id;           //礼包码
    public String gift_name;         //礼包名称
    public String gift_content;      //礼包说明
    public String gift_use_type;      //礼包使用类型说明
    public String gift_end_time;      //礼包有效期
    public String user_point;        //所需积分
    public String coupon_sn;        //礼包码


    //新增字段
    public int gift_type;    //1. APP专享，2. VIP专享，0. 普通礼包
}
