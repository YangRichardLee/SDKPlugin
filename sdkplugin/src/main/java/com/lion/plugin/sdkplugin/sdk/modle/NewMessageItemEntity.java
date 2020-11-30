package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * Created by zhewang on 2020/7/2.
 */
public class NewMessageItemEntity implements Serializable {
    public String article_id;
    public String title;
    public String time;
    public String cate_name;
    public String link;
    public Boolean is_new;
}
