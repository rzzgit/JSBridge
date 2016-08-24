package com.rzz.web.plugin;

import android.content.Intent;
import android.util.Log;

import com.rzz.jsbridge.core.JSPlugin;

public class FirstPlugin extends JSPlugin{

      public void test(String jsonObject){
            getJsBridge().getWebView().addLifeListener(this);
    	    getJsBridge().getWebView().onStart();
            getJsBridge().getWebView().onActivityResult(1, 2, new Intent());
            onActivityResult(1,2,new Intent());
            onSuccess(jsonObject);
      }

      @Override
      public void onStart() {
            super.onStart();
            Log.d("RZZ", "123");
      }

      @Override
      public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
      }

      public void onActivityResult(Integer requestCode, Integer resultCode, Intent data) {
            Log.d("RZZ","");
      }

      @Override
      public void onDestroy() {
            super.onDestroy();
            getJsBridge().getWebView().removeLifeListener(this);
      }
}
