package com.opengl.lib;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;




public class ShaderUtil {

    private static final String GLES20_TAG = "GLES20_ERROR";

   /**
     * 加载指定着色器
     * @param shareType //着色器类型
     * @param source    // 着色器的脚本类型
     * @return
     */
    public static int loadShader(int shareType,String source){

        int shader = GLES20.glCreateShader(shareType);

        if(shader != 0){
            GLES20.glShaderSource(shader,source);   //加载源码
            GLES20.glCompileShader(shader);         //编译
            int[] compiled = new int[1];
            //获取编译的状态
            GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,compiled,0);
            if(compiled[0] == 0){
                Log.e(GLES20_TAG, "Could not compile shader " + shareType + ":");
                Log.e(GLES20_TAG, GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    //创建着色器程序的方法
    public static int createProgram(String vertexSource,String fragmentSource){
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,vertexSource);
        if(vertexShader == 0){
            return  0;
        }

        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentSource);
        if(fragmentShader == 0){
            return  0;
        }


        int program = GLES20.glCreateProgram();
        if(program != 0){
            GLES20.glAttachShader(program,vertexShader);
            checkGlError("glAttachShader vertex");
            GLES20.glAttachShader(program,fragmentShader);
            checkGlError("glAttachShader fragment");

            GLES20.glLinkProgram(program);
            int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(program,GLES20.GL_LINK_STATUS,linkStatus,0);
            if(linkStatus[0] != GLES20.GL_TRUE){
                Log.e(GLES20_TAG, "Could not link program ");
                Log.e(GLES20_TAG, GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
            }
        }

        return program;
    }





    public static void checkGlError(String op){
        int error = 0;

        while((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR ){
            Log.e(GLES20_TAG, " error: " + op + ": glError " + error);
            throw  new RuntimeException(op + ": glError " + error);
        };
    }

    public static String loadFromAssetsFile(String fname, Resources r){
        String result = null;
        try{
            InputStream is = r.getAssets().open(fname);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = is.read()) != -1){
                baos.write(ch);
            }

            byte[] buff = baos.toByteArray();
            result = new String(buff,"UTF-8");
            result.replaceAll("\\r\\n","\n");

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}






















