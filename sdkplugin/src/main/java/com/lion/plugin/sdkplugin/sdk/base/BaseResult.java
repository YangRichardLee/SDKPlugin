package com.lion.plugin.sdkplugin.sdk.base;

import java.io.Serializable;

/**
 * 服务器返回结果基类
 * Created by eleven
 * on 2016/12/19 下午3:04.
 */

public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public boolean status;
    public int errorno;
    public String errortext;
    public String firstNum;
    public T data;
    //1，2 警告封号
    public String event_type;
    public String active_time;
    public String event_data_title;
    public String event_data_message;
}
