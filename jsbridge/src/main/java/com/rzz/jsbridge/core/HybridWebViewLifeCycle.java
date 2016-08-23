package com.rzz.jsbridge.core;

import android.content.Intent;

/**
 * Created by Administrator on 2016/8/23.
 */
public interface HybridWebViewLifeCycle {
    void onStart();

    void onRestart();


    void onResume() ;


    void onStop() ;


    void onPause() ;


    void onDestroy();


    void onActivityResult(int requestCode, int resultCode, Intent data) ;
}
