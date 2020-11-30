package com.lion.plugin.sdkplugin.sdk.manager;

import android.text.TextUtils;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.modle.*;
import com.lion.plugin.sdkplugin.sdk.network.HttpProxy;
import com.lion.plugin.sdkplugin.sdk.precenter.UserModel;
import com.lion.plugin.sdkplugin.sdk.uitils.DeviceUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.GlobalUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.SharedPreferencesUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eleven
 * on 2016/12/18 下午5:49.
 */

public class UserCenterManager {

    private static final String TAG = UserCenterManager.class.getName();

    //获取礼包列表
    public static void requestGiftList(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.gifts");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());

        HttpProxy.post(params, GiftListResult.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

    //领取礼包
    public static void requestGetGiftCode(String giftId, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.get_gift");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("id", giftId);

        HttpProxy.post(params, GiftCodeEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }
    //获得我的礼包
    public static void requestGetMyGiftCode(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "user.get_my_gifts");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());

        HttpProxy.post(params, MyGiftResult.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }
    //获取活动接口
    public static void requestActivityList(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.get_discount");
        params.put("game_id", Config.getGameId());
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");

        HttpProxy.post(params, ActivityListResult.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

    //获取用户信息
    public static void requestGetUserInfo(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.info");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());

        HttpProxy.post(params, User.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }


    //获取客服信息
    public static void requestGetCsInfo(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.get_custom_service");

        HttpProxy.post(params, CsInfoResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

//    //获取消息列表
//    public static void requestGetMsgList(final HttpRequestCallback callback) {
//        Map<String, String> params = new HashMap<>();
//        params.put("action", "system.get_msg");
//        params.put("sid", UserModel.getInstance().getUser().getSid());
//        params.put("token",UserModel.getInstance().getToken());
//        params.put("game_id", Config.getGameId());
//
//        HttpProxy.post(params, MessageListResult.class, new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                callback.onSuccess(data);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                callback.onFail(msg);
//            }
//        });
//    }
//
//    //3.2.0 版本获取消息列表
//    public static void requestGetMsgListNew(final HttpRequestCallback callback) {
//        Map<String, String> params = new HashMap<>();
//        params.put("action", "user.get_msg");
//        params.put("token",UserModel.getInstance().getToken());
//        params.put("game_id", Config.getGameId());
//
//        HttpProxy.post(params, MessageListResult.class, new HttpRequestCallback() {
//            @Override
//            public void onSuccess(Object data) {
//                callback.onSuccess(data);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                callback.onFail(msg);
//            }
//        });
//    }
    //3.7.1 版本获取新闻、消息列表
    public static void requestGetNewsList(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "game.news_list");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
     //   params.put("cate_id", id);
        params.put("offset", "0");
        params.put("limitsize", "99");

        HttpProxy.post(params, NewMessageListResult.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }
    //更新公告消息的阅读状态
    public static void requestUpdateMessageReadStatus(String msgId, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.read_msg");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("msg_id", msgId);
        params.put("game_id", Config.getGameId());

        HttpProxy.post(params, OperationStatusEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }


    //检查是否需要弹出实名认证窗口
    public static void getIdCardInfo(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.get_idcard_info");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        GlobalUtil.testParams(params);
        HttpProxy.post(params, GetIdCardInfoEty.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }


    //绑定身份证信息
    public static void bindIdCardInfo(String name, String idNumber, String phone, String verifyCode, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.bindIdCardInfo");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        if (!TextUtils.isEmpty(phone))
            params.put("phoneNumber", phone);
        if (!TextUtils.isEmpty(verifyCode))
            params.put("verifyCode", verifyCode);
        params.put("name", name);
        params.put("idNo", idNumber);

        HttpProxy.post(params, BindIdCardResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                ULogUtil.w(TAG,"[onFail].... msg :" + msg);
                callback.onFail(msg);
            }
        });
    }

    //获取vip礼包数量
    public static void requestVipGiftList(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.vip_gifts");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());

        HttpProxy.post(params, VipGiftListResult.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }


    //更新礼包的更新时间
    public static void updateGiftListReadStatus() {
        final String curMills = String.valueOf(System.currentTimeMillis());
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.getAllTipsCount");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        params.put("last_time_gift", curMills);
        params.put("last_time_message", SharedPreferencesUtil.getString(SPConstants.GM_GET_MESSAGE_LIST_LAST_TIME, ""));

        HttpProxy.post(params, CountTipsEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                SharedPreferencesUtil.saveString(SPConstants.GM_GET_GIFT_LIST_LAST_TIME, curMills);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
            }
        });
    }
    //获取个人中心界面是否有新消息
    public static void requestNewStatus(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.remind");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());

        HttpProxy.post(params, RequestNewResult.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

    //更新消息的更新时间
    public static void updateMessageListReadStatus() {
        final String curMills = String.valueOf(System.currentTimeMillis());
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.getAllTipsCount");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        params.put("last_time_gift", SharedPreferencesUtil.getString(SPConstants.GM_GET_GIFT_LIST_LAST_TIME, ""));
        params.put("last_time_message", curMills);

        HttpProxy.post(params, CountTipsEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                SharedPreferencesUtil.saveString(SPConstants.GM_GET_MESSAGE_LIST_LAST_TIME, curMills);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
            }
        });
    }

    //刷新红点信息
    public static void requestGetDotsInfo(final HttpRequestCallback callback) {
        final String curMills = String.valueOf(System.currentTimeMillis());
        String giftLastTime = SharedPreferencesUtil.getString(SPConstants.GM_GET_GIFT_LIST_LAST_TIME, "");
        String messageLastTime = SharedPreferencesUtil.getString(SPConstants.GM_GET_MESSAGE_LIST_LAST_TIME, "");
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.getAllTipsCount");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        params.put("last_time_gift", TextUtils.isEmpty(giftLastTime) ? curMills : giftLastTime);
        params.put("last_time_message", TextUtils.isEmpty(messageLastTime) ? curMills : messageLastTime);

        HttpProxy.post(params, CountTipsEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }

    //获取各种tips信息
    public static void requestGetTipsInfo(final HttpRequestCallback callback) {
        final String curMills = String.valueOf(System.currentTimeMillis());
        String giftLastTime = SharedPreferencesUtil.getString(SPConstants.GM_GET_GIFT_LIST_LAST_TIME, "");
        String messageLastTime = SharedPreferencesUtil.getString(SPConstants.GM_GET_MESSAGE_LIST_LAST_TIME, "");
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.getAllTipsCount");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        params.put("last_time_gift", TextUtils.isEmpty(giftLastTime) ? curMills : giftLastTime);
        params.put("last_time_message", TextUtils.isEmpty(messageLastTime) ? curMills : messageLastTime);

        HttpProxy.post(params, CountTipsEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);

                if (TextUtils.isEmpty(SharedPreferencesUtil.getString(SPConstants.GM_GET_GIFT_LIST_LAST_TIME, "")))
                    SharedPreferencesUtil.saveString(SPConstants.GM_GET_GIFT_LIST_LAST_TIME, curMills);

                if (TextUtils.isEmpty(SharedPreferencesUtil.getString(SPConstants.GM_GET_MESSAGE_LIST_LAST_TIME, "")))
                    SharedPreferencesUtil.saveString(SPConstants.GM_GET_MESSAGE_LIST_LAST_TIME, curMills);

                GlobalUtil.saveAllItemCounts((CountTipsEntity) data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }

    //获取vip界面的数据
    public static void requestVipInfo(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.vip_info");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());

        ULogUtil.e("requestVipInfo",params.toString());
        HttpProxy.post(params, VipInfoEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {

                ULogUtil.e("requestVipInfo",data.toString());
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                callback.onFail(msg);
            }
        });
    }

    //获取平台福利信息
    public static void requestPlatformGiftInfo(final boolean isForList, final HttpRequestCallback callback) {
        final String mills = String.valueOf(System.currentTimeMillis());
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.get_platform_send_coupon");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        params.put("reset", isForList ? "1" : "0");
        params.put("lasttime", SharedPreferencesUtil.getString(SPConstants.GM_GET_SEND_COUPON_LAST_TIME, ""));

        HttpProxy.post(params, PlatformGiftListResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                PlatformGiftListResultEntity entity = (PlatformGiftListResultEntity) data;
                if ((entity.coupon_list != null && entity.coupon_list.size() > 0)
                        || (entity.gift_list != null && entity.gift_list.size() > 0)) {
                    SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_PLATFORM_GIFT_OR_COUPON, true);
                } else
                    SharedPreferencesUtil.saveBoolean(SPConstants.GM_USER_HAS_NEW_PLATFORM_GIFT_OR_COUPON, false);
                callback.onSuccess(data);
                if (isForList)
                    SharedPreferencesUtil.saveString(SPConstants.GM_GET_SEND_COUPON_LAST_TIME,
                            mills);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }

    //更新用户的角色信息
    public static void requestUpdateUserLevelInfo(int level, final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "user.update_role_level");
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());

        HttpProxy.post(params, Object.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }
        });
    }


    //页面log
    public static void logPage(String pageName,String enterOrLeave) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.user_log_page");
        params.put("guid", DeviceUtil.getGuid());
        params.put("game_id", Config.getGameId());
        params.put("device_name", DeviceUtil.getDeviceModelName());
        params.put("device_manufacturer", DeviceUtil.getDeviceManufacturerName());
        params.put("system_version", DeviceUtil.getAppVersionName());
        params.put("type", "page");
        params.put("promote", Config.getPromote());
        params.put("page_name", pageName);
        params.put("version", Config.getSDKVersion());
        params.put("page_action", enterOrLeave);
        params.put("os", "android");

        HttpProxy.post(params, Object.class, new HttpRequestCallback() {
        });
    }

    //点击事件log
    public static void logClickEvent(String event) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.user_log_page");
        params.put("guid", DeviceUtil.getGuid());
        params.put("game_id", Config.getGameId());
        params.put("device_name", DeviceUtil.getDeviceModelName());
        params.put("device_manufacturer", DeviceUtil.getDeviceManufacturerName());
        params.put("system_version", DeviceUtil.getAppVersionName());
        params.put("type", "click");
        params.put("promote", Config.getPromote());
        params.put("page_name", event);
        params.put("version", Config.getSDKVersion());
        params.put("os", "android");
        HttpProxy.post(params, Object.class, new HttpRequestCallback() {
        });
    }
    //banner事件log
    public static void logBannerEvent(String pageName, String noteJs, int type) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.user_log_page");
        params.put("guid", DeviceUtil.getGuid());
        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
        params.put("game_id", Config.getGameId());
        params.put("device_name", DeviceUtil.getDeviceModelName());
        params.put("device_manufacturer", DeviceUtil.getDeviceManufacturerName());
        params.put("system_version", DeviceUtil.getAppVersionName());
        if (type == 1) {
            params.put("type", "click");
        }
        if (type == 2) {
            params.put("type", "view");
        }
        params.put("note", noteJs);
        params.put("promote", Config.getPromote());
        params.put("page_name", pageName);
        params.put("version", Config.getSDKVersion());
        params.put("page_action", "0");
        params.put("os", "android");

        HttpProxy.post(params, Object.class, new HttpRequestCallback() {
        });
    }

