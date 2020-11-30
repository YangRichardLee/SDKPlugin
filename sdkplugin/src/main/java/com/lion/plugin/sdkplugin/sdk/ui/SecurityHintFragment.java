package com.lion.plugin.sdkplugin.sdk.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lion.plugin.sdkplugin.R;
import com.lion.plugin.sdkplugin.sdk.manager.LoginPresenter;


/**
 * Created by zhewang on 2020/11/3.
 */
public class SecurityHintFragment extends UserBaseFragment implements
        View.OnClickListener  {
    TextView confirm_TV, back_TV, hint_TV;
    private LoginPresenter loginPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_security_hint, null, false);
        confirm_TV = view.findViewById(R.id.tv_to_login);
        back_TV = view.findViewById(R.id.tv_back_to_phone_login);
        hint_TV = view.findViewById(R.id.tv_hint);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back_TV.setOnClickListener(this);
        confirm_TV.setOnClickListener(this);
        setData();
    }

    private void setData() {
        SpannableStringBuilder builder = new SpannableStringBuilder("        强烈建议您使用手机登录或进入游戏后绑定手机");
        ForegroundColorSpan span1 = new ForegroundColorSpan(Color.parseColor("#D9001B"));
        ForegroundColorSpan span2 = new ForegroundColorSpan(Color.parseColor("#D9001B"));
        builder.setSpan(span1, 12, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(span2, 20, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        hint_TV.setText(builder);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == back_TV.getId()) {
            onBack();
        }
        if (v.getId() == confirm_TV.getId()) {
            loginPresenter.fastLogin();
            return;
        }
    }
}
