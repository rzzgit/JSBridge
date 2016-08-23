package com.rzz.jsbridge.core;

import android.app.Activity;
import android.webkit.WebView;

/**
 * Created by Administrator on 2016/8/23.
 */
public interface HybridWebView extends HybridWebViewLifeCycle{

    void loadUrl(String url);

    WebView getWebView();

    Activity getActivity();

    void addLifeListener(HybridWebViewLifeCycle hybridWebViewLifeCycle);

    void removeLifeListener(HybridWebViewLifeCycle hybridWebViewLifeCycle);
}
