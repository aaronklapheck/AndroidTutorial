package com.tutorial.aaronpractice;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEx {
	
	private float verts[] = {
			0, 1, // verticy 1
			1, -1, 
			-1, -1};
	
	private float rgbaVals[] = {
		1, 1, 0, .5f, // color 1
		.25f, 0, 0.85f, 1, 
		0, 1, 1, 1
		};
	private FloatBuffer vertBuff, colorBuff;
	private short[] pIndex = {0, 1, 2};
	private ShortBuffer pBuff;
	
	public GLTriangleEx(){
		ByteBuffer bBuffer = ByteBuffer.allocateDirect(verts.length*4); // float is 4 bytes
		bBuffer.order(ByteOrder.nativeOrder());
		vertBuff = bBuffer.asFloatBuffer();
		vertBuff.put(verts).position(0);
		
		ByteBuffer pBuffer = ByteBuffer.allocateDirect(pIndex.length*2); // short is 2 bytes
		pBuffer.order(ByteOrder.nativeOrder());
		pBuff = pBuffer.asShortBuffer();
		pBuff.put(pIndex).position(0);
		
		ByteBuffer cBuffer = ByteBuffer.allocateDirect(rgbaVals.length*4); // float is 4 bytes
		cBuffer.order(ByteOrder.nativeOrder());
		colorBuff = cBuffer.asFloatBuffer();
		colorBuff.put(rgbaVals).position(0);
	}
	
	
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
}
