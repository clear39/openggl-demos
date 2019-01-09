package com.xqli.helloworld.GL.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;


public class BufferUtil {

	public static ByteBuffer arr2ByteBuffer(float[] arr){
		ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length * 4);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		fbb.put(arr);
		ibb.position(0);
		return ibb ;
	}
	
	/**
	 * ����������ת�����ֽڻ�����
	 */
	public static ByteBuffer arr2ByteBuffer(byte[] arr){
		ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length);
		ibb.order(ByteOrder.nativeOrder());
		ibb.put(arr);
		ibb.position(0);
		return ibb ;
	}
	
	/**
	 * ��listת�����ֽڻ�����
	 */
	public static ByteBuffer list2ByteBuffer(List<Float> list){
		ByteBuffer ibb = ByteBuffer.allocateDirect(list.size() * 4);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		for(Float f : list){
			fbb.put(f);
		}
		ibb.position(0);
		return ibb ;
	}
	

	public static FloatBuffer list2FloatBuffer(List<Float> list){
		ByteBuffer ibb = ByteBuffer.allocateDirect(list.size() * 4);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		for(Float f : list){
			fbb.put(f);
		}
		fbb.position(0);
		return fbb ;
	}

	public static FloatBuffer arr2FloatBuffer(float[] coords) {
		ByteBuffer ibb = ByteBuffer.allocateDirect(coords.length * 4);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		fbb.put(coords);
		fbb.position(0);
		return fbb ;
	}
	

}
