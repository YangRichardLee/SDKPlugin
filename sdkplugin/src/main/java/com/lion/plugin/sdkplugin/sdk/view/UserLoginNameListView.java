package com.lion.plugin.sdkplugin.sdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lion.plugin.sdkplugin.R;
import com.lion.plugin.sdkplugin.sdk.widget.GMLinearLayout;

/**
 * Created by Antec on 2017/11/29.
 */
public class UserLoginNameListView extends GMLinearLayout implements View.OnClickListener{
    private TextView user_login_name_item;
    private RelativeLayout gm_img_delet_login_name;
    private RelativeLayout user_login_info_RL;
    private String entity;
    private OnDelBtnClickListener mOnDelBtnClickListener;
    public UserLoginNameListView(Context context) {
        super(context);
    }

    public UserLoginNameListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    protected void initViews() {
        contentView = inflate(mContext, R.layout.adapter_item_login_list_item_for_username_select_view_guaimao, this);
        user_login_info_RL = (RelativeLayout) contentView.findViewById(R.id.rl_adapter_item_coupon_user_info_container);
        user_login_name_item = (TextView) contentView.findViewById(R.id.tv_login_list_item_select_view_guaimao);
        gm_img_delet_login_name = (RelativeLayout) contentView.findViewById(R.id.ll_login_list_item_select_view_guaimao);
        gm_img_delet_login_name.setOnClickListener(this);
    }

    public void setData(String entity) {
        if (entity != null) {
            this.entity = entity;
            user_login_name_item.setText(entity);
        }
    }

    @Override
    public void onClick(View v) {
        mOnDelBtnClickListener.onDelBtnClicked();
    }


    /**
     * 删除按钮点击监听器接口
     *
     * @author Jone
     */
    public interface OnDelBtnClickListener {
        void onDelBtnClicked();
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDeleteBtnClickListener) {
        mOnDelBtnClickListener = onDeleteBtnClickListener;
    }

}
