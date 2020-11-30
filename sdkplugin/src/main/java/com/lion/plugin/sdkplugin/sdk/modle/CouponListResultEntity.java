package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

import java.util.List;

/**
 * Created by eleven
 * on 2017/1/3 上午11:47.
 */

public class CouponListResultEntity extends BaseResult {
    public String wait_to_get_coupon;
    public List<CouponItemEntity> list;
}
