package com.lion.plugin.sdkplugin.sdk.widget.floatwindow;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lion.plugin.pluginlibs.BasePluginActivity;
import com.lion.plugin.sdkplugin.R;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.manager.UserCenterManager;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.precenter.UserPresenter;
import com.lion.plugin.sdkplugin.sdk.uitils.ScreenResolution;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;
import com.lion.plugin.sdkplugin.sdk.view.DotView;

import java.lang.reflect.Field;

/**
 * 浮标逻辑
 * Created by Administrator on 2016/11/16.
 */
public class FloatWindow implements View.OnTouchListener {
    private final int FLOAT_VIEW_MIN_WIDTH;
    // 悬浮窗口上的按钮
    private ImageView floating_front;
    private ImageView floating_hide_top;
    private ImageView floating_hide_left;
    private ImageView floating_close;
    private ImageView floating_close_bg;
    private TextView floating_close_txt;

    private ImageView floatingTips_IV;
    private RelativeLayout floatFrontContainer_RL;

    private ObjectAnimator yTranslation;


    private boolean mIsHide;
    private boolean mIsAtEdge;
    private TimeCount mTimeCount;
    private boolean isShow;
    private boolean mIsBottomShow;
    private boolean isHiddenByUser;
    private boolean isHiddenAreaActivated;
    private int statusBarHeight;

    private View mRoot;
    //    private ViewGroup mRootBottom;
    private WindowManager.LayoutParams mLayoutParams;
    //    private WindowManager.LayoutParams mBottomLayoutParams;
    private WindowManager mWndMgr;

    private float mStartX, mStartY;
    private int mPos[] = {0, 0};

    private Context mContext;

