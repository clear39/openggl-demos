package com.xqli.helloworld.GL.charts;

import com.xqli.helloworld.GL.utils.BufferUtil;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Line extends BaseGraph {
    @Override
    public void draw(GL10 gl) {

        List<Float> coordsList = new ArrayList<Float>();

        coordsList.add(-1.0f);
        coordsList.add(-1.0f);
        coordsList.add(0.0f);

        coordsList.add(-1.0f);
        coordsList.add(1.0f);
        coordsList.add(0.0f);


        coordsList.add(1.0f);
        coordsList.add(1.0f);
        coordsList.add(0.0f);




        gl.glColor4f(1.0f,0.0f,0.0f,1.0f);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2FloatBuffer(coordsList)); // OpenGL docs.

        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, coordsList.size() / 3);


        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.

    }
}
