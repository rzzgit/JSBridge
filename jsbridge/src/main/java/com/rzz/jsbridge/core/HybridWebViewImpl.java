package com.rzz.jsbridge.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Vector;

public class HybridWebViewImpl implements  HybridWebView{
	private Vector<HybridWebViewLifeCycle> lifeOberservers = new Vector<HybridWebViewLifeCycle>();//监听自己的监听器队列

	private WebView webView;

	private Activity activity;

	private Handler handler = new Handler(){

		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 1:
					webView.loadUrl(   msg.getData().getString("url"));
					break;
			}
			super.handleMessage(msg);
		}

	};


	/**
	 * 默认使用自带webview，后续支持其他webkit
	 * @param context
	 */
	public HybridWebViewImpl(Context context) {
		this(context, new WebView(context));
	}


	@SuppressLint("JavascriptInterface")
	public HybridWebViewImpl(Context context, WebView webView) {
		super();
		this.webView = webView;
        if(context instanceof Activity){
			this.activity = (Activity)context;
		}
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		webView.addJavascriptInterface(new JSBridge(this), "JSBridge");
		webView.setWebChromeClient(new WebChromeClient());
		webView.setWebViewClient(new MyWebViewClient());
	}

    private class MyWebViewClient extends WebViewClient{
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			view.loadUrl("javascript: TriggerNative = function(pluginName,method,data,callID){ window.JSBridge.triggerNative(pluginName,method,data,callID);};");
		}
	}

    //存在bug，当方法名相同，且方法参数的数量相同就会出现反射失误，如果根据values分别获取class又涉及到int 和integer之间无法正确对应
	private void triggerListener(String action,Object ...values){
		Enumeration<HybridWebViewLifeCycle> en =lifeOberservers.elements();
		while(en.hasMoreElements()) {
			HybridWebViewLifeCycle obj = en.nextElement();
			try {
				Method[] methods = obj.getClass().getMethods();
				for(Method m:methods){
					if(action.equals(m.getName()) && m.getParameterTypes().length == values.length){
						m.invoke(obj,values);
						break;
					}
				}
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 加载页面
	 * @param url
	 */
	@Override
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

	@Override
	public WebView getWebView() {
		return webView;
	}

	@Override
	public Activity getActivity() {
		return activity;
	}

	@Override
	public void addLifeListener(HybridWebViewLifeCycle hybridWebViewLifeCycle) {
		lifeOberservers.add(hybridWebViewLifeCycle);
	}

	@Override
	public void removeLifeListener(HybridWebViewLifeCycle hybridWebViewLifeCycle) {
		lifeOberservers.remove(hybridWebViewLifeCycle);
	}

	@Override
	public void onStart() {
		triggerListener("onStart");
	}

	@Override
	public void onRestart() {
		triggerListener("onRestart");
	}

	@Override
	public void onResume() {
		triggerListener("onResume");
	}

	@Override
	public void onStop() {
		triggerListener("onStop");
	}

	@Override
	public void onPause() {
		triggerListener("onPause");
	}

	@Override
	public void onDestroy() {
		triggerListener("onDestroy");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		triggerListener("onActivityResult",requestCode,resultCode,data);
	}



}
