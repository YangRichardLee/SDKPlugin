package com.lion.plugin.sdkplugin.sdk.modle;


import com.lion.plugin.sdkplugin.sdk.base.BaseResult;
import java.util.List;

public class AdInfoEntity extends BaseResult {

    /**
     * enable_ads : adnet,oceanengine
     * ads_rule : {"polling_type":"polling","polls_num":"0","list":[{"appid":"test1","posid":"test1","advertiser":"adnet"},{"appid":"test2","posid":"test2","advertiser":"oceanengine"}]}
     * status : true
     */

    //adnet 优量汇  oceanengine 穿山甲

    private String enable_ads;
    private AdsRuleBean ads_rule;

    public String getEnable_ads() {
        return enable_ads;
    }

    public void setEnable_ads(String enable_ads) {
        this.enable_ads = enable_ads;
    }

    public AdsRuleBean getAds_rule() {
        return ads_rule;
    }

    public void setAds_rule(AdsRuleBean ads_rule) {
        this.ads_rule = ads_rule;
    }

    public static class AdsRuleBean {
        /**
         * polling_type : polling
         * polls_num : 0
         * list : [{"appid":"test1","posid":"test1","advertiser":"adnet"},{"appid":"test2","posid":"test2","advertiser":"oceanengine"}]
         */

        private String polling_type;
        private String polls_num;
        private List<ListBean> list;

        public String getPolling_type() {
            return polling_type;
        }

        public void setPolling_type(String polling_type) {
            this.polling_type = polling_type;
        }

        public String getPolls_num() {
            return polls_num;
        }

        public void setPolls_num(String polls_num) {
            this.polls_num = polls_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * appid : test1
             * posid : test1
             * advertiser : adnet
             */

            private String appid;
            private String posid;
            private String advertiser;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPosid() {
                return posid;
            }

            public void setPosid(String posid) {
                this.posid = posid;
            }

            public String getAdvertiser() {
                return advertiser;
            }

            public void setAdvertiser(String advertiser) {
                this.advertiser = advertiser;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "appid='" + appid + '\'' +
                        ", posid='" + posid + '\'' +
                        ", advertiser='" + advertiser + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "AdsRuleBean{" +
                    "polling_type='" + polling_type + '\'' +
                    ", polls_num='" + polls_num + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AdInfoEntity{" +
                "enable_ads='" + enable_ads + '\'' +
                ", ads_rule=" + ads_rule +
                '}';
    }
}
