package com.lion.plugin.pluginlibs;

public class AppStatus {
    public static final int INIT_SUCCESS = 100;
    public static final int INIT_FALIED = 101;
    public static final int GET_SERVERLIST = 150;
    public static final int LOGIN_SUCCESS = 200;
    public static final int LOGIN_FALIED = 201;
    public static final int LOGIN_CANCEL = 202;
    public static final int LOGOUT_SUCCESS = 300;
    public static final int LOGOUT_FALIED = 301;
    public static final int PAY_SUCCESS = 400;
    public static final int PAY_FALIED = 401;
    public static final int PAY_CANCEL = 402;
    public static final int PAY_ORDER = 403;
    public static final int GAME_EXIT = 500;
    public static final int ACTION_SHOW_QUIT_DIALOG = 501;
    public static final int REALNAME_CHECK = 609;
    public static final int ANTI_UNREGISTER = 600;
    public static final int ANTI_CHILD = 601;
    public static final int ANTI_MINOR = 602;
    public static final int ANTI_AUDLT = 603;
    public static final int REGIST_REALNAME_SUCC = 610;
    public static final int REGIST_REALNAME_FAILED = 611;
    public static final int ACTION_AD_SUCCESS = 701;
    public static final int ACTION_AD_FAILED = 702;
    public static final int ACTION_SHARE_SUCCESS = 703;
    public static final int ACTION_SHARE_FAILED = 704;
    public static final int ACTION_BINDED = 801;
    public static final int ACTION_BIND_SUCCESS = 802;
    public static final int ACTION_BIND_FAILED = 803;
    public static final int ACTION_SYS_SETTING = 901;
    public static final int _ROLEINFO_ENTERING = 1;
    public static final int _ROLEINFO_CREAT = 2;
    public static final int _ROLEINFO_UPGRADE = 3;
    public static final int _ROLEINFO_EXIT = 4;
    public static final int _ROLEINFO_COMPLETE_NOVICE_GUIDANCE = 5;

    public static final int FINISH_PLUGIN = 999;



    //内部逻辑
    public static final int ACTION_START_CHECK_VERSION = 1;//检查版本
    public static final int ACTION_START_CHECK_AD = 2;//检查是否有广告
    public static final int ACTION_START_LOGIN = 3;//开始执行登录
    public static final int ACTION_GET_USER_INFO_TIPS = 5;//统计用户待完成任务，如：待领取礼包、优惠券等等。
    public static final int ACTION_START_GET_MAIN_ICON = 6;//获取主界面 ICON 图标文字
    public static final int ACTION_GET_KEFU_INFO = 7;//获取客服列表信息详情
    public static final int ACTION_GET_USER_INFO_VIP_ETY = 8;//获取用户VIP权益
    public static final int ACTION_GET_USER_INFO_MSG_LIST = 9;//获取用户消息列表

    public static final int ACTION_NEED_UPDATE = 10;//需要更新
    public static final int ACTION_NEED_SHOW_AD = 11;//需要显示广告
    public static final int ACTION_GET_USERINFO_SUCCESS = 12;//获取用户信息成功

    public static final int ACTION_TO_LOGIN_OTHERS = 20;//跳转其他登录界面
    public static final int ACTION_TO_SERVICE = 21;//跳转客服界面
    public static final int ACTION_TO_LOGIN_FAST = 22;//跳转快速登录说明
    public static final int ACTION_TO_LOGIN_PROTOCOL = 23;//跳转服务协议
    public static final int ACTION_TO_LOGIN_RESET_PWD = 24;//跳转忘记密码页
    public static final int ACTION_TO_LOGIN_NORMAL = 25;//跳转正常登陆页
    public static final int ACTION_TO_VERIFIED = 26;//跳转实名制页
    public static final int ACTION_TO_COUPONS = 27;//跳转优惠券页
    public static final int ACTION_TO_PAY = 28;//跳转支付页
    public static final int ACTION_TO_LOGIN_ACCOUNT_INFO = 29;//跳转账户信息短暂显示页面
    public static final int ACTION_TO_BIND_PHONE = 52;//跳转绑定页
    public static final int ACTION_TO_SUBMIT_PAY_COIN = 53;//跳转 确认支付 页
    public static final int ACTION_TO_SHOW_PAY_SUCCESS = 54;//显示 成功页面
    public static final int ACTION_GO_BACK_COUPONS = 55;//back  coupon

    public static final int ACTION_BIND_PHONE_SUCC = 30;//绑定手机成功
    public static final int ACTION_BIND_PHONE_FAIL = 31;//绑定手机失败
    public static final int ACTION_BIND_IDCARD_SUCC = 32;//绑定身份证成功
    public static final int ACTION_BIND_IDCARD_FAIL = 33;//绑定身份证失败
    public static final int ACTION_GET_SMS_CODE_SUCC = 34;//获取验证码成功
    public static final int ACTION_GET_SMS_CODE_FAIL = 35;//获取验证码失败
    public static final int ACTION_HAS_AVAILABLE = 36;//设备已注册
    public static final int ACTION_HAS_NOT_AVAILABLE = 37;//设备未注册

    public static final int ACTION_GET_AVAILABLE_GIFTLIST = 41;//获取一用户礼包列表成功
    public static final int ACTION_GET_AVAILABLE_COUPONS = 42;//获取一用户优惠券列表成功
    public static final int ACTION_GET_PALTFORM_COUPON_SUCC = 43;//获取一平台礼遇礼包成功
    public static final int ACTION_GET_PALTFORM_COUPON_FAIL = 44;//获取一平台礼遇礼包失败
    public static final int ACTION_GET_VIP_WEEK_GIFTLIST = 45;//获取 一用户VIP AND WEEK 列表成功
    public static final int ACTION_GET_VIP_WEEK_GIFTLIST_FAILED = 48;//获取 一用户VIP AND WEEK 列表失败
    public static final int ACTION_GET_RECEIVEGIFT_SUCCESS = 46;//用户领取礼包列表成功
    public static final int ACTION_GET_RECEIVEGIFT_FAIL = 47;//用户领取礼包列表成功

    public static final int ACTION_GET_HEARTBEAT_EVENT = 51;//心跳接口返回封号或者警告

    public static final int ACTION_PUT_USER_INFO_SEND = 60;//上传用户信息
    public static final int ACTION_PUT_USER_INFO_FAIL = 61;//上传用户信息失败
    public static final int ACTION_PUT_USER_INFO_SUCC = 62;//上传用户信息成功
    public static final int ACTION_LOGIN_THIRD_SUCC = 63;//第三方登录成功
    public static final int ACTION_LOGIN_THIRD_FAIL = 64;//第三方登录失败


    public static final int ACTION_TO_DO_PAY = 70;//执行支付

    public static final int ACTION_HAS_NO_MSG_READ = 80;//执行支付


}