    private boolean isShowPoint = false;
    private int currentPostion = HIDE_LEFT;         //0 中间位置， 1,左边位置，2，右边位置
    private final static int HIDE_LEFT = 1;
    private final static int HIDE_RIGHT = 2;
    private final static int FRONT_FLOAT = 0;
    private boolean showDot = false;
    private SelectPicPopupWindow menuWindow;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                refreshAllTips();
            }
        }
    };

    private static FloatWindow instance;

    public static FloatWindow getInstance(Activity activity) {
        if (instance == null) {
            instance = new FloatWindow(activity);
        }
        return instance;
    }

    public static void destroy() {
        if (instance != null) {
            instance.hide();
            instance = null;
        }
    }

    public FloatWindow(final Activity activity) {
        mContext = activity;
        FLOAT_VIEW_MIN_WIDTH = (int) PluginPlatform.getInstance().getResources().getDimension(R.dimen.floatview_left_width_guaimao);
        statusBarHeight = getStatusBarHeight();
        if (mRoot == null) {
            LayoutInflater inflater = (LayoutInflater) PluginPlatform.getInstance().getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mRoot = inflater.inflate(R.layout.layout_float_window_gm,null);
//            mRoot = PluginPlatform.getInstance().getActivity().get.inflate(R.layout.layout_float_window_gm, null);
            initComponents();
            initLayoutParams();
            isShow = false;
            isHiddenByUser = false;
        }

        menuWindow = new SelectPicPopupWindow((Activity) mContext);
        mTimeCount = new TimeCount(2000, 1000);
        new Thread(new MyThread()).start();
        mWndMgr = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
//        mWndMgr.addView(mRootBottom, mBottomLayoutParams);
        mIsBottomShow = true;
        hideHiddenZone();

        hideOnEdge(false);
    }

    public void show() {
        if (!isShow && !isHiddenByUser) {
            mWndMgr.addView(mRoot, mLayoutParams);
            isShow = true;
            mTimeCount.start();
            showCertainTipsImage();
        }
    }

    public boolean isShowing() {
        return isShow;
    }

    public void hide() {
        if (isShow) {
            if (mTimeCount != null)
                mTimeCount.cancel();
            mWndMgr.removeView(mRoot);
            isShow = false;
        }
    }

    private void initLayoutParams() {
        mLayoutParams = new WindowManager.LayoutParams();
        // 设置type
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        // 设置color format
        mLayoutParams.format = PixelFormat.RGBA_8888;
        // 设置Window flag
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 设置悬浮窗口停靠的位置
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        // 设置初始坐标
        mLayoutParams.x = 10;
        mLayoutParams.y = 300;
        mLayoutParams.width = (int) mContext.getResources().getDimension(R.dimen.floatview_width_guaimao);
        mLayoutParams.height = (int) mContext.getResources().getDimension(R.dimen.floatview_height_guaimao);


//        mBottomLayoutParams = new WindowManager.LayoutParams();
//        // 设置type
//        mBottomLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
//        // 设置color format
//        mBottomLayoutParams.format = PixelFormat.RGBA_8888;
//        // 设置Window flag
//        mBottomLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        // 设置悬浮窗口停靠的位置
//        mBottomLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//        // 设置初始坐标
//        Pair<Integer, Integer> res = ScreenResolution.getResolution(mContext);
//        mBottomLayoutParams.x = 0;
//        mBottomLayoutParams.y = res.second - (int) mContext.getResources().getDimension(Config.getDimensByName("float_window_bottom_height_guaimao"));
//        mBottomLayoutParams.width = res.first;
////        mBottomLayoutParams.height = (int) mContext.getResources().getDimension(R.dimen.float_window_bottom_height);
//        mBottomLayoutParams.height = (int) mContext.getResources().getDimension(Config.getDimensByName("float_window_bottom_height_guaimao"));

    }

    protected void initComponents() {
        floating_front = (ImageView) mRoot.findViewById(R.id.floating_front);
        floating_hide_left = (ImageView) mRoot.findViewById(R.id.float_hiding_left);
        floating_hide_left.setVisibility(View.INVISIBLE);
//        if (PluginDataUtils.getInstance().isWsy()) {
//            floating_front.setImageResource(R.drawable.ic_float_icon_normal_wsy);
//            floating_hide_left.setImageResource(R.drawable.ic_float_hide);
//        } else
        floating_front.setImageResource(R.drawable.ic_float_icon_normal_ysy);
        floating_hide_left.setImageResource(R.drawable.ic_float_hide_ysy);
//        }
        // floating_front.setImageResource(R.drawable.ic_float_icon_normal_wsy);
        floatingTips_IV = (ImageView) mRoot.findViewById(R.id.iv_floating_front_tips);
        floatFrontContainer_RL = (RelativeLayout) mRoot.findViewById(R.id.rl_floating_front_tips_container);

        floating_hide_top = (ImageView) mRoot.findViewById(R.id.float_hiding_top);
        floating_hide_top.setVisibility(View.INVISIBLE);

        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mRoot.measure(measureSpec, measureSpec);

        // 设置悬浮窗个按钮的监听
        floating_front.setOnTouchListener(this);
        floating_hide_top.setOnTouchListener(this);
        floating_hide_left.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event) {
        mTimeCount.cancel();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (v == floating_hide_top || v == floating_hide_left) {
                    hideOnEdge(false);
                } else {
                    mIsAtEdge = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        float x = event.getRawX();
        float y = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                floating_front.setPressed(true);
                mStartX = x;
                mStartY = y;
                mRoot.getLocationOnScreen(mPos);
                floating_front.setPressed(true);
                break;
            case MotionEvent.ACTION_MOVE:
                floating_front.setPressed(true);
                refreshTips();
                // 更新悬浮窗位置
                mLayoutParams.x = (int) (mPos[0] + x - mStartX);
                mLayoutParams.y = (int) (mPos[1] + y - mStartY);
                mWndMgr.updateViewLayout(mRoot, mLayoutParams);

                // 显示隐藏区域
                if (Math.abs(x - mStartX) > TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, mContext.getResources().getDisplayMetrics())
                        || Math.abs(y - mStartY) > TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, mContext.getResources().getDisplayMetrics())) {
//                    showHiddenZone();
                    showHiddenZone2();
                }
                break;
            case MotionEvent.ACTION_UP:
                floating_front.setPressed(false);
                onTouchEnd(Math.abs(x - mStartX) > TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, mContext.getResources().getDisplayMetrics())
                        || Math.abs(y - mStartY) > TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, mContext.getResources().getDisplayMetrics()));
            default:
                break;
        }
        return true;
    }

    private void onTouchEnd(boolean hasMoved) {
        // 隐藏隐藏区域
        hideHiddenZone();
        refreshTips();
        if (isHiddenAreaActivated) {
            isHiddenByUser = true;
            hide();
        } else {
            if (hasMoved) {
                mTimeCount.start();
            } else {
                if (!mIsHide && mIsAtEdge) {
                    mTimeCount.start();
                } else if (!mIsHide && !mIsAtEdge) {
//                    hide();
                    mTimeCount.start();
                    UserPresenter.showUserCenterActivity((Activity) mContext);
                }
            }
        }
    }

    private void hideOnEdge(boolean flag) {
        if (mIsHide == flag)
            return;

        if (flag) {
            hideControllers();
        } else {
            showControllers();
        }

        mIsHide = flag;
    }

    private void showControllers() {
        floating_front.setVisibility(View.VISIBLE);
        floatFrontContainer_RL.setVisibility(View.VISIBLE);
        if (showDot) {
            floatingTips_IV.setVisibility(View.VISIBLE);
        }
        showCertainTipsImage();
//        refreshAllTips();

        floating_hide_left.clearAnimation();
        floating_hide_left.setX(0);
        floating_hide_left.setY(0);
        floating_hide_left.setVisibility(View.INVISIBLE);

        floating_hide_top.clearAnimation();
        floating_hide_top.setX(0);
        floating_hide_top.setY(0);
        floating_hide_top.setVisibility(View.INVISIBLE);

    }

    public void showRedDot() {
        floatingTips_IV.setVisibility(View.VISIBLE);
        showDot = true;
    }

    public void setRedDot() {
        showDot = false;
    }


    private void refreshAllTips() {
        UserCenterManager.requestGetTipsInfo(new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                showCertainTipsImage();
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                showCertainTipsImage();
            }
        });

        UserCenterManager.requestPlatformGiftInfo(false, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
//                showCertainTipsImage();
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                showCertainTipsImage();
            }
        });
    }

    public void showCertainTipsImage() {
//        if (GlobalUtil.shouldShowFloatIconCouponSendTips()) {
//            floatingTips_IV.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_float_tips_coupon_guaimao));
//            SharedPreferencesUtil.saveString(SPConstants.GM_CURRENT_FLOAT_TIPS_TYPE, StringConstants.FLOAT_TIPS_TYPE_SEND_COUPON_GUAIMAO);
////        } else if(GlobalUtil.shouldShowFloatKfTips()){
////            floatingTips_IV.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_float_tips_kf_guaimao));
////            SharedPreferencesUtil.saveString(SPConstants.GM_CURRENT_FLOAT_TIPS_TYPE,
////                    StringConstants.FLOAT_TIPS_TYPE_SEND_KF_GUAIMAO);
//        }else if (SharedPreferencesUtil.getInt(SPConstants.GM_USER_KF_MESSAGE_NUMBER,0) > 0){
//            floatingTips_IV.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_float_tips_kf_guaimao));
////                        SharedPreferencesUtil.saveString(SPConstants.GM_CURRENT_FLOAT_TIPS_TYPE,
////                    StringConstants.FLOAT_TIPS_TYPE_SEND_KF_GUAIMAO);
//        }else if (SharedPreferencesUtil.getBoolean(SPConstants.GM_USER_MESSAGE_HAS_UPDATE, false)) {
//            floatingTips_IV.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_float_tips_msg_guaimao));
//            SharedPreferencesUtil.saveString(SPConstants.GM_CURRENT_FLOAT_TIPS_TYPE,
//                    StringConstants.FLOAT_TIPS_TYPE_SEND_MESSAGE_GUAIMAO);
//        } else if (SharedPreferencesUtil.getBoolean(SPConstants.GM_USER_GIFT_HAS_UPDATE, false)) {
//            floatingTips_IV.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_float_tips_gift_guaimao));
//            SharedPreferencesUtil.saveString(SPConstants.GM_CURRENT_FLOAT_TIPS_TYPE,
//                    StringConstants.FLOAT_TIPS_TYPE_SEND_GIFT_GUAIMAO);
//        } else {
        // floatingTips_IV.setVisibility(View.VISIBLE);
        //      }
    }

    private void hideControllers() {
        floating_front.setVisibility(View.INVISIBLE);
        floatFrontContainer_RL.setVisibility(View.INVISIBLE);
        floatingTips_IV.setVisibility(View.INVISIBLE);
//        stopAnimation();
    }

    private void showHiddenZone() {
        int pos[] = {0, 0};
        floating_close.getLocationOnScreen(pos);
        int float_close_height = floating_close.getHeight();
        int float_close_width = floating_close.getWidth();

        Rect floating_close_rect = new Rect(pos[0], pos[1], pos[0] + float_close_width, pos[1] + float_close_height);
        Rect mRoot_rect = new Rect(mLayoutParams.x, mLayoutParams.y, mLayoutParams.x + mLayoutParams.width, mLayoutParams.y + mLayoutParams.height);

        if (floating_close_rect.intersect(mRoot_rect)) {
            changeHiddenAreaActivated(true);
        } else {
            changeHiddenAreaActivated(false);
        }

        if (!mIsBottomShow) {
//            mRootBottom.setVisibility(View.VISIBLE);
            menuWindow.showAtLocation(PluginPlatform.getInstance().getActivity().getWindow().getDecorView(), Gravity.BOTTOM | Gravity.LEFT, 0, 0);
            mIsBottomShow = true;
        }
    }


    private void showHiddenZone2() {
        menuWindow.showAtLocation(PluginPlatform.getInstance().getActivity().getWindow().getDecorView(), Gravity.BOTTOM | Gravity.LEFT, 0, 0);

        int pos[] = {0, 0};
        floating_close.getLocationOnScreen(pos);
        int float_close_height = floating_close.getHeight();
        int float_close_width = floating_close.getWidth();

        Rect floating_close_rect = new Rect(pos[0], pos[1], pos[0] + float_close_width, pos[1] + float_close_height);
        Rect mRoot_rect = new Rect(mLayoutParams.x, mLayoutParams.y, mLayoutParams.x + mLayoutParams.width, mLayoutParams.y + mLayoutParams.height);

        if (floating_close_rect.intersect(mRoot_rect)) {
            changeHiddenAreaActivated(true);
        } else {
            changeHiddenAreaActivated(false);
        }

        if (!mIsBottomShow) {
//            mRootBottom.setVisibility(View.VISIBLE);
            menuWindow.dismiss();
            mIsBottomShow = true;
        }
    }

    private void changeHiddenAreaActivated(boolean isActivated) {
        if (isActivated) {
            floating_close.setActivated(true);
            floating_close_bg.setVisibility(View.VISIBLE);
            floating_close_txt.setTextColor(0xffb53537);
            isHiddenAreaActivated = true;
        } else {
            floating_close.setActivated(false);
            floating_close_bg.setVisibility(View.INVISIBLE);
            floating_close_txt.setTextColor(0xffffffff);
            isHiddenAreaActivated = false;
        }
    }

    private void hideHiddenZone() {
        if (mIsBottomShow) {
//            mRootBottom.setVisibility(View.INVISIBLE);
            menuWindow.dismiss();
            mIsBottomShow = false;
        }
    }

    private void detectEdge() {

        Pair<Integer, Integer> res = ScreenResolution.getResolution(mContext);
        mRoot.getLocationOnScreen(mPos);

        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mRoot.measure(measureSpec, measureSpec);

        boolean toHide = true;
        floating_hide_left.setVisibility(View.VISIBLE);
        mRoot.invalidate();
        if (mPos[0] <= res.first.intValue() / 2) {
            // left
            currentPostion = HIDE_LEFT;
            mLayoutParams.x = 0;
            mLayoutParams.y = mPos[1];
            mWndMgr.updateViewLayout(mRoot, mLayoutParams);
            refreshTips();
            if (floating_hide_left.getRotation() != 0)
                floating_hide_left.setRotation(0);
        } else {
            // right
            currentPostion = HIDE_RIGHT;
            mLayoutParams.x = res.first.intValue() - FLOAT_VIEW_MIN_WIDTH;
            mLayoutParams.y = mPos[1];
            mWndMgr.updateViewLayout(mRoot, mLayoutParams);
            floating_hide_left.setX(mLayoutParams.height / 2);
            refreshTips();
            floating_hide_left.setRotation(180);
        }
        mIsAtEdge = toHide;
        hideOnEdge(toHide);
    }

    // 用于计时的类
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            detectEdge();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }
    }

    private void refreshTips() {
        if (isShowPoint) {
//            if (UserPresenter.getUser().has_new_msg
//                    || UserPresenter.getUser().has_new_giftbox
//                    || UserPresenter.getUser().has_discount) {
//                switch (currentPostion) {
//                    case HIDE_LEFT:
//                        tipsCenter_DT.setVisibility(View.INVISIBLE);
//                        tipsRightSide_DT.setVisibility(View.INVISIBLE);
//                        tipsLeftSide_DT.setVisibility(View.VISIBLE);
//                        break;
//                    case HIDE_RIGHT:
//                        tipsCenter_DT.setVisibility(View.INVISIBLE);
//                        tipsRightSide_DT.setVisibility(View.VISIBLE);
//                        tipsLeftSide_DT.setVisibility(View.INVISIBLE);
//                        break;
//                    case FRONT_FLOAT:
//                        tipsCenter_DT.setVisibility(View.VISIBLE);
//                        tipsRightSide_DT.setVisibility(View.INVISIBLE);
//                        tipsLeftSide_DT.setVisibility(View.INVISIBLE);
//                        break;
//                }
////                tipsLeftSide_DT.setVisibility(showSide ? View.VISIBLE : View.GONE);
//            } else {
//                tipsCenter_DT.setVisibility(View.INVISIBLE);
//                tipsLeftSide_DT.setVisibility(View.INVISIBLE);
//                tipsRightSide_DT.setVisibility(View.INVISIBLE);
//            }
        }
    }

    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
        return statusBarHeight;
    }

    private void startAnimation() {
        float currentTranslationY = floating_front.getTranslationY();
        float currentTranslationX = floating_front.getTranslationX();
        if (yTranslation == null)
            yTranslation = ObjectAnimator.ofFloat(floating_front, "translationY", currentTranslationY, -10F, 10F);
        yTranslation.setDuration(500);
        yTranslation.setRepeatCount(6);
        yTranslation.setupEndValues();
        yTranslation.start();
    }

    private void stopAnimation() {
        if (yTranslation != null) {
            yTranslation.cancel();
            yTranslation = null;
        }
    }

    public class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000 * 60 * 3);// 线程暂停10分钟，单位毫秒
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);// 发送消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class SelectPicPopupWindow extends PopupWindow {
        private View mMenuView;
        private final String TAG = SelectPicPopupWindow.class.getName();

        public SelectPicPopupWindow(Activity context) {
            super(context);
            ULogUtil.d(TAG, "SelectPicPopupWindow .  create.");
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mMenuView = inflater.inflate(R.layout.layout_float_window_bottom_gm, null);
            floating_close = (ImageView) mMenuView.findViewById(R.id.iv_float_window_close);
            floating_close_bg = (ImageView) mMenuView.findViewById(R.id.iv_float_window_close_bg);
            floating_close_bg.setAlpha(40);
            floating_close_txt = (TextView) mMenuView.findViewById(R.id.tv_float_window_close_txt);

            //设置SelectPicPopupWindow的View
            this.setContentView(mMenuView);
            //设置SelectPicPopupWindow弹出窗体的宽
            this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            //设置SelectPicPopupWindow弹出窗体的高
            this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //设置SelectPicPopupWindow弹出窗体可点击
            this.setFocusable(true);
            //设置SelectPicPopupWindow弹出窗体动画效果
//            this.setAnimationStyle(Config.getAnimByName("AnimBottom);
            //实例化一个ColorDrawable颜色为透明
            ColorDrawable dw = new ColorDrawable(0x77222222);
            //设置SelectPicPopupWindow弹出窗体的背景
            this.setBackgroundDrawable(dw);
        }

    }
}