    //广告事件log
    public static void logAdClickEvent(String pageName) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.user_log_page");
        params.put("guid", DeviceUtil.getGuid());
        params.put("game_id", Config.getGameId());
        params.put("device_name", DeviceUtil.getDeviceModelName());
        params.put("device_manufacturer", DeviceUtil.getDeviceManufacturerName());
        params.put("system_version", DeviceUtil.getAppVersionName());
        params.put("type", "click");
        params.put("promote", Config.getPromote());
        params.put("page_name", pageName);
        params.put("version", Config.getSDKVersion());
        params.put("page_action", "0");
        params.put("os", "android");

        HttpProxy.post(params, Object.class, new HttpRequestCallback() {
        });
    }

    //激活游戏
    public static void activateGame(String activate) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "system.user_log_page");
        params.put("guid", DeviceUtil.getGuid());
        params.put("game_id", Config.getGameId());
        params.put("device_name", DeviceUtil.getDeviceModelName());
        params.put("device_manufacturer", DeviceUtil.getDeviceManufacturerName());
        params.put("system_version", DeviceUtil.getAppVersionName());
        params.put("type", "activate");
        params.put("promote", Config.getPromote());
        params.put("version", Config.getSDKVersion());
        params.put("page_name", activate);
        params.put("os", "android");
        HttpProxy.post(params, Object.class, new HttpRequestCallback() {
        });
        ULogUtil.d(TAG,"[activateGame] ...");
    }

