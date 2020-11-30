package com.lion.plugin.sdkplugin.sdk.modle;

/**
 * Created by zhewang on 2020/10/29.
 */
public class BannerDataBean {
    public String imageUrl;
    public String loadurl;
    public String id;
    public int viewType;
    public String landimageurl;

    public BannerDataBean(String imageUrl, String loadurl, String id, String landimageurl, int viewType) {
        this.imageUrl = imageUrl;
        this.landimageurl = landimageurl;
        this.viewType = viewType;
        this.loadurl = loadurl;
        this.id = id;
    }
}
