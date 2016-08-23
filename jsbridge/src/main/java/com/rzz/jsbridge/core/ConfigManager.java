package com.rzz.jsbridge.core;

import android.content.Context;

import com.rzz.jsbridge.core.utils.AssetHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ConfigManager {
	private final static String PLUGIN_CONFIG_NAME = "jsbridge_config.json";
   
	public static Map<String,PluginConfig> pluginMapping = new HashMap<String,PluginConfig>();
	
	
    
	
	public static void parseMapping(Context context){
		String str = AssetHelper.getPluginMapping(context, PLUGIN_CONFIG_NAME);
		try {
			JSONObject configObj = new JSONObject(str);
			JSONArray plugins = configObj.getJSONArray("plugins");
			for(int i=0,l = plugins.length();i<l;i++){
				JSONObject pluginObj = plugins.getJSONObject(i);
				PluginConfig plugin = new PluginConfig();
				plugin.setExt(pluginObj.optJSONObject("ext"));
				plugin.setPackageName(pluginObj.optString("package"));
				plugin.setName(pluginObj.optString("name"));
				pluginMapping.put(plugin.getName(), plugin);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
    
    
}
