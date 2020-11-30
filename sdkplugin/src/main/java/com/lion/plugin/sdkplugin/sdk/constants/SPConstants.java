package com.lion.plugin.sdkplugin.sdk.constants;

/**
 * 使用的sp中的名称定义
 * Created by eleven
 * on 2016/12/20 下午1:53.
 */

public class SPConstants {

    public final static String GM_USER_CENTER_GIFT_COUNT = "gm_user_center_gift_count";
    public final static String GM_USER_CENTER_GIFT_NEW_TIPS_HAS_READ = "gm_user_center_gift_new_tips_has_read";

    public final static String GM_USER_CENTER_MESSAGE_COUNT = "gm_user_center_message_count";
    public final static String GM_USER_CENTER_MESSAGE_NEW_TIPS_HAS_READ = "gm_user_center_message_new_tips_has_read";

    public final static String GM_USER_CENTER_ACTIVITY_COUNT = "gm_user_center_activity_count";
    public final static String GM_USER_CENTER_ACTIVITY_NEW_TIPS_HAS_READ = "gm_user_center_activity_new_tips_has_read";
    public final static String GM_USER_LOGIN_TIME = "gm_user_login_time";
    public final static String GM_USER_READ_BANNER = "gm_user_read_banner";
    public final static String GM_USER_FIRST_FAST_LOGIN = "gm_user_first_fast_login";
    //记录此SDK需要哪些登陆入口
    public final static String GM_SDK_NEED_LOGIN_TYPES = "gm_sdk_need_login_types";


    //用户相关
    public final static String GM_USER_ONLYID_PESUDOUNIQUEID = "gm_user_onlyid_pesudouniqueid";
    public final static String GM_USER_ONLYID_PESUDOUNIQUEID_FILE = "/.gmuseronlyid/.pesudouniqueid";

    public final static String GM_USER_SID = "gm_user_sid";
    public final static String GM_USER_UID = "gm_user_uid";
    public final static String GM_USER_PHONE = "gm_user_phone";
    public final static String GM_USER_NICK_NAME = "gm_user_nick_name";
    public final static String GM_USER_FACE = "gm_user_face";
    public final static String GM_USER_NEED_BIND = "gm_user_need_bind";
    public final static String GM_USER_COINS = "gm_user_coins";
    public final static String GM_USER_PROMOTE_COIN = "gm_user_promote_coin";
    public final static String GM_USER_PAY_COUPON_COUNT = "gm_user_pay_coupon_count";
    public final static String GM_USER_TOKEN = "gm_user_token";
    public static final String GM_USER_PASSWORD = "gm_user_password";
    public static final String GM_USER_HAS_NEW_MESSAGE = "gm_user_has_new_message";
    public static final String GM_USER_HAS_NEW_GIFT = "gm_user_has_new_gift";
    public static final String GM_USER_HAS_NEW_KF_MSG = "gm_user_has_new_kf_msg";
    public static final String GM_USER_HAS_NEW_ACTIVITY = "gm_user_has_new_activity";
    public static final String GM_USER_NEED_BIND_ID_CARD_INFO = "gm_user_need_bind_id_card_info";
    public static final String GM_USER_ID_CARD_NAME = "gm_user_id_card_name";
    public static final String GM_USER_ID_CARD_NO = "gm_user_id_card_no";
    public static final String GM_USER_LOGIN_NAME_TO_FORGET_PWD = "gm_user_login_name_to_forget_pwd";


    public static final String GM_IS_LOGOUT_BY_USER = "gm_is_logout_by_user";
    public static final String GM_USER_PAY_COUPON_COUNT_ALL = "gm_user_pay_coupon_count_all";
    public static final String GM_USER_USER_POINT = "gm_user_user_point";
    public static final String GM_USER_HAS_DISCOUNT = "gm_user_has_discount";

    public static final String GM_IS_FIRST_LOGIN = "gm_is_first_login";
    public static final String GM_IS_NEED_AUTO_LOGIN = "gm_is_need_auto_login";
    public static final String GM_SELNUM_GO_TO_NORMOL_LOGIN_VIEW = "gm_selnum_go_to_normol_login_view";
    public static final String GM_LAST_FAST_LOGIN_NUMBER = "gm_last_fast_login_number";
    public static final String GM_CHANGE_USER_TO_LOGIN = "gm_change_user_to_login";
    public static final String GM_CHANGE_USER_TO_LOGIN_SPECIAL = "gm_change_user_to_login_special";
    public static final String GM_CHANGE_USER_TO_LOGIN_FAILED = "gm_change_user_to_login_failed";
    public static final String GM_CHANGE_USER_TO_LOGIN_OTHER = "gm_change_user_to_login_other";
    public static final String GM_FAST_LOGIN_NUMBER = "gm_fast_login_number";

