package com.xqli.helloworld.GL;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

    private static final String TAG = "MyGLSurfaceView";

    private float mStartX = 0.0f;
    private static float X_DISTANCE = 10.0f;

    private float mStartY = 0.0f;
    private static float Y_DISTANCE = 20.0f;

    private MyRenderer mRenderer;

    private int mWidth;
    private int mHeight;

    public MyGLSurfaceView(Context context) {
        super(context);
        //mRenderer = new OpenGLRenderer2();
        mRenderer = new MyRenderer();
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight =  MeasureSpec.getSize(heightMeasureSpec);

        X_DISTANCE = (float) (Math.PI * 20 / mWidth);
        Y_DISTANCE = (float) (Math.PI * 20 / mHeight);


        Log.d(TAG, "onMeasure: mWidth:" + mWidth + ",mHeight:" + mHeight);
        Log.d(TAG, "onMeasure: X_DISTANCE:" + X_DISTANCE + ",Y_DISTANCE:" + Y_DISTANCE);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
            mStartX = event.getX();
            mStartY = event.getY();
        }else if(event.getActionMasked() == MotionEvent.ACTION_MOVE || event.getActionMasked() == MotionEvent.ACTION_UP){
            if(Math.abs(event.getX() - mStartX) > X_DISTANCE
                    || Math.abs(event.getY() - mStartY) > Y_DISTANCE){

                mRenderer.yrotate = mRenderer.yrotate + (event.getX() - mStartX) / X_DISTANCE;

                mRenderer.xrotate = mRenderer.xrotate + (event.getY() - mStartY) / Y_DISTANCE;
            }

            mStartX = event.getX();
            mStartY = event.getY();
        }

        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            mStartX = 0.0f;
            mStartY = 0.0f;
        }

        return true;
    }
}
