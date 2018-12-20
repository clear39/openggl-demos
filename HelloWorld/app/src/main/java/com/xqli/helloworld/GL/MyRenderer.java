package com.xqli.helloworld.GL;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.xqli.helloworld.GL.charts.Square;
import com.xqli.helloworld.GL.charts.Triangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer extends BaseRenderer {

    private Triangle mTriangle;
    private Square mSquare;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        log("onSurfaceCreated " + config);
        gl.glClearColor(0.0f,0.0f,0.0f,1.0f);
        //清除屏幕缓冲区，执行这一行屏幕为黑色背景
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        //gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        mTriangle = new Triangle();
        mSquare = new Square();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        log("onSurfaceChanged " + width+","+height);
        gl.glViewport(0,0,width,height); //设置视口大小，相当于照相机底片大小
        float ratio = width / height;


        //投影分为正投影和透视投影
        //透视投影有深度测试（远的物体小，进的物体大）

        gl.glMatrixMode(GL10.GL_PROJECTION);//投影矩阵
        gl.glLoadIdentity();



        //设置平截头体矩烝的俩种方法
        //  near和far是表示视点到远近投影平面的距离(注意是距离 不是坐标)
        //GLU.gluPerspective(gl,45.0f,ratio,3,7);
        gl.glFrustumf(-ratio,ratio,-1,1,3,7);

        //eyeX,eyeY,eyeZ 相当于眼睛所放到位置
        //centerX,centerY,centerZ 相当于原点中心坐标
        //upX,upY,upZ 相当于眼睛朝哪个方向看
        GLU.gluLookAt(gl,0,0,5,0,0,0,0,0,1);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        log("onDrawFrame ");
        //每次绘制需要清理屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);

        //设置视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();


        mTriangle.draw(gl);
        mSquare.draw(gl);
    }
}
