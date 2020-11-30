package com.lion.plugin.sdkplugin.sdk.modle;


import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

/**
 * Created by eleven
 * on 2017/1/9 下午1:33.
 */

public class VersionCheckResultEntity extends BaseResult {

    public boolean hasNewVersion;
    public boolean forceUpdate;
    public String downloadUrl;
    public String qq_login_type;
    public String pay_type;
}
