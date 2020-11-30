package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * Created by eleven
 * on 2017/1/4 下午1:49.
 */

public class OrderParamsEntity implements Serializable {

    public String appId;
    public String mhtOrderNo;
    public String mhtOrderName;
    public String mhtOrderType;
    public String mhtCurrencyType;
    public int    mhtOrderAmt;
    public String mhtOrderDetail;
    public String funcode;
    public String mhtOrderStartTime;
    public String notifyUrl;
    public String mhtCharset;
    public String payChannelType;
    public String mhtSignature;
    public String mhtSignType;
    public String deviceType;
    public String version;
    public String mhtOrderTimeOut;
    public String consumerCreateIp;
}
