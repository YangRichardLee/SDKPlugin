package com.lion.plugin.sdkplugin.sdk.modle;

/**
 * vip界面的qq相关配置实体类
 * Created by eleven
 * on 2017/10/21 上午11:20.
 */

public class CustomerInfoEntity {
    public String qq_number;
    public String qq_group_number;
    public String qq_group_key;
    public String work_time;

    @Override
    public String toString() {
        return "CustomerInfoEntity{" +
                "qq_number='" + qq_number + '\'' +
                ", qq_group_number='" + qq_group_number + '\'' +
                ", qq_group_key='" + qq_group_key + '\'' +
                ", work_time='" + work_time + '\'' +
                '}';
    }
}
