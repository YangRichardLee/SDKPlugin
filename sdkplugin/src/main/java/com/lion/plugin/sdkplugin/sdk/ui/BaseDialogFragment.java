package com.lion.plugin.sdkplugin.sdk.ui;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

public class BaseDialogFragment extends DialogFragment {
    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commit();
        } catch (IllegalStateException e) {
            Log.d("GUAIMAO", "Exception", e);
        }
    }
}