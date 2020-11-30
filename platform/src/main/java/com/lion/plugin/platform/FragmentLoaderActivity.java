package com.lion.plugin.platform;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lion.plugin.pluginlibs.AppConstants;
import com.lion.plugin.pluginlibs.BaseFragment;
import com.lion.plugin.pluginlibs.IRemoteFragment;

import java.lang.reflect.Constructor;

public class FragmentLoaderActivity extends BaseHostActivity {

    private String mClass;

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

        mClass = getIntent().getStringExtra(AppConstants.PROXY_FRAGMENT);

        setContentView(getContentView());



        try {
            //反射出插件的Fragment对象
//            Class<?> localClass = PluginPlatform.getInstance().getDexClassLoader().loadClass(mClass);
//            Constructor<?> localConstructor = localClass.getConstructor(new Class[] {});
//            Object instance = localConstructor.newInstance(new Object[] {});
//            BaseFragment f = (BaseFragment) instance;
//
//            f.setContainerId(R.id.container);
//
//
//            FragmentManager fm = getFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            ft.replace(R.id.container, f);
//            ft.commit();



//            ClassLoader classLoader = getClassLoader();
//            Fragment fg = (Fragment) classLoader.loadClass(fragClass).newInstance();
//            mRemoteFragment = (IRemoteFragment) fg;
//            mRemoteFragment.setProxy(getDexPath());
//            fg.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction()
//                    .addToBackStack(null)
//                    .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .replace(android.R.id.primary,fg).commitAllowingStateLoss();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
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

}
