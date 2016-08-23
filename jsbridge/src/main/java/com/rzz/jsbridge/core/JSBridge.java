package com.rzz.jsbridge.core;

import android.webkit.JavascriptInterface;

import com.rzz.jsbridge.core.utils.JSONHelper;

import java.lang.reflect.Method;


/**
 * js与native的核心交互
 * @author com.rzz
 */
public class JSBridge{

	private HybridWebView webView;

	public HybridWebView getWebView() {
		return webView;
	}

	public void setWebView(HybridWebView webView) {
		this.webView = webView;
	}

	public JSBridge(HybridWebView webView) {
		this.webView = webView;
	}

	/**
	 * 反射出动态插件
	 * @param pluginName
	 * @param pluginMethod
	 * @param pluginParams
	 * @param callBack
	 */
	@JavascriptInterface
	public void triggerNative(String pluginName,String pluginMethod,String pluginParams,String callBack) {
		PluginConfig plugin = ConfigManager.pluginMapping.get(pluginName);
		JSPlugin jsPluginInstance = null;
		try {
			if(plugin==null){
				throw new RuntimeException("plugin not found！");
			}

			Class<?> cls = Class.forName(plugin.getPackageName());

			Object pluginInstance = cls.newInstance();

			jsPluginInstance = (JSPlugin)pluginInstance;
			jsPluginInstance.setJsBridge(this);
			jsPluginInstance.setCallBack(callBack);
			jsPluginInstance.setExt(plugin.getExt());

			if(pluginParams == null){
				Method method = cls.getDeclaredMethod(pluginMethod);
				method.invoke(pluginInstance);
			}else{
				Method method = cls.getDeclaredMethod(pluginMethod, String.class);
				method.invoke(pluginInstance,pluginParams);
			}
		} catch (ClassNotFoundException e) {
			onError(callBack,e);
		} catch(Exception e){
			onError(callBack,e);
		}finally{
			if(jsPluginInstance!=null){
				jsPluginInstance.onDestroy();
			}
		}
	}


	/**
	 * 通知js
	 * @param callBack
	 * @param result
	 */
	public void triggerJS(String callBack,JSResult result){
		webView.loadUrl("javascript:JSBridgeCallBacks['"+callBack+"']("+objectToString(result)+");");
	}

	/**
	 * 通知触发成功回调
	 * @param callBack
	 * @param object
	 */
	public void onSuccess(String callBack,Object object){
		JSResult jsResult = new JSResult();
		jsResult.setCode(JSResult.SUCCESS);
		jsResult.setData(object);
		triggerJS(callBack,jsResult);
	}

	/**
	 * 通知触发失败回调
	 * @param callBack
	 * @param object
	 */
	public void onError(String callBack,Object object){
		JSResult jsResult = new JSResult();
		jsResult.setCode(JSResult.ERROR);
		jsResult.setData(object);
		triggerJS(callBack,jsResult);
	}


	/**
	 * 将对象序列化成字符回调给js，由于不想引入第三方库，所以自己写了一个。可以用其他第三方库直接序列化
	 * @param result
	 * @return
	 */
	public String objectToString(Object result){
		return JSONHelper.toJSON(result);
	}
}
