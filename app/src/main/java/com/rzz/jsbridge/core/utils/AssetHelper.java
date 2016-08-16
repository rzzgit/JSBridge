package com.rzz.jsbridge.core.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetHelper {
	public static String getPluginMapping(Context context,String path){
   	 AssetManager assetManager = context.getAssets();
   	StringBuffer stringBuffer = new StringBuffer();
   	 try {
   	 	InputStream is = assetManager.open(path);
   	 	BufferedReader br = new BufferedReader(new InputStreamReader(is));
   	 	
   	 	String str = null;
   	 	while((str = br.readLine())!=null){
   	 		stringBuffer.append(str);
   	 	}
   	 	return stringBuffer.toString();
   	 } catch (IOException e) {
   	 	e.printStackTrace();
   	 	return null;
   	 }
    }
}
