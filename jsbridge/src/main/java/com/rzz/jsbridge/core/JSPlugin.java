package com.rzz.jsbridge.core;

import android.content.Intent;

import org.json.JSONObject;


/**
 * js插件
 * @author com.rzz
 */
public class JSPlugin implements HybridWebViewLifeCycle{

	private JSBridge jsBridge;
	private String callBack;
	private JSONObject ext;

	public JSPlugin() {
		super();
	}

	public JSBridge getJsBridge() {
		return jsBridge;
	}

	public void setJsBridge(JSBridge jsBridge) {
		this.jsBridge = jsBridge;
	}


	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}


	public JSONObject getExt() {
		return ext;
	}

	public void setExt(JSONObject ext) {
		this.ext = ext;
	}



	public void onSuccess(Object object){
		jsBridge.onSuccess(callBack,object);
		onDestroy();
	}

	public void onError(Object object){
		jsBridge.onError(callBack,object);
		onDestroy();
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onRestart() {

	}

	@Override
	public void onResume() {

	}

	@Override
	public void onStop() {

	}

	@Override
	public void onPause() {

	}

	/**
	 * 释放插件的重要资源
	 */
	@Override
	public void onDestroy(){

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	}
}
