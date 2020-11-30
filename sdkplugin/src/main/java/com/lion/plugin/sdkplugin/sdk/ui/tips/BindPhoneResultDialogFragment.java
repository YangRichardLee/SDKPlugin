package com.lion.plugin.sdkplugin.sdk.ui.tips;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.constants.BundleConstants;
import com.lion.plugin.sdkplugin.sdk.ui.BaseDialogFragment;

public class BindPhoneResultDialogFragment extends BaseDialogFragment {

    public static final int VIEW_TYPE_FOR_USER_CENTER_BIND = 0;
    public static final int VIEW_TYPE_FOR_PURCHASE_BIND = 1;

    private PurchaseSuccessDialogFragment.PurchaseSuccessDialogListener listener;
    private TextView tips_TV;
    private int cur_view_type = 0;

    public void setListener(PurchaseSuccessDialogFragment.PurchaseSuccessDialogListener listener) {
        this.listener = listener;
        cur_view_type = getArguments().getInt(BundleConstants.DATA_FOR_BIND_PHONE_DIALOG_TYPE, 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setCancelable(true);
        getDialog().getWindow().setLayout(270, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = inflater.inflate(getLayoutByName("dialog_bind_phone_result_guaimao"), null, false);
        tips_TV = (TextView) view.findViewById(getIdByName("tv_bind_phone_result_tips_gm"));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch (cur_view_type) {
            case VIEW_TYPE_FOR_USER_CENTER_BIND:
                tips_TV.setText("绑定成功");
                break;
            case VIEW_TYPE_FOR_PURCHASE_BIND:
                tips_TV.setText("认证成功");
                break;
        }
    }


    public int getLayoutByName(String layoutName) {
        return Config.getLayoutByName(layoutName);
    }

    public int getIdByName(String idName) {
        return Config.getIdByName(idName);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null)
            listener.onSuccessConfirm();
    }
}
