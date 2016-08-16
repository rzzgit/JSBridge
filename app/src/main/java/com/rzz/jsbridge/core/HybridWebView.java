package com.rzz.jsbridge.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class HybridWebView{

	private Handler handler = new Handler(){

		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 1:
					webView.loadUrl(   msg.getData().getString("url"));
					break;
			}
			super.handleMessage(msg);
		};

	};

	private WebView webView;


	/**
	 * 默认使用自带webview，后续支持其他webkit
	 * @param context
	 */
	public HybridWebView(Context context) {
		this(context,new WebView(context));
	}


	@SuppressLint("JavascriptInterface")
	public HybridWebView(Context context,WebView webView) {
		super();
		this.webView = webView;

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		webView.addJavascriptInterface(new JSBridge(this), "JSBridge");
		webView.setWebChromeClient(new WebChromeClient());
	}



	/**
	 * 加载页面
	 * @param url
	 */
	public void loadUrl(String url) {
		if(Looper.getMainLooper() == Looper.myLooper()){
			webView.loadUrl(url);
		}else{
			Message msg = new Message();
			msg.what =1;
			Bundle bundle=new Bundle();
			bundle.putString("url", url);
			msg.setData(bundle);
			handler.sendMessage(msg);
		}
	}



	public WebView getmWebView() {
		return webView;
	}



	public void setmWebView(WebView webView) {
		this.webView = webView;
	}




}
