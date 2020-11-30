package com.lion.plugin.pluginlibs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by pengyuntao on 16/8/19.
 */
public class BaseFragment extends Fragment implements IRemoteFragment {

    protected String dexPath;

    private int containerId;

    public BaseFragment() {

    }


    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public void startPluginFragment(BaseFragment nowFragment,BaseFragment targetFragment,Bundle args){
        targetFragment.setArguments(args);
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)  //将当前fragment加入到返回栈中
                .replace(nowFragment.getContainerId(), targetFragment).commit();
    }


    public void startPluginFragment(Context context, String fragment) {
        startPluginFragment(context,fragment,  null);
    }

    public void startPluginFragment(Context context,String fragment, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(AppConstants.PROXY_VIEW_ACTION);
        intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
        intent.putExtra(AppConstants.PROXY_FRAGMENT, fragment);
        if (bundle != null){
            intent.putExtra(AppConstants.EXTRA_BUNDLE, bundle);
        }
        context.startActivity(intent);
    }

    @Override
    public void setProxy( String dexPath) {
        this.dexPath = dexPath;
    }

    public void onBack(){
        getFragmentManager().popBackStack();
//        getSupportFragmentManager().popBackStack();
    }
}
