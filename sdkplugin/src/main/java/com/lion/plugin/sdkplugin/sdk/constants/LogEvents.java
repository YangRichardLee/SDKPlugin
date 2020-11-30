package com.lion.plugin.sdkplugin.sdk.constants;

/**
 * Created by eleven
 * on 2017/10/17 上午9:45.
 */

public class LogEvents {

    //一键登录界面
    public static final String ONE_KEY_PLAY_BUTTON_CLICK = "one_key_play_button_click";             //一键登录按钮点击
    public static final String HAS_ACCOUNT_BUTTON_CLICK = "has_account_button_click";               //已有账号按钮点击
    public static final String CREATE_ACCOUNT_BUTTON_CLICK = "create_account_button_click";         //创建账号按钮点击

    //登录界面
    public static final String REGISTER_BUTTON_CLICK = "register_button_click";                     //注册按钮点击
    public static final String LOGIN_BUTTON_CLICK = "login_button_click";                           //登录按钮点击
    public static final String FORGET_PASSWORD_BUTTON_CLICK = "forget_password_button_click";       //忘记密码按钮点击
    public static final String FAST_LOGIN_BUTTON_CLICK = "fast_login_button_click";                 //快速登录按钮点击
    public static final String FAST_LOGIN_INTO_BUTTON_CLICK = "fast_login_yes_button_click";                 //快速登录按钮点击
    public static final String FAST_LOGIN_FAIL_BUTTON_CLICK = "fast_login_no_button_click";                 //快速登录按钮点击
    public static final String KF_BUTTON_CLICK_NORMAL = "kf_button_click_normal";                 //客服按钮点击（正常登录界面）
    public static final String DROP_DOWN_BUTTON_CLICK_NORMAL = "drop_down_button_click_normal";    //账户下拉列表按钮点击
    public static final String DROP_DOWN_BUTTON_CLICK_NORMAL_DELETE = "drop_down_button_click_normal_delete";    //账户下拉列表删除账号按钮点击
    public static final String PASSWORD_CODE_BUTTON_CLICK = "password_code_button_click";    //密码or验证码切换按钮点击
    public static final String GET_CODE_BUTTON_CLICK_NORMAL = "get_code_button_click_normal";    //获取（正常登录界面）
    public static final String OTHER_WAYS_LOGIN_BUTTON_CLICK = "other_ways_login_button_click";    //其他登录方式按钮点击（正常登录界面）


    //切换账号页

    public static final String KF_BUTTON_CLICK_SWITCH_ACCOUNTS = "kf_button_click_switch_accounts";    //客服按钮点击（切换账号界面）
    public static final String DROP_DOWN_BUTTON_CLICK_SWITCH_ACCOUNTS = "drop_down_button_click_switch_accounts";    //下拉展开按钮点击（切换账号界面）
    public static final String DROP_DOWN_BUTTON_CLICK_SWITCH_ACCOUNTS_DELETE = "drop_down_button_click_switch_accounts_delete";    //删除账号按钮点击（切换账号界面）
    public static final String LOGIN_BUTTON_CLICK_SWITCH_ACCOUNTS = "login_button_click_switch_accounts";    //登录按钮点击（切换账号界面）
    public static final String ADD_BUTTON_CLICK_SWITCH_ACCOUNTS = "add_button_click_switch_accounts";    //添加账号按钮点击（切换账号界面）

    //其他登录页
    public static final String KF_BUTTON_CLICK_OTHER_WAYS_LOGIN = "kf_button_click_other_ways_login";    //客服按钮点击（其他登录页）
    public static final String QQ_LOGIN_BUTTON_CLICK = "qq_login_button_click";           //QQ按钮点击（其他登录页）
    public static final String WEIBO_LOGIN_BUTTON_CLICK = "weibo_login_button_click";     //微博按钮点击（其他登录页）
    public static final String SMS_LOGIN_BUTTON_CLICK = "sms_login_button_click";         //一键登录按钮点击（其他登录页）

    //忘记密码界面
    public static final String GET_CODE_BUTTON_CLICK_FORGET_PWD = "get_code_button_click_forget_pwd";  //获取（重置密码界面）
    public static final String SAVE_AND_LOGIN_FORGET_PWD = "save_and_login_forget_pwd";  //完成设置并登录（重置密码界面）