//    //获取该设备最后一次登录的游戏账号
//    public static void getLastFastLogin(final HttpRequestCallback callback){
//        Map<String,String> params = new HashMap<>();
//        params.put("action","user.get_lastfastlogin");
//        params.put("game_id",Config.getGameId());
//        params.put("device_no",GlobalUtil.getIMEI(Platform.getInstance().getContext()));
//
//        HttpProxy.post(params, LastFastLogin.class,new HttpRequestCallback(){
//            @Override
//            public void onSuccess(Object data) {
//                super.onSuccess(data);
//                callback.onSuccess(data);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                callback.onFail(msg);
//            }
//        });
//    }
//
//    //获取前端配置
//    public static void getLocalConfig(){
//        Map<String,String> params = new HashMap<>();
//        params.put("action","game.local_config");
//        params.put("id", Config.getGameId());
//        params.put("device_no", GlobalUtil.getIMEI(Platform.getInstance().getContext()));
//
//        HttpProxy.post(Config.getApiHost(),params, null, new HttpRequestCallback(){
//            @Override
//            public void onSuccess(Object data) {
//                ULogUtil.e(TAG+"getLocalConfig",data.toString());
//                try {
//                    JSONObject jsonObject = new JSONObject(data.toString());
//                    if (jsonObject.has("local_config")){
//                        JSONObject info = new JSONObject(jsonObject.getString("local_config"));
//                        if (info.has("fast_login") && info.getString("fast_login").equals("0")){
//                            ConfigManager.getInstance().setUseFastLogin(false);
//                        }
//                        if (info.has("jrtt_pay") && info.getString("jrtt_pay").equals("1")){
//                            ConfigManager.getInstance().setJrttPay(true);
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//            }
//        });
//    }
//
//    //获取用户实名信息
//    public static void getUserRealNameInfo(final HttpRequestCallback callback){
//        Map<String,String> params = new HashMap<>();
//        params.put("action","user.get_state");
//        params.put("game_id", Config.getGameId());
//        params.put("token", UserModel.getInstance().isLogin() ? UserModel.getInstance().getUser().getToken() : "");
//        params.put("device_no", GlobalUtil.getIMEI(Platform.getInstance().getContext()));
//
//        HttpProxy.post(params, RealNameInfoEntity.class,new HttpRequestCallback(){
//            @Override
//            public void onSuccess(Object data) {
//                RealNameInfoEntity realNameInfoEntity = (RealNameInfoEntity) data;
//                ULogUtil.e(TAG,"getUserRealNameInfo -->   " + realNameInfoEntity.toString());
//                ConfigManager.getInstance().setRealNameInfoEntity(realNameInfoEntity);
//                if (realNameInfoEntity.isRealname()){
//                    if (Integer.valueOf(realNameInfoEntity.getAge())<8){
//                        CallbackManager.getRegistRealNameCallback().registRealNameSuccChild();
//                    }else if (Integer.valueOf(realNameInfoEntity.getAge())>=8 &&Integer.valueOf(realNameInfoEntity.getAge())<18){
//
//                        CallbackManager.getRegistRealNameCallback().registRealNameSuccMinor();
//                    }else if (Integer.valueOf(realNameInfoEntity.getAge())>=18){
//
//                        CallbackManager.getRegistRealNameCallback().registRealNameSuccAudlt();
//                    }
//                }else {
//                    CallbackManager.getRegistRealNameCallback().registRealNameFailed();
//                }
//                if (callback !=null){
//                    callback.onSuccess(data);
//                }
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                CallbackManager.getRegistRealNameCallback().registRealNameFailed();
//                if (callback != null){
//                    callback.onFail(msg);
//                }
//            }
//
//            @Override
//            public void onError(String msg) {
//                super.onError(msg);
//                CallbackManager.getRegistRealNameCallback().registRealNameFailed();
//                if (callback != null){
//                    callback.onError(msg);
//                }
//            }
//        });
//    }
//    //获取广告配置
//    public static void getAdSettingInfo(final HttpRequestCallback callback){
//        Map<String,String> params = new HashMap<>();
//        params.put("action","system.get_settings");
//        params.put("game_id",Config.getGameId());
//        params.put("device_no",GlobalUtil.getIMEI(Platform.getInstance().getContext()));
//
//        HttpProxy.post(params, AdInfoEntity.class,new HttpRequestCallback(){
//            @Override
//            public void onSuccess(Object data) {
//                AdInfoEntity adInfoEntity = (AdInfoEntity) data;
//                ConfigManager.getInstance().setAdInfoEntity(adInfoEntity);
////                ULogUtil.d(TAG+"getAdSettingInfo",adInfoEntity.toString());
//                if (callback !=null){
//                    callback.onSuccess(data);
//                }
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                if (callback != null){
//                    callback.onFail(msg);
//                }
//            }
//
//            @Override
//            public void onError(String msg) {
//                super.onError(msg);
//                if (callback != null){
//                    callback.onError(msg);
//                }
//            }
//        });
//    }

}
