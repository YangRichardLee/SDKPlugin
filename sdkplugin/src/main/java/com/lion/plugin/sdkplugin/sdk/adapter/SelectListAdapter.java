package com.lion.plugin.sdkplugin.sdk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.sdkplugin.sdk.view.UserLoginNameListView;

import java.util.ArrayList;
import java.util.List;

public class SelectListAdapter extends BaseAdapter {


    private Context mContext;
    private List<User> mAccounts = new ArrayList<User>();
    private OnItemClickListener mOnItemClickListener;
    private OnDelBtnClickListener mOnDelBtnClickListener;

    public SelectListAdapter(Context context, List<User> items) {
        mAccounts = items;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mAccounts == null ? 0 : mAccounts.size();
    }

    @Override
    public Object getItem(int position) {
        return mAccounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UserLoginNameListView itemView;
        if (null == convertView) {
            itemView = new UserLoginNameListView(mContext);
            convertView = itemView;
            itemView.setTag(convertView);
        } else{
            itemView = (UserLoginNameListView) convertView.getTag();
        }
        itemView.setData((mAccounts.get(position).getPhone().equals("null")||mAccounts.get(position).getPhone().isEmpty())?mAccounts.get(position).getNickName():mAccounts.get(position).getPhone());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(position);
            }
        });
        itemView.setOnDelBtnClickListener(new UserLoginNameListView.OnDelBtnClickListener() {
            @Override
            public void onDelBtnClicked() {
                mOnDelBtnClickListener.onDelBtnClicked(position);
            }
        });
        return convertView;
    }

    /**
     * 选择条目点击监听器接口
     *
     * @author Jone
     */
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    /**
     * 删除按钮点击监听器接口
     *
     * @author Jone
     */
    public interface OnDelBtnClickListener {
        void onDelBtnClicked(int position);
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener onDeleteBtnClickListener) {
        mOnDelBtnClickListener = onDeleteBtnClickListener;
    }

}
