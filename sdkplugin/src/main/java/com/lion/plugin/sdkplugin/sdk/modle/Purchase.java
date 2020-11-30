package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * 支付实体Model
 * Created by Eleven on 16/4/23.
 */
public class Purchase implements Serializable {
    private double coins;
    private String productName;         //商品名称
    private String serverid;
    private String roleid;
    private String developerInfo;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public String getServerid() {
        return serverid;
    }

    public void setServerid(String serverid) {
        this.serverid = serverid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getDeveloperInfo() {
        return developerInfo;
    }

    public void setDeveloperInfo(String developerInfo) {
        this.developerInfo = developerInfo;
    }
}
