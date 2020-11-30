package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

/**
 * 绑定phone的信息实体类
 * Created by eleven
 * on 2017/3/29 上午10:41.
 */

public class BindPhoneInfoEntity extends BaseResult {
    public boolean isOpenBind;
    public int bindType; //1. 支付前，2. 支付后
}
