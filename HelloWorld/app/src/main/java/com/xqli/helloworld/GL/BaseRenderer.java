package com.xqli.helloworld.GL;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class BaseRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "Renderer";


    //
    protected float xrotate = 0f;
    protected float yrotate = 0f;



    protected void log(String logStr){
        Log.d(TAG,logStr);
    }
}
