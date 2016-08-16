package com.rzz.jsbridge.core;

import org.json.JSONObject;


/**
 * js插件
 * @author rzz
 */
public class JSPlugin {

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
	}

	public void onError(Object object){
		jsBridge.onError(callBack,object);
	}

	/**
	 * 释放插件的重要资源
	 */
	public void onDestroy(){

	}
}
