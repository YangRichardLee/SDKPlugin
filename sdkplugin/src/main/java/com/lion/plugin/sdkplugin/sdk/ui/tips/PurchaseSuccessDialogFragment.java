package com.lion.plugin.sdkplugin.sdk.ui.tips;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lion.plugin.sdkplugin.sdk.constants.BundleConstants;
import com.lion.plugin.sdkplugin.sdk.ui.BaseDialogFragment;

import static com.lion.plugin.sdkplugin.sdk.base.Config.getIdByName;
import static com.lion.plugin.sdkplugin.sdk.base.Config.getLayoutByName;

public class PurchaseSuccessDialogFragment extends BaseDialogFragment {

    public static final int VIEW_TYPE_OTHER = 1;
    public static final int VIEW_TYPE_COIN = 2;
    public static final int VIEW_TYPE_PROMOTE_POINT = 3;

    private TextView balanceTips_TV, unit_TV;
    private LinearLayout balanceContainer_LL;
    private PurchaseSuccessDialogListener listener;
    private int cur_type = -1;
    private String balance;

    public void setListener(PurchaseSuccessDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            cur_type = getArguments().getInt(BundleConstants.DATA_FOR_PURCHASE_SUCCESS_TYPE);
            balance = getArguments().getString(BundleConstants.DATA_FOR_PURCHASE_SUCCESS_BALANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setCancelable(true);
        getDialog().getWindow().setLayout(270, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = inflater.inflate(getLayoutByName("dialog_purchase_success_guaimao"), null, false);
        balanceTips_TV = (TextView) view.findViewById(getIdByName("tv_purchase_success_dialog_balance_guaimao"));
        unit_TV = (TextView) view.findViewById(getIdByName("tv_purchase_success_dialog_unit_guaimao"));
        balanceContainer_LL = (LinearLayout) view.findViewById(getIdByName("ll_purchase_success_dialog_balance_tips_guaimao"));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPriceTips();
    }

    private void setPriceTips() {
        unit_TV.setText(cur_type == VIEW_TYPE_COIN ? "猫币" : "点券");
        balanceContainer_LL.setVisibility(TextUtils.isEmpty(balance) ? View.INVISIBLE : View.VISIBLE);
        balanceTips_TV.setText(balance);
    }

    public interface PurchaseSuccessDialogListener {
        void onSuccessConfirm();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null)
            listener.onSuccessConfirm();
    }

}

