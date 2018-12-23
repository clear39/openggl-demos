package com.xqli.helloworld.GL.charts;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {

    private final float[] vertices = {
            0f,1f,0f,
            -1f,-1f,0f,
            1f,-1f,0f
    };

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    public Triangle(){
        ByteBuffer ibb = ByteBuffer.allocateDirect(vertices.length * 4);
        ibb.order(ByteOrder.nativeOrder());
        vertexBuffer = ibb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }


    public void draw(GL10 gl) {
        gl.glColor4f(1.0f,0.0f,0.0f,1.0f);
        // Enabled the vertex buffer for writing and to be used during rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.
        // Specifies the location and data format of an array of vertex coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer); // OpenGL docs.
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
        //When you are done with the buffer don't forget to disable it.
        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.


    }
}
