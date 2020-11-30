package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

import java.util.List;

/**
 * Created by eleven
 * on 2016/12/19 下午3:04.
 */

public class GiftListResult extends BaseResult<GiftItemEntity> {
    public List<GiftItemEntity> list;
    public String usage;
}
