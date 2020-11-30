package com.lion.plugin.sdkplugin.sdk.widget;


import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;



/**
 * Created by eleven
 * on 2016/11/12 下午6:02.
 */

public class GMLinearLayout extends LinearLayout {

    protected Context mContext;
    protected View contentView;

    public GMLinearLayout(Context context) {
        super(context);
        this.mContext = context;
        initViews();
    }


    public GMLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initViews();
    }

    protected void initViews() {
    }

}
