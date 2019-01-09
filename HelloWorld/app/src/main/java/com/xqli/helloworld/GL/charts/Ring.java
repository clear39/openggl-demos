package com.xqli.helloworld.GL.charts;

import com.xqli.helloworld.GL.utils.BufferUtil;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * 画一个环
 */

public class Ring extends BaseGraph {
    @Override
    public void draw(GL10 gl) {

        //计算坐标
        List<Float> coordsList = new ArrayList<Float>();

        float Rinner = 0.2f; //内环半径
        float Rring = 0.3f; //环半径
        int count = 10;  //内环划分数量
        float alphaStep = (float)(2*Math.PI / count);
        float alpha = 0;
        float x0,y0,z0,x1,y1,z1;


        float countRing = 10; //环划分数量
        float betaStep = (float)(2*Math.PI / countRing);
        float beta = 0;

        for (int i = 0; i <= count; i++) {

            alpha = i * alphaStep;

            for(int j = 0;j <= countRing ; j++){
                beta = betaStep * j;
                x0 = (Rring*(1+(float)Math.cos(beta)) + Rinner) * (float)Math.cos(alpha);
                y0 = (Rring*(1+(float)Math.cos(beta)) + Rinner) * (float)Math.sin(alpha);
                z0 = - Rring * (float) Math.sin(beta);

                x1 = (Rring*(1+(float)Math.cos(beta)) + Rinner) * (float)Math.cos(alpha + alphaStep);
                y1 = (Rring*(1+(float)Math.cos(beta)) + Rinner) * (float)Math.sin(alpha + alphaStep);
                z1 = - Rring * (float) Math.sin(beta);

                coordsList.add(x0);
                coordsList.add(y0);
                coordsList.add(z0);

                coordsList.add(x1);
                coordsList.add(y1);
                coordsList.add(z1);
            }

        }




        gl.glColor4f(1.0f,0.0f,0.0f,1.0f);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2FloatBuffer(coordsList)); // OpenGL docs.

        gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, coordsList.size() / 3);

        //gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP , 0,coordsList.size() / 3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.


    }
}
