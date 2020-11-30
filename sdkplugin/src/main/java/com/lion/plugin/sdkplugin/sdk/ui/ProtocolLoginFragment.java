package com.lion.plugin.sdkplugin.sdk.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lion.plugin.sdkplugin.R;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;


/**
 * Created by Antec on 2018/1/23.
 */
public class ProtocolLoginFragment extends UserBaseFragment {
    private static final String TAG = ProtocolLoginFragment.class.getName();
    private WebView protocol_WV;
    private String curUrl = "";
    private static final String PROTOCOL_DEBUG_URL = "https://demo.gm88.com/index.php?act=agreement&gm_source=android";
   // private static final String PROTOCOL_RELEASE_URL = "https://m.gm88.com/index.php?act=agreement&gm_source=android";
    private static final String PROTOCOL_RELEASE_URL = "https://m.gm88.com/index.php?act=agreement&gm_source=android";
    private static final String PROTOCOL_WSY_RELEASE_URL = "http://m.gm88.com/index.php?act=base_agreement&gm_source=android";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gm_fragment_login_entrance_protocol, null, false);
        protocol_WV = (WebView) view.findViewById(R.id.wv_user_center_login_entrance_protocol);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvents();
        initWebView();
    }

    private void initEvents() {
    }

    private void initWebView() {
        protocol_WV.getSettings().setJavaScriptEnabled(true);
        protocol_WV.getSettings().setDomStorageEnabled(true);
        protocol_WV.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        protocol_WV.getSettings().setLoadWithOverviewMode(true);
        protocol_WV.addJavascriptInterface(new gmservice(),"gmservice");

        protocol_WV.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                protocol_WV.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        protocol_WV.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
//        curUrl = PluginDataUtils.getInstance().isWsy()?PROTOCOL_WSY_RELEASE_URL:PROTOCOL_RELEASE_URL;
        curUrl = PROTOCOL_RELEASE_URL;
        protocol_WV.loadUrl(curUrl);

    }

    public class gmservice {
        @JavascriptInterface
        public void close() {
            ULogUtil.d(TAG,"onBack");
            onBack();
        }
    }
}

