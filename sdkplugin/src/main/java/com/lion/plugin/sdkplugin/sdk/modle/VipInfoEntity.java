package com.lion.plugin.sdkplugin.sdk.modle;

/**
 * Created by eleven
 * on 2017/9/20 下午1:28.
 */

public class VipInfoEntity{
    /**
     * vip_level : 0
     * growth : 70
     * vip_growth : 0
     * next_vip_growth : 1000
     * privilege_level : 0
     * vip_activity_count : 0
     * wait_to_get_vip_gift : 0
     * wait_to_get_week_vip_gift : 0
     * wait_to_get_upgrade_vip_gift : 0
     * status : true
     */

    private String vip_level;
    private String growth;
    private int vip_growth;
    private int next_vip_growth;
    private String privilege_level;
    private String vip_activity_count;
    private String wait_to_get_vip_gift;
    private String wait_to_get_week_vip_gift;
    private String wait_to_get_upgrade_vip_gift;
    private String vip_rights;
    private boolean status;
    public String getVip_rightsUrl() {
        return vip_rights;
    }
    public String getVip_level() {
        return vip_level;
    }

    public void setVip_level(String vip_level) {
        this.vip_level = vip_level;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public int getVip_growth() {
        return vip_growth;
    }

    public void setVip_growth(int vip_growth) {
        this.vip_growth = vip_growth;
    }

    public int getNext_vip_growth() {
        return next_vip_growth;
    }

    public void setNext_vip_growth(int next_vip_growth) {
        this.next_vip_growth = next_vip_growth;
    }

    public String getPrivilege_level() {
        return privilege_level;
    }

    public void setPrivilege_level(String privilege_level) {
        this.privilege_level = privilege_level;
    }

    public String getVip_activity_count() {
        return vip_activity_count;
    }

    public void setVip_activity_count(String vip_activity_count) {
        this.vip_activity_count = vip_activity_count;
    }

    public String getWait_to_get_vip_gift() {
        return wait_to_get_vip_gift;
    }

    public void setWait_to_get_vip_gift(String wait_to_get_vip_gift) {
        this.wait_to_get_vip_gift = wait_to_get_vip_gift;
    }

    public String getWait_to_get_week_vip_gift() {
        return wait_to_get_week_vip_gift;
    }

    public void setWait_to_get_week_vip_gift(String wait_to_get_week_vip_gift) {
        this.wait_to_get_week_vip_gift = wait_to_get_week_vip_gift;
    }

    public String getWait_to_get_upgrade_vip_gift() {
        return wait_to_get_upgrade_vip_gift;
    }

    public void setWait_to_get_upgrade_vip_gift(String wait_to_get_upgrade_vip_gift) {
        this.wait_to_get_upgrade_vip_gift = wait_to_get_upgrade_vip_gift;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


//    public String vip_level;                //VIP等级
//    public String growth;                   //成长值
//    public int vip_growth;               //vip成长值
//    public int next_vip_growth;          //下一级需要的成长值
//    public String privilege_level;          //特权等级
//    public String vip_activity_count;       //VIP活动数量
//
//    public String wait_to_get_vip_gift;     //待领取的vip礼包数量
//    public String wait_to_get_week_vip_gift;        //待领取的vip周礼包数量
//    public String wait_to_get_upgrade_vip_gift;     //待领取的vip升级礼包数量
//
//    @Override
//    public String toString() {
//        return "VipInfoEntity{" +
//                "vip_level='" + vip_level + '\'' +
//                ", growth='" + growth + '\'' +
//                ", vip_growth='" + vip_growth + '\'' +
//                ", next_vip_growth='" + next_vip_growth + '\'' +
//                ", privilege_level='" + privilege_level + '\'' +
//                ", vip_activity_count='" + vip_activity_count + '\'' +
//                ", wait_to_get_vip_gift='" + wait_to_get_vip_gift + '\'' +
//                ", wait_to_get_week_vip_gift='" + wait_to_get_week_vip_gift + '\'' +
//                ", wait_to_get_upgrade_vip_gift='" + wait_to_get_upgrade_vip_gift + '\'' +
//                '}';
//    }
}
