package com.xqli.helloworld.GL.charts;


import com.xqli.helloworld.GL.utils.BufferUtil;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * 球形
 */
public class Sphere extends BaseGraph{

    @Override
    public void draw(GL10 gl) {

        //计算球体坐标
        List<Float> coordsList = new ArrayList<Float>();

        float R = 0.7f; //球的半径
        int stack = 16; //球的水平层数
        float stackStep = (float) (Math.PI / stack);//单位角度值

        int slice = 24;
        float sliceStep = (float)(Math.PI/slice);   //水平圆递增的角度

        float r0,r1;
        float x0,y0,z0,x1,y1,z1;
        float alpha0 = 0.0f,alpha1 = 0.0f;
        float beta = 0;

        for (int i = 0 ; i < stack; i++){
            //这里将角度左移 Math.PI / 2
            alpha0 = (float)(-Math.PI / 2 + i * stackStep) ; //
            alpha1 = (float)(-Math.PI / 2 + (i+1)* stackStep);

            r0 = R * (float) Math.cos(alpha0);
            y0 = R * (float) Math.sin(alpha0);

            r1 = R * (float) Math.cos(alpha1);
            y1 = R * (float) Math.sin(alpha1);


            for(int j = 0; j < slice * 2; j++){
                beta = j * sliceStep;

                x0 = r0 * (float)Math.cos(beta);
                z0 = - r0 * (float) Math.sin(beta);

                x1 = r1 * (float)Math.cos(beta);
                z1 = - r1 * (float) Math.sin(beta);

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
