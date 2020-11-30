package com.lion.plugin.sdkplugin.sdk.modle;


import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;
import com.lion.plugin.sdkplugin.sdk.uitils.SharedPreferencesUtil;

/**
 * Created by eleven
 * on 2017/10/12 下午5:20.
 */

public class CountTipsEntityHolder {

    public static CountTipsEntity entity;
    public static boolean CurrentHasPlatformGift = false;

    public static CountTipsEntity getEntity() {
        return entity;
    }

    public static void setEntity(CountTipsEntity entity) {
        CountTipsEntityHolder.entity = entity;
    }


    public static void updateGiftUpdateStatus(boolean hasUpdate) {
        entity.gift_has_update = hasUpdate;
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_GIFT_HAS_UPDATE, entity.gift_has_update);
    }

    public static void updateMessageUpdateStatus(boolean hasUpdate) {
        entity.message_has_update = hasUpdate;
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_MESSAGE_HAS_UPDATE, entity.message_has_update);
    }

    public static void updateKFUpdateStatus(boolean hasUpdate) {
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_KF_HAS_UPDATE, hasUpdate);
    }

    public static void updatePlatformGiftStatus(boolean hasGift) {
        SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_CURRENT_HAS_NEW_PLATFORM_GIFT, hasGift);
    }
}
