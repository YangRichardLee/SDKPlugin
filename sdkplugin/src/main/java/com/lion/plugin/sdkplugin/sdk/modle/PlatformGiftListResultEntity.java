package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

import java.util.List;

/**
 * 平台福利结果实体类
 * Created by eleven
 * on 2017/9/21 下午3:35.
 */

public class PlatformGiftListResultEntity extends BaseResult{
    public List<CouponItemEntity> coupon_list;
    public List<VipGiftItemEntity> gift_list;
}