    //是否已经初始化过SDK
    public static final String GM_IS_FIRST_TO_INITIALIZE = "gm_is_first_to_initialize";

    //用户是否已经弹出过实名认证的View
    public static final String GM_USER_HAS_PROMPT_ID_BIND_VIEW = "gm_user_has_prompt_id_bind_view";
    //上一次用户的Id绑定类型
    public static final String GM_USER_LAST_ID_BIND_TYPE = "gm_user_last_id_bind_type";
    public static final String DEVICE_UNIQUE_ID = "device_unique_id";

    public static final String GM_HAS_READ_ACTIVITY_LIST = "gm_has_read_activity_list";
    public static final String GM_USER_REGISTER_TYPE = "gm_user_register_type";
    public static final String GM_USER_CENTER_DISPLAY_TIME = "gm_user_center_display_time";
    public static final String GM_LOGIN_UID_GUAIMAO = "gm_login_uid_guaimao";

    //各种数字统计
    public static final String GM_WAIT_TO_GET_VIP_GIFT = "gm_wait_to_get_vip_gift";         //待领取的vip礼包数
    public static final String GM_HAS_WAIT_TO_GET_COUPON = "gm_has_wait_to_get_coupon";     //待领取的优惠券数
    public static final String GM_INVALID_COUPON_COUNT = "gm_invalid_coupon_count";         //即将过期的优惠券数
    public static final String GM_WAIT_TO_GET_UPGRADE_VIP_GIFT = "gm_wait_to_get_upgrade_vip_gift"; //待领取的vip升级礼包数
    public static final String GM_WAIT_TO_GET_WEEK_VIP_GIFT = "gm_wait_to_get_week_vip_gift";       //待领取的vip周礼包数
    public static final String GM_UNREAD_MSG_COUNT = "gm_unread_msg_count";                         //未读消息数量
    public static final String GM_AVALID_COUPON_COUNT = "gm_avalid_coupon_count";           //可用的优惠券数量
    public static final String GM_USER_UNREAD_ACTIVITY_IDS = "gm_user_unread_activity_ids";         //未读的activityId
    public static final String GM_GET_SEND_COUPON_LAST_TIME = "gm_get_send_coupon_last_time";
    public static final String GM_USER_HAS_NEW_PLATFORM_GIFT_OR_COUPON = "gm_user_has_new_platform_gift_or_coupon";
    public static final String GM_CURRENT_FLOAT_TIPS_TYPE = "gm_current_float_tips_type";

    public static final String GM_USER_HAS_ENTER_GIFT_SECOND_PAGE = "gm_user_has_enter_gift_second_page";   //是否进入过礼包的二级页
    public static final String GM_GET_ALL_COUNT_TIPS_LAST_TIME = "gm_get_all_count_tips_last_time";         //上次获取各种统计的时间
    public static final String GM_GET_GIFT_LIST_LAST_TIME = "gm_get_gift_list_last_time";
    public static final String GM_GET_MESSAGE_LIST_LAST_TIME = "gm_get_message_list_last_time";
    public static final String GM_USER_MESSAGE_HAS_UPDATE = "gm_user_message_has_update";
    public static final String GM_USER_KF_HAS_UPDATE = "gm_user_message_has_update";
    public static final String GM_USER_KF_MESSAGE_NUMBER = "gm_user_kf_message_number";
    public static final String GM_USER_GIFT_HAS_UPDATE = "gm_user_gift_has_update";
    public static final String GM_USER_LAST_LOGIN_TYPE = "gm_user_last_login_type";         //注册类型
    public static final String GM_USER_VIP_LEVEL = "gm_user_vip_level";
    public static final String GM_USER_IS_NEW_USER = "gm_user_is_new_user";
    public static final String GM_USER_NEW_USER_ACCOUNT = "gm_user_new_user_account";
    public static final String GM_USER_NEW_USER_PASSWORD = "gm_user_new_user_password";
    public static final String GM_DEVICE_UNIQUE_0LD_ID = "gm_device_unique_0ld_id";
    public static final String GM_USER_LAST_LOGIN_MOBILE = "gm_user_last_login_mobile";
    //用户是否进入过礼包界面
    public static final String GM_USER_HAS_ENTER_GIFT_LIST_VIEW = "gm_user_has_enter_gift_list_view";
    public static final String GM_USER_HAS_ENTER_MESSAGE_LIST_FRAGMENT = "gm_user_has_enter_message_list_fragment";
    public static final String GM_USER_HAS_ENTER_KF_FRAGMENT = "gm_user_has_enter_kf_fragment";
    public static final String GM_USER_CURRENT_HAS_NEW_PLATFORM_GIFT = "gm_user_current_has_new_platform_gift";
}
