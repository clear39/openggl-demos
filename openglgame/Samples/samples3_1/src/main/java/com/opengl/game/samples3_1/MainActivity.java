package com.opengl.game.samples3_1;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends Activity {


    private MyTDView mGLRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mGLRootView = new MyTDView(this);
        mGLRootView.requestFocus();
        mGLRootView.setFocusableInTouchMode(true);
        setContentView(mGLRootView);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mGLRootView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLRootView.onPause();
    }



}
