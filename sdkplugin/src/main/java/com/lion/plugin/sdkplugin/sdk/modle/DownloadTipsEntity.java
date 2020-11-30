package com.lion.plugin.sdkplugin.sdk.modle;

import java.io.Serializable;

/**
 * Created by eleven
 * on 2016/11/16 下午4:42.
 */

public class DownloadTipsEntity implements Serializable {
    public boolean hasUpdate;
    public boolean isForceUpdate;
    public String size;
    public String downloadUrl;
}