    //登录事件
    public static final String CODE_LOGIN_SUCCESS = "code_login_success";        //验证码登录成功
    public static final String CODE_LOGIN_FAIL = "code_login_fail";        //验证码登录失败
    public static final String PASSWORD_LOGIN_SUCCESS = "password_login_success";        //密码登录成功
    public static final String PASSWORD_LOGIN_FAIL = "password_login_fail";        //密码登录失败
    public static final String FAST_LOGIN_SUCCESS = "fast_login_success";        //快速登录成功
    public static final String FAST_LOGIN_FAIL = "fast_login_fail";        //快速登录失败
    public static final String QQ_LOGIN_SUCCESS = "qq_login_success";        //QQ登录成功
    public static final String QQ_LOGIN_FAIL = "qq_login_fail";        //QQ登录失败
    public static final String WEIBO_LOGIN_SUCCESS = "weibo_login_success";        //微博登录成功
    public static final String WEIBO_LOGIN_FAIL = "weibo_login_fail";        //微博登录失败
    public static final String ONE_KEY_LOGIN_SUCCESS = "one_key_login_success";        //一键登录成功
    public static final String ONE_KEY_LOGIN_FAIL = "one_key_login_fail";        //一键登录失败

    //页面停留
    public static final String DEVICE_ACTIVATED = "device_activated";   //设备激活
    public static final String OUTOMATIC_LOGIN_VIEW = "outomatic_login_view";   //自动登录界面
    public static final String CARD_BIND_VIEW = "card_bind_view";   //身份证绑定提示界面
    public static final String NORMAL_LOGIN_VIEW = "normal_login_view";   //正常登录界面
    public static final String SWITCH_ACCOUNTS_VIEW = "switch_accounts_view";   //切换账号界面
    public static final String OTHER_WAYS_LOGIN_VIEW = "other_ways_login_view";   //其他登录方式界面
    public static final String FORGET_PASSWROD_VIEW = "forget_passwrod_view";   //重置密码界面
    public static final String KF_NORMAL_VIEW = "kf_normal_view";   //客服界面（正常登录界面）
    public static final String KF_SWITCH_ACCOUNTS_VIEW = "kf_switch_accounts_view";   //客服界面（切换账号界面）
    public static final String KF_OTHER_WAYS_LOGIN_VIEW = "kf_other_ways_login_view";   //客服界面（其他登录方式界面）
    public static final String KF_FORGET_PASSWORD_VIEW = "kf_forget_password_view";   //客服界面（重置密码页）

    //页面事件
    public static final String PAGE_EVENT_ACCOUNT_FRAGMENT = "AccountFragment";                     //个人中心账号界面
    public static final String PAGE_EVENT_ACTIVITY_DETAIL_FRAGMENT = "ActivityDetailFragment";      //活动详情界面
    public static final String PAGE_EVENT_BIND_ID_FRAGMENT = "BindIdCardInfoFragment";              //绑定身份证界面，带手机号输入的
    public static final String PAGE_EVENT_BIND_ID_WITHOUT_PHONE_FRAGMENT = "BindIdCardInfoWithoutPhoneFragment";              //绑定身份证界面，不带手机号输入的

    public static final String PAGE_EVENT_FORGET_PASSWORD = "FORGET_PASSWORD_PAGE";                 //忘记密码界面（和注册页面是同一个）
    public static final String PAGE_EVENT_REGISTER = "REGISTER_PAGE";                      //注册界面

    public static final String PAGE_EVENT_ENTER = "0";
    public static final String PAGE_EVENT_LEAVE = "1";

    //个人中心界面
    public static final String AVATAR_BUTTON_CLICK = "avatar_button_click";             //头像点击
    public static final String COUPON_BUTTON_CLICK = "coupon_button_click";             //优惠券
    public static final String HIDDEN_BUTTON_CLICK = "hidden_button_click";             //隐藏点击
    public static final String GIFT_BUTTON_CLICK = "gift_button_click";                 //礼包点击
    public static final String ACTIVITY_BUTTON_CLICK = "activity_button_click";         //活动点击
    public static final String VIP_BUTTON_CLICK = "vip_button_click";                   //VIP点击
    public static final String ACCOUNT_BUTTON_CLICK = "account_button_click";           //账号点击
    public static final String CUSTOMER_SERVICE_BUTTON_CLICK = "customer_service_button_click";     //客服点击
    public static final String MESSAGE_BUTTON_CLICK = "message_button_click";           //消息点击
    public static final String DOWNLOAD_BUTTON_CLICK = "download_button_click";         //下载按钮点击
    public static final String CHANGE_ACCOUNT_CLICK = "change_account_click";          //切换账号按钮
    public static final String BIND_ID_BUTTON_CLICK = "bind_id_button_click";           //实名认证点击
    //客服界面
    public static final String HOT_LINE_BUTTON_CLICK = "hot_line_button_click";          //客服热线点击
    public static final String QQ_BUTTON_CLICK = "qq_button_click";                      //QQ客服点击
    public static final String QQ_GROUP_BUTTON_CLICK = "qq_group_button_click";          //QQ群点击
    public static final String SAVE_PIC_BUTTON_CLICK = "save_pic_button_click";          //保存二维码至相册按钮点击
}
