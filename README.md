# JSBridge
How to do use?

for java: 

  HybridManager.init(getApplicationContext());
	hybridWebView = new HybridWebView(this);
	hybridWebView.loadUrl("file:///android_asset/demo.html");
	
	addContentView(hybridWebView.getmWebView(), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	
a simple plugin:

for java

   public class FirstPlugin extends JSPlugin{
      //to do sth.  such as:  camera plugin , file plugin , http request plugin and so on....
   
      public void test(String jsonObject){
    	     onSuccess(jsonObject);
    	     //or   onError (jsonObject);
      }
}


for js:
    <script src="JSBridge.js"></script>
     function test(){
            //             pluginName    pluginMethod       params      success or error callback  
            triggerNative('FirstPlugin',   'test',          {a:1,b:3},  function(data){
                  alert("success     "+data);
            },function(error){
                alert("error     "+error);
            });
        }
	
for assets config
   file name is "jsbridge_config";

     {
 
   "plugins":[
  
		{"name":"FirstPlugin","package":"com.rzz.web.plugin.FirstPlugin","ext":{"b":"v"} }
    ,
		{"name":"TestPlugin","package":"com.rzz.web.plugin.TestPlugin","ext":{"b":"v"} } ]

}
		
		
		
		
