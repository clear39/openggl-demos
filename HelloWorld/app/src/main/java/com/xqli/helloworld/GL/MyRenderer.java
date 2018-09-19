package com.xqli.helloworld.GL;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer extends BaseRenderer {

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        log("onSurfaceCreated " + config);
        gl.glClearColor(0.0f,0.0f,0.0f,1.0f);
        //清除屏幕缓冲区，执行这一行屏幕为黑色背景
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        //gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        log("onSurfaceChanged " + width+","+height);
        gl.glViewport(0,0,width,height); //设置视口大小，相当于照相机底片大小
        float ratio = width / height;

        gl.glMatrixMode(GL10.GL_PROJECTION);//投影矩阵
        gl.glLoadIdentity();

        //GLU.gluPerspective(gl,45.0f,ratio,3,7);

        gl.glFrustumf(-ratio,ratio,-1,1,3,7);//设置平截头体的坐标/大小

        GLU.gluLookAt(gl,0,0,5,0,0,0,0,0,1);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //每次绘制需要清理屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);

        //设置视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        float[] cs = new float[]{
                0f,1f,0f,
                -1f,-1f,0f,
                1f,-1f,0f
        };

        ByteBuffer ibb = ByteBuffer.allocate(cs.length*4);
        ibb.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = ibb.asFloatBuffer();
        floatBuffer.put(cs);
        floatBuffer.position(0);

        gl.glVertexPointer(3,GL10.GL_FLOAT,0,floatBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);

    }
}
