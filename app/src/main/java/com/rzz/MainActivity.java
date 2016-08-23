package com.rzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;

import com.rzz.jsbridge.core.HybridManager;
import com.rzz.jsbridge.core.HybridWebView;

public class MainActivity extends Activity {
    private HybridWebView hybridWebView;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		HybridManager.init(getApplicationContext());
		
		hybridWebView = HybridManager.getNewInstanceWeb(this);
		addContentView(hybridWebView.getWebView(), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		hybridWebView.loadUrl("http://192.168.1.120:3000/demo.html");
		hybridWebView.loadUrl("file:///android_asset/demo.html");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
