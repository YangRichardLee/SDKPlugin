package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

/**
 * Created by Antec on 2018/3/30.
 */
public class GetIdCardInfoEty extends BaseResult{

    private int showType;// 0不提示  1提示一次
    private int forcedVerified;// 0非强制认证、不提示 1非强制认证、提示一次  2非强制认证、一直提示 3强制认证、一直提示
    private int payAgeLimit ;// 支付年龄限制 0不提示  1强制提示
    private int playTimeLimit; // 游玩时间限制 0不提示  1强制提示

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getForcedVerified() {
        return forcedVerified;
    }

    public void setForcedVerified(int forcedVerified) {
        this.forcedVerified = forcedVerified;
    }

    public void showReduce(){
        showType--;
    }

    public int getPayAgeLimit() {
        return payAgeLimit;
    }

    public void setPayAgeLimit(int payAgeLimit) {
        this.payAgeLimit = payAgeLimit;
    }

    public int getPlayTimeLimit() {
        return playTimeLimit;
    }

    public void setPlayTimeLimit(int playTimeLimit) {
        this.playTimeLimit = playTimeLimit;
    }

    public void forcedVerifiedReduce(){
        if (forcedVerified == 1){
            forcedVerified--;
        }
    }

    @Override
    public String toString() {
        return "GetIdCardInfoEty{" +
                "showType=" + showType +
                ", forcedVerified=" + forcedVerified +
                ", payAgeLimit=" + payAgeLimit +
                ", playTimeLimit=" + playTimeLimit +
                '}';
    }
}
