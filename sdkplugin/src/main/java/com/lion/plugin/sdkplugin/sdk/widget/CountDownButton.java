package com.lion.plugin.sdkplugin.sdk.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 发送验证码倒计时按钮
 * Created by eleven on 16/9/6.
 */
public class CountDownButton extends TextView implements View.OnClickListener {

    public final static String TAG = "CountDownButton";

    CountDownTimer timer;
    public static final int MILLIS_INFUTURE = 60000;
    public static final int COUNTDOWN_INTERVAL = 1000;


    public CountDownButton(Context context) {
        this(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        showBg(true);
        setOnClickListener(this);
    }


    public void toWaitRequest(long time) {
        showBg(false);
        timer = new CountDownTimer(time, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long l) {
                setText(l / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                showBg(true);
            }
        };
        timer.start();
    }

    @Override
    public void onClick(View view) {
        if (isEnabled()) {
            setEnabled(false);
            toWaitRequest(MILLIS_INFUTURE);
            showBg(false);
        }
    }

    public void showBg(boolean enable) {
        setEnabled(enable);
        if (enable)
            setText("获取验证码");
    }

}
