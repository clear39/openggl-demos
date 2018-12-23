package com.xqli.helloworld.GL.lesson2;

import android.opengl.GLU;

import com.xqli.helloworld.GL.BaseRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyDemoRenderer extends BaseRenderer {

    private float ratio;

    //±í²ãŽŽœšÊ±
    public void onSurfaceCreated(GL10 gl,   EGLConfig  config) {
        //ÉèÖÃÇåÆÁÉ«
        gl.glClearColor(0, 0, 0, 1);
        //ÆôÓÃ¶¥µã»º³åÇø.

    }

    //±í²ãsizeÊ±
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //ÉèÖÃÊÓ¿Ú,Êä³ö»­ÃæµÄÇøÓò
        gl.glViewport(0, 0, width, height);

        ratio = (float)width / (float)height;

        //ŸØÕóÄ£Êœ,Í¶Ó°ŸØÕó,openGL»ùÓÚ×ŽÌ¬»ú
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //ŒÓÔØµ¥Î»ŸØÕó
        gl.glLoadIdentity();
        //ÆœœØÍ·Ìå
        gl.glFrustumf(-1f, 1f, -ratio, ratio, 3, 7);
    }

    //»æÍŒ
    public void onDrawFrame(GL10 gl) {
        //eyex,eyey,eyez:·ÅÖÃÑÛÇòµÄ×ø±ê
        //centerx,centery,centerz:ÑÛÇòµÄ¹Û²ìµã.
        //upx,upx,upx:Öž¶šÑÛÇòÏòÉÏµÄÏòÁ¿

        //Çå³ýÑÕÉ«»ºŽæÇø
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        //Ä£ÐÍÊÓÍŒŸØÕó
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();//ŒÓÔØµ¥Î»ŸØÕó
        GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);

        //»­ÈýœÇÐÎ
        //»æÖÆÊý×é
        //ÈýœÇÐÎ×ù±ê
        float[] coords = {
                0f,ratio,0f,
                -1f,-ratio,0f,
                1f,-ratio,0f
        };

        //·ÖÅä×ÖœÚ»ºŽæÇø¿ÕŒä,Žæ·Å¶¥µã×ø±êÊýŸÝ
        ByteBuffer ibb = ByteBuffer.allocateDirect(coords.length * 4);
        //ÉèÖÃµÄË³Ðò(±ŸµØË³Ðò)
        ibb.order(ByteOrder.nativeOrder());
        //·ÅÖÃ¶¥µã×ø±êÊý×é
        FloatBuffer fbb = ibb.asFloatBuffer();
        fbb.put(coords);
        //¶šÎ»ÖžÕëµÄÎ»ÖÃ,ŽÓžÃÎ»ÖÃ¿ªÊŒ¶ÁÈ¡¶¥µãÊýŸÝ
        ibb.position(0);

        //ÉèÖÃ»æÍŒÑÕÉ«,ºìÉ«
        gl.glColor4f(1f, 0f, 0f, 1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //3:3Î¬µã,Ê¹ÓÃÈýžö×ø±êÖµ±íÊŸÒ»žöµã
        //type:Ã¿žöµãµÄÊýŸÝÀàÐÍ
        //stride:0,¿ç¶È.
        //ibb:Öž¶š¶¥µã»º³åÇø
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, ibb);
        //»æÖÆÈýœÇÐÎ
        //0:ÆðÊŒµã:
        //3:»æÖÆµãµÄÊýÁ¿
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
