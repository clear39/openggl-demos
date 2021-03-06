package com.xqli.helloworld.GL.charts;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square extends  BaseGraph{
    // Our vertices.
    private float vertices[] = {
            -1.0f,  1.0f, 0.0f,// 0, Top Left
            -1.0f, -1.0f, 0.0f, // 1, Bottom Left
             1.0f, -1.0f, 0.0f, // 2, Bottom Right
             1.0f,  1.0f, 0.0f, // 3, Top Right
    };


    // The order we like to connect them.
    private short[] indices = { 0, 1, 2, 0, 2, 3 };

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;

    public Square() {
        // a float is 4 bytes, therefore we
        // multiply the number if
        // vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
        // short is 2 bytes, therefore we multiply
        //the number if vertices with 2.
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }


     /**
     * This function draws our square on screen.
     * @param gl
     */
     public void draw(GL10 gl) {
         // Counter-clockwise winding.
         gl.glFrontFace(GL10.GL_CCW);
         // Enable face culling.
         gl.glEnable(GL10.GL_CULL_FACE);

         // What faces to remove with the face culling.
         gl.glCullFace(GL10.GL_BACK);

         // Enabled the vertices buffer for writing
         //and to be used during
         // rendering.
         gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
         // Specifies the location and data format of
         //an array of vertex
         // coordinates to use when rendering.
         gl.glVertexPointer(3, GL10.GL_FLOAT, 0,vertexBuffer);
         gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,GL10.GL_UNSIGNED_SHORT, indexBuffer);


         // Disable the vertices buffer.
         gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

         // cull(剔除), 缺省的逆时针方向代表面的“前面 Front

         // Disable face culling.
         gl.glDisable(GL10.GL_CULL_FACE);//打开 忽略“后面”设置


         //gl.glFrontFace(GL10.GL_CCW); //设置逆时针方法为面的“前面”

         //gl.glCullFace(GL10.GL_BACK);//明确指明“忽略“哪个面



     }
}
