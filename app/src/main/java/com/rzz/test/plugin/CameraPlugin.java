/**
 * Created by Administrator on 2016/8/23.
 */
package com.rzz.test.plugin;

import com.rzz.jsbridge.core.JSPlugin;

public class CameraPlugin extends JSPlugin{

    public void testaaa(String params){
//        HybridManager.getAppContext();
        getJsBridge().getWebView().getActivity();

        onSuccess("teest");

       // onError("sdfs");
    }
}
