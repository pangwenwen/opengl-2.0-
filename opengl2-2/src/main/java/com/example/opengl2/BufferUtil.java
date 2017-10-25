package com.example.opengl2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class BufferUtil {
	public static FloatBuffer mBuffer;
	public static FloatBuffer floatToBuffer(float[] a){
		//�ȳ�ʼ��buffer������ĳ���*4����Ϊһ��floatռ4���ֽ�
		ByteBuffer mbb = ByteBuffer.allocateDirect(a.length*4);
		//����������nativeOrder
		mbb.order(ByteOrder.nativeOrder());
		mBuffer = mbb.asFloatBuffer();
		mBuffer.put(a);
		mBuffer.position(0);
		return mBuffer;
	}
	
	public static IntBuffer intToBuffer(int[] a){
		
		IntBuffer intBuffer;
		//�ȳ�ʼ��buffer������ĳ���*4����Ϊһ��floatռ4���ֽ�
		ByteBuffer mbb = ByteBuffer.allocateDirect(a.length*4);
		//����������nativeOrder
		mbb.order(ByteOrder.nativeOrder());
		intBuffer = mbb.asIntBuffer();
		intBuffer.put(a);
		intBuffer.position(0);
		return intBuffer;
	}

	public static ShortBuffer shortToBuffer(short[] a){
		ShortBuffer shortBuffer;
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(a.length*2);
		byteBuffer.order(ByteOrder.nativeOrder());

		shortBuffer = byteBuffer.asShortBuffer();
		shortBuffer.put(a);
		shortBuffer.position(0);
		return shortBuffer;
	}
}