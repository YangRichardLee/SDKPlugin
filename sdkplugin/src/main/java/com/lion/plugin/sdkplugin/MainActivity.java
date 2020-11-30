package com.lion.plugin.sdkplugin;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lion.plugin.pluginlibs.AppConstants;
import com.lion.plugin.pluginlibs.BasePluginActivity;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;

public class MainActivity extends BasePluginActivity {

    private static final String TAG = "Client-MainActivity";

    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_two);

        //startActivity，插件内跳转
        Button button1 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, PluginPlatform.getInstance().getDexPath());
                intent.putExtra(AppConstants.PROXY_ACTIVITY, "com.lion.plugin.sdkplugin.SecondActivity");
                startActivity(intent);
            }
        });

        //startActivityForResult
        button2 = (Button) findViewById(R.id.button5);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, PluginPlatform.getInstance().getDexPath());
                intent.putExtra(AppConstants.PROXY_ACTIVITY, "com.lion.plugin.sdkplugin.TestActivity");
                startActivityForResult(intent, 0);
            }
        });

        //跳转到宿主其它Activity
        Button button3 = (Button) findViewById(R.id.button6);
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userName", "lion");
                ComponentName componentName = new ComponentName("com.lion.plugin.demo", "com.lion.plugin.demo.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == 2) {
            String userName = data.getStringExtra("username");
            button2.setText(button2.getText() + userName);
        }
    }
}