package com.opengl.game.samples3_1;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.opengl.lib.ShaderUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {
    public static float[] mProjMatrix = new float[16]; //4*4
    public static float[] mVMatrix = new float[16];
    public static float[] mMVPMatrix;
    int mProgram;
    int muMVPMatrixHandle;
    int maPositionHandle;
    int maColorHandle;
    String mVertexShader;
    String mFragmentShader;
    static float[] mMMatrix = new float[16];

    FloatBuffer mVertexBuffer;
    FloatBuffer mColorBuffer;
    int vCount = 0;
    float xAngle = 0;

    public Triangle(MyTDView mv){
        initVertexData();
        initShader(mv);
    }

    public void initVertexData(){
        vCount = 3;
        final float UNIT_SIZE = 0.2f;
        float vertices[] = new float[]{
                -4*UNIT_SIZE,0,0,
                0,-4*UNIT_SIZE,0,
                4*UNIT_SIZE,0,0
        };

        //生成顶点缓冲区
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序为本地操作系统顺序
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);


        //颜色缓冲区
        float colors[] = new float[]{
                1,1,1,0,
                0,0,1,0,
                0,1,0,0
        };

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
    }




    public void initShader(MyTDView mv){
        mVertexShader = ShaderUtil.loadFromAssetsFile("vertex.sh",mv.getResources());
        mFragmentShader = ShaderUtil.loadFromAssetsFile("frag.sh",mv.getResources());
        mProgram = ShaderUtil.createProgram(mVertexShader,mFragmentShader);
        maPositionHandle = GLES20.glGetAttribLocation(mProgram,"aPosition");
        maColorHandle = GLES20.glGetAttribLocation(mProgram,"aColor");
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram,"uMVPMatrix");
    }

    public void drawSelf(){
        GLES20.glUseProgram(mProgram);
        Matrix.setRotateM(mMMatrix,0,0,0,1,0);  //  初始化变换矩阵
        Matrix.translateM(mMMatrix,0,0,0,1);        //  设置沿着Z轴正向位移
        Matrix.rotateM(mMMatrix,0,xAngle,1,0,0);    //设置绕x轴旋转
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle,1,false,Triangle.getFinalMatrix(mMMatrix),0);


        GLES20.glVertexAttribPointer(maPositionHandle,3,GLES20.GL_FLOAT,false,3*4,mVertexBuffer);//将顶点位置数据传送进渲染管线
        GLES20.glVertexAttribPointer(maColorHandle,4,GLES20.GL_FLOAT,false,4*4,mColorBuffer);//将顶点颜色数据传送进渲染管线

        GLES20.glEnableVertexAttribArray(maPositionHandle);//启动顶点位置数据
        GLES20.glEnableVertexAttribArray(maColorHandle);//启用顶点着色数据
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vCount);//执行绘制
    }


    //产生最终变换的矩阵
    public static float[]  getFinalMatrix(float[] spec){
        mMVPMatrix = new float[16];
        Matrix.multiplyMM(mMVPMatrix,0,mVMatrix,0,spec,0);//矩阵相乘
        Matrix.multiplyMM(mMVPMatrix,0,mProjMatrix,0,mMVPMatrix,0);
        return mMVPMatrix;
    }

}
































