package com.lion.plugin.sdkplugin.sdk.ui;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.game.sdk.reconstract.model.User;
import com.lion.plugin.pluginlibs.BaseFragment;
import com.lion.plugin.sdkplugin.R;
import com.lion.plugin.sdkplugin.sdk.adapter.SelectListAdapter;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.constants.BundleConstants;
import com.lion.plugin.sdkplugin.sdk.constants.LogEvents;
import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;
import com.lion.plugin.sdkplugin.sdk.manager.FileUserInfoManager;
import com.lion.plugin.sdkplugin.sdk.manager.LoginPresenter;
import com.lion.plugin.sdkplugin.sdk.precenter.RegisterPresenter;
import com.lion.plugin.sdkplugin.sdk.ui.tips.BindPhoneResultDialogFragment;
import com.lion.plugin.sdkplugin.sdk.ui.tips.PurchaseSuccessDialogFragment;
import com.lion.plugin.sdkplugin.sdk.uitils.SharedPreferencesUtil;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;
import com.lion.plugin.sdkplugin.sdk.widget.CountDownButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhewang on 2020/5/8.
 */
public class NewRegisterFragment extends UserBaseFragment implements View.OnClickListener,
        PurchaseSuccessDialogFragment.PurchaseSuccessDialogListener, SelectListAdapter.OnItemClickListener
        , SelectListAdapter.OnDelBtnClickListener, PopupWindow.OnDismissListener {
    private static final String TAG = NewRegisterFragment.class.getName();
    private BindPhoneResultDialogFragment resultDialogFragment;
    private long mLastClickTime = 0;
    private final long TIME_INTERVAL = 5000L;
    private RegisterPresenter registerPresenter;
    private EditText phoneNumber_ET, pwd_ET;
    private List<User> mUserInfoList = new ArrayList<>();
    private List<User> mUserInfoListOnlyPhone = new ArrayList<>();
    private TextView confirm_TV, protocol_TV, version_TV;
    private SelectListAdapter selectListAdapter;
    private ImageView pull_up_or_down,pull_up_or_down2;
    private LoginPresenter loginPresenter;
    private LinearLayout quickLogin_LL, phoneLogin_LL, accountLogin_LL;
    private RelativeLayout pullUsername_RL_accout,pullUsername_RL_phone;
    private LayoutInflater mInflater;
    private PopupWindow mSelectWindow;
    private String selectToken = "";
    private View mLoginByPwd, mLoginByVCode;
    private boolean isShowCodeLogin = true;
    private static final String _DEFAULT_PWD = "267&*(&%$@*!";
    private CountDownButton vcode_BT;
    private EditText phone_ET, vcode_ET;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenter = new RegisterPresenter(this);
        loginPresenter = new LoginPresenter(this);
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_login_entrance_registered, null, false);
        initView(view);
        initEvents();
        return view;
    }

    private void initView(View view) {

        mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        phoneNumber_ET = view.findViewById(R.id.et_login_entrance_user_name_gm);
        mLoginByPwd = view.findViewById(R.id.login_by_pwd);
        mLoginByVCode = view.findViewById(R.id.login_by_vcode);
        pwd_ET = view.findViewById(R.id.et_login_entrance_user_password_gm);
        confirm_TV = view.findViewById(R.id.tv_login_and_register_btn_gm);
        pull_up_or_down = view.findViewById(R.id.pull_up_or_down);
        pull_up_or_down2 = view.findViewById(R.id.pull_up_or_down2);
        protocol_TV = view.findViewById(R.id.tv_login_entrance_read_protocol);
        version_TV = view.findViewById(R.id.tv_login_version_code);
        quickLogin_LL = view.findViewById(R.id.ll_quick_login);
        phoneLogin_LL = view.findViewById(R.id.ll_phone_login);
        accountLogin_LL = view.findViewById(R.id.ll_account_login);
        pullUsername_RL_accout = view.findViewById(R.id.gm_rl_login_entrance_username_pull_info);
        pullUsername_RL_phone = view.findViewById(R.id.gm_rl_login_entrance_phone_pull_info);

        phone_ET = view.findViewById(R.id.et_login_entrance_phone_gm);
        vcode_ET = view.findViewById(R.id.et_login_entrance_user_Vcode_gm);

        vcode_BT = view.findViewById(R.id.bt_get_Vcode);
        pwd_ET.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    private void changeLoginView() {
        if (isShowCodeLogin) {
            mLoginByPwd.setVisibility(View.GONE);
            mLoginByVCode.setVisibility(View.VISIBLE);
            phoneLogin_LL.setVisibility(View.GONE);
            accountLogin_LL.setVisibility(View.VISIBLE);
        } else {
            mLoginByPwd.setVisibility(View.VISIBLE);
            mLoginByVCode.setVisibility(View.GONE);
            phoneLogin_LL.setVisibility(View.VISIBLE);
            accountLogin_LL.setVisibility(View.GONE);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserInfoList = FileUserInfoManager.getInstance().getUserList();
        mUserInfoListOnlyPhone = chooseUserListOnlyPhone(mUserInfoList);

        if (mUserInfoList.size() < 1) {
            pullUsername_RL_accout.setVisibility(View.GONE);
        } else {
            pullUsername_RL_accout.setVisibility(View.VISIBLE);
        }

        if (mUserInfoListOnlyPhone.size() < 1) {
            pullUsername_RL_phone.setVisibility(View.GONE);
        } else {
            pullUsername_RL_phone.setVisibility(View.VISIBLE);
        }
    }

    private void initEvents() {
        quickLogin_LL.setOnClickListener(this);
        mLoginByPwd.setOnClickListener(this);
        mLoginByVCode.setOnClickListener(this);
        phoneLogin_LL.setOnClickListener(this);
        accountLogin_LL.setOnClickListener(this);
        vcode_BT.setOnClickListener(this);
        confirm_TV.setOnClickListener(this);
        protocol_TV.setOnClickListener(this);
        pullUsername_RL_accout.setOnClickListener(this);
        pullUsername_RL_phone.setOnClickListener(this);
        version_TV.setText("v" + Config.getSDKVersion());
    }

    private List<User> chooseUserListOnlyPhone(List<User> allOfUserInfoList){
        Iterator<User> iter = allOfUserInfoList.iterator();
        while (iter.hasNext()) {
            User user = iter.next();
            if (user.phone.isEmpty()) {
                iter.remove();
                FileUserInfoManager.getInstance().removeUser(user);
            }
        }
        return allOfUserInfoList;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == phoneLogin_LL.getId()) {
            isShowCodeLogin = true;
            changeLoginView();
            return;
        }

        if (v.getId() == accountLogin_LL.getId()) {
            isShowCodeLogin = false;
            changeLoginView();
            return;
        }

        if (v.getId() == confirm_TV.getId()) {
            logMyClickEvent(LogEvents.LOGIN_BUTTON_CLICK);
            if (isShowCodeLogin) {
                //验证码登录
                if (RegisterPresenter.checkPhone(phone_ET.getText().toString().trim())
                        && !TextUtils.isEmpty(vcode_ET.getText().toString().trim())) {
                    loginPresenter.loginByVCode(phone_ET.getText().toString().trim(), vcode_ET.getText().toString().trim());
                }
            } else {
                //密码登录
                if (pwd_ET.getText().toString().equals(_DEFAULT_PWD)) {
                    loginByToken(selectToken);
                } else {
                    doLoginByPassword((phoneNumber_ET.getText().toString()), pwd_ET.getText().toString());
                }
            }
            return;
        }

        if (v.getId() == vcode_BT.getId()){
            if (RegisterPresenter.checkPhone(phone_ET.getText().toString().trim())) {
                loginPresenter.getVerifyCodeForLogin(phone_ET.getText().toString().trim(), vcode_ET);
            }
            return;
        }


        if (v.getId() == quickLogin_LL.getId()) {
            logMyClickEvent(LogEvents.FAST_LOGIN_BUTTON_CLICK);
            long nowTime = System.currentTimeMillis();
            if (!SharedPreferencesUtil.getBoolean(SPConstants.GM_USER_FIRST_FAST_LOGIN, false)) {
                startPluginFragment(NewRegisterFragment.this,new SecurityHintFragment(),null);
            } else {
                if (nowTime - mLastClickTime > TIME_INTERVAL) {
                    logMyClickEvent(LogEvents.FAST_LOGIN_INTO_BUTTON_CLICK);
                    loginPresenter.fastLogin();
                    mLastClickTime = nowTime;
                }
            }

        }


        if (v.getId() == pullUsername_RL_accout.getId()) {
            logMyClickEvent(LogEvents.DROP_DOWN_BUTTON_CLICK_NORMAL);
            if (mUserInfoList.size() != 0) {
                showAccountChoiceWindow(mUserInfoList);
            }
            return;
        }
        if (v.getId() == pullUsername_RL_phone.getId()) {
            logMyClickEvent(LogEvents.DROP_DOWN_BUTTON_CLICK_NORMAL);
            if (mUserInfoListOnlyPhone.size() != 0) {
                showAccountChoiceWindow(mUserInfoListOnlyPhone);
            }
            return;
        }

        if (v.getId() == protocol_TV.getId()) {
            startPluginFragment(NewRegisterFragment.this,new ProtocolLoginFragment(),null);
        }
    }


    private void initDialog() {
        if (resultDialogFragment == null) {
            resultDialogFragment = new BindPhoneResultDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(BundleConstants.DATA_FOR_BIND_PHONE_DIALOG_TYPE,
                    BindPhoneResultDialogFragment.VIEW_TYPE_FOR_USER_CENTER_BIND);
            resultDialogFragment.setArguments(bundle);
            resultDialogFragment.setListener(this);
        }
    }

    private void showAccountChoiceWindow(List<User> userInfoTableLoginName) {

        View view = mInflater.inflate(R.layout.fragment_login_username_selectlist_gm, null);
        RelativeLayout contentview = view.findViewById(R.id.fragment_login_username_select_listlayout);
        ListView userlist = view.findViewById(R.id.fragment_login_username_select_list);

        selectListAdapter = new SelectListAdapter(getActivity(), userInfoTableLoginName);
        selectListAdapter.setOnItemClickListener(this);
        selectListAdapter.setOnDelBtnClickListener(this);
        userlist.setAdapter(selectListAdapter);

        if (isShowCodeLogin){
            mSelectWindow = new PopupWindow(contentview, phone_ET.getMeasuredWidth() + pullUsername_RL_phone.getMeasuredWidth() - 30, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mSelectWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_account_info_shot_white_corner_radius_1dp));
            mSelectWindow.setOutsideTouchable(true);
            mSelectWindow.setFocusable(true);
            mSelectWindow.setOnDismissListener(this);
            mSelectWindow.showAsDropDown(phone_ET, 2, 0);
            pull_up_or_down.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_login_entrance_username_down_info_guaimao));
        }else {
            mSelectWindow = new PopupWindow(contentview, phoneNumber_ET.getMeasuredWidth() + pullUsername_RL_accout.getMeasuredWidth() - 30, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mSelectWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_account_info_shot_white_corner_radius_1dp));
            mSelectWindow.setOutsideTouchable(true);
            mSelectWindow.setFocusable(true);
            mSelectWindow.setOnDismissListener(this);
            mSelectWindow.showAsDropDown(phoneNumber_ET, 2, 0);
            pull_up_or_down2.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_login_entrance_username_down_info_guaimao));
        }
    }




    @Override
    public void onDismiss() {
        pull_up_or_down.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_login_entrance_username_pull_info_guaimao));
        pull_up_or_down2.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_login_entrance_username_pull_info_guaimao));
    }

    @Override
    public void onItemClicked(int position) {
        mSelectWindow.dismiss();
        if (isShowCodeLogin){
            phone_ET.setText(mUserInfoListOnlyPhone.get(position).getPhone());
            vcode_ET.setText("");
            selectToken = "";
        }else {
            phoneNumber_ET.setText((mUserInfoList.get(position).getPhone().equals("null") || mUserInfoList.get(position).getPhone().isEmpty()) ?
                    mUserInfoList.get(position).getNickName() : mUserInfoList.get(position).getPhone());
            pwd_ET.setText(_DEFAULT_PWD);
            selectToken = mUserInfoList.get(position).token;
        }
        Log.w("onItemClicked", mUserInfoList.get(position).toString());
    }

    @Override
    public void onDelBtnClicked(int position) {
        logMyClickEvent(LogEvents.DROP_DOWN_BUTTON_CLICK_NORMAL_DELETE);
        FileUserInfoManager.getInstance().removeUser(mUserInfoList.get(position));
        mSelectWindow.dismiss();
        phoneNumber_ET.setSelection(phoneNumber_ET.getText().length());
        selectListAdapter.notifyDataSetChanged();
        Log.w("onDelBtnClicked_Lion", mUserInfoList.toString());

    }

    private void loginByToken(String token) {
        ULogUtil.d(TAG, "[loginByToken.... ] token :" + token);
        loginPresenter.loginByToken(token);
    }

    private void doLoginByPassword(String phone, String pwd) {
        loginPresenter.login(phone, pwd);
    }

    @Override
    public void onSuccessConfirm() {
        ULogUtil.d(TAG, "[onSuccessConfirm]...");
        getFragmentManager().popBackStack();
    }


    @Override
    public String getPhone() {
        return phoneNumber_ET.getEditableText().toString();
    }

    @Override
    public String getPassword() {
        return pwd_ET.getEditableText().toString();
    }

    @Override
    public void onBack() {
//        super.onBack();
    }
}
