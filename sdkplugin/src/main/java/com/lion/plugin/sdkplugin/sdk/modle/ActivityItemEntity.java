package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * Created by eleven
 * on 2016/11/13 上午10:28.
 */

public class ActivityItemEntity implements Serializable {

    public int id;
    public String main_img;
    public String title;
    public String more_url;
    public String start_time;
    public String end_time;
    public int expire_day_count;
    public boolean hasRead;
    public boolean is_new;             //是否是新的
}
