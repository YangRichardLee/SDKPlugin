package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

import java.util.List;

/**
 * Created by zhewang on 2020/10/28.
 */

public class BannerResultEntity extends BaseResult {
    public List<BannerResultItemEntity> list;
    public boolean hasBanner;

    public List<BannerResultItemEntity> getBannerlist() {
        return list;
    }

    public static class BannerResultItemEntity {
        public String banner_w;         //横屏的广告位
        public String banner_h;         //竖屏的广告位
        public String link_url;         //跳转的url
        public String banner_id;        //id,所有banner广告中唯一
        public String frequency;        //弹出类型 0 每次登陆弹出（默认情况） 1 每日首次登陆
        public String sort_order;       //排序ID：数字越小的，越靠前
        public Boolean is_closed;
        public String getBanner_w() {
            return banner_w;
        }

        public Boolean getIs_closed() {
            return is_closed;
        }

        public void setIs_closed(Boolean is_closed) {
            this.is_closed = is_closed;
        }

        public void setBanner_w(String banner_w) {
            this.banner_w = banner_w;
        }

        public String getBanner_h() {
            return banner_h;
        }

        public void setBanner_h(String banner_h) {
            this.banner_h = banner_h;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        public String getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(String banner_id) {
            this.banner_id = banner_id;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }
    }
}
