package com.lion.plugin.sdkplugin.sdk.modle;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by eleven
 * on 2017/1/3 上午11:46.
 */

public class CouponItemEntity implements Serializable {
    public String pay_coupon_id;
    public String pay_coupon_name;
    public int need_amount;
    public int deduct_amount;
    public String start_time;
    public String end_time;
    public boolean selected;
    public boolean usable;
    public boolean is_expiring;             //是否即将过期
    public String game_name;                        //游戏名称
    public boolean has_get;                         //是否已领取

    public String getStartDateString() {
        String targetString = "";
        if (!TextUtils.isEmpty(start_time)) {
            String monthNum = start_time.substring(5, 7);
            String dayNum = start_time.substring(8);
            if (monthNum.startsWith("0"))
                monthNum = monthNum.substring(1);
            if (dayNum.startsWith("0"))
                dayNum = dayNum.substring(1);
            targetString = monthNum + "月" + dayNum + "日";
        }
        return targetString;
    }


    public String getEndDateString() {
        String targetString = "";
        if (!TextUtils.isEmpty(end_time)) {
            String monthNum = end_time.substring(5, 7);
            String dayNum = end_time.substring(8);
            if (monthNum.startsWith("0"))
                monthNum = monthNum.substring(1);
            if (dayNum.startsWith("0"))
                dayNum = dayNum.substring(1);
            targetString = monthNum + "月" + dayNum + "日";
        }
        return targetString;
    }
}
