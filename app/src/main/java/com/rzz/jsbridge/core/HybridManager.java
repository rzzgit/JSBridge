package com.rzz.jsbridge.core;

import android.content.Context;

public class HybridManager {
	private static Context appContext;
	
	 public static void init(Context context){
		 HybridManager.appContext  = context;
		 ConfigManager.parseMapping(context);
	 }

	public static Context getAppContext() {
		return appContext;
	}

	public static void setAppContext(Context appContext) {
		HybridManager.appContext = appContext;
	}
	 
	 

}
