package com.rzz.jsbridge.core;
import org.json.JSONObject;

public class PluginConfig {
	 private String name;
	    private String packageName;
	    private JSONObject ext;
	      
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		public JSONObject getExt() {
			return ext;
		}
		public void setExt(JSONObject ext) {
			this.ext = ext;
		}
      
      
      
}
