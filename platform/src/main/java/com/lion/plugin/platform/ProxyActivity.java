package com.lion.plugin.platform;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.lion.plugin.pluginlibs.AppConstants;
import com.lion.plugin.pluginlibs.BaseFragment;
import com.lion.plugin.pluginlibs.IRemoteActivity;
import com.lion.plugin.pluginlibs.IRemoteFragment;

import java.lang.reflect.Constructor;

public class ProxyActivity extends BaseHostActivity {

    private static final String TAG = "ProxyActivity";

    private IRemoteActivity mRemoteActivity;
    private IRemoteFragment mRemoteFragment;

    protected LinearLayout viewRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.getWindow().getDecorView().setBackground(null);
        getLayoutInflater().setFactory2(new WidgetLayoutInflaterFactory());
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        if (getIntent().hasExtra(AppConstants.PROXY_ACTIVITY)){
            if (getIntent().hasExtra(AppConstants.EXTRA_INFO)){
                launchTargetActivity(getIntent().getStringExtra(AppConstants.PROXY_ACTIVITY),getIntent().getBundleExtra(AppConstants.EXTRA_INFO));
            }else {
                launchTargetActivity(getIntent().getStringExtra(AppConstants.PROXY_ACTIVITY));
            }
        }else if (getIntent().hasExtra(AppConstants.PROXY_FRAGMENT)){
            if (getIntent().hasExtra(AppConstants.EXTRA_INFO)){
                installPluginFragment(getIntent().getStringExtra(AppConstants.PROXY_FRAGMENT),getIntent().getBundleExtra(AppConstants.EXTRA_INFO));
            }else {
                installPluginFragment(getIntent().getStringExtra(AppConstants.PROXY_FRAGMENT));
            }
        }
    }

    protected View getContentView() {
        viewRoot = new LinearLayout(this);
        viewRoot.setFitsSystemWindows(true);
        ViewGroup.LayoutParams rootLayout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        viewRoot.setLayoutParams(rootLayout);
        viewRoot.setGravity(Gravity.CENTER);
        viewRoot.setId(android.R.id.primary);
        return viewRoot;
    }

    protected void launchTargetActivity(String className){
        launchTargetActivity(className,new Bundle());
    }

    protected void launchTargetActivity(final String className,Bundle bundle) {
        try {
            //反射出插件的Activity对象
            Class<?> localClass = getClassLoader().loadClass(className);
            Constructor<?> localConstructor = localClass.getConstructor();
            Object instance = localConstructor.newInstance();

            mRemoteActivity = (IRemoteActivity) instance;
            mRemoteActivity.setProxy(this, getDexPath());
            mRemoteActivity.onCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void installPluginFragment(String fragClass){
        installPluginFragment(fragClass,new Bundle());
    }

    /**
     * 反射创建fragment,通过fragmentManager把创建的fragment附加到Activity
     *
     * @param fragClass
     */
    protected void installPluginFragment(String fragClass,Bundle bundle) {
        try {
            if (isFinishing()) {
                return;
            }

            Class<?> localClass = getClassLoader().loadClass(fragClass);
            Constructor<?> localConstructor = localClass.getConstructor();
            Object instance = localConstructor.newInstance();
            BaseFragment f = (BaseFragment) instance;
            f.setArguments(bundle);

            f.setContainerId(android.R.id.primary);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(android.R.id.primary, f);
            ft.commit();
//            ClassLoader classLoader = getClassLoader();
//            Fragment fg = (Fragment) classLoader.loadClass(fragClass).newInstance();
//            mRemoteFragment = (IRemoteFragment) fg;
//            mRemoteFragment.setProxy(getDexPath());
//            fg.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction()
//                    .addToBackStack(null)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .replace(android.R.id.primary,fg).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult resultCode=" + resultCode);
        if (mRemoteActivity != null)
        mRemoteActivity.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mRemoteActivity != null)
        mRemoteActivity.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mRemoteActivity != null)
        mRemoteActivity.onRestart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mRemoteActivity != null)
        mRemoteActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mRemoteActivity != null)
        mRemoteActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRemoteActivity != null)
        mRemoteActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRemoteActivity != null)
        mRemoteActivity.onDestroy();
    }

}
