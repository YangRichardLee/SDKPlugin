package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * 个人中心消息列表实体类
 * Created by eleven
 * on 2016/11/13 上午2:28.
 */

public class MessageItemEntity implements Serializable {
    public String msg_id;
    public String title;
    public String content;
    public String add_time;
    public boolean readed;          //是否已读
    public String type;            //0. 公告  1. 资讯 2新闻,， 3. 攻略， 4. 活动， 5. VIP 60. 初始密码： 61. 优惠券待领取：62. vip升级礼包待领取：63. VIP周礼包待领取,64.优惠券即将到期


    public String msg_link; //1. 资讯 2新闻,， 3. 攻略， 4. 活动， 5. VIP 显示为h5页面
}
