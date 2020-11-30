package com.lion.plugin.sdkplugin.sdk.modle;


import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

/**
 * 检查结果实体类
 * Created by eleven
 * on 2016/12/27 下午9:54.
 */

public class CheckResultEntity extends BaseResult {
    public boolean hasRegistered;
    public int regType;             //如已注册，那么对应的用户的注册方式
}
