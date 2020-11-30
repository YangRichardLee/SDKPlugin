package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

/**
 * Created by eleven
 * on 2017/5/16 上午10:59.
 */

public class IdConfirmCheckResultEntity extends BaseResult {
    public static IdConfirmCheckResultEntity instance ;
    public boolean shouldShow;
    public int showType; //0代表always，1代表once
    public int forcedVerified; //强制验证 0不强制 1强制

    public static IdConfirmCheckResultEntity getInstance(){
        if (instance == null){
            instance = new IdConfirmCheckResultEntity();
        }
        return instance;
    }

    public int getForcedVerified() {
        return forcedVerified;
    }

    public void setForcedVerified(int forcedVerified) {
        this.forcedVerified = forcedVerified;
    }
}
