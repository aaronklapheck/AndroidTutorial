package com.tutorial.aaronpractice;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCube {

	private float verts[] = { 
			1, 1, -1, // pt0 - top front right.
			1, -1, -1, // pt1 - bottom front right
			-1, -1, -1, // pt2 - bottom front left
			-1, 1, -1, // pt3 - top front left
			
			1, 1, 1, // pt4 - top back right.
			1, -1, 1, // pt5 - bottom back right
			-1, -1, 1, // pt6 - bottom back left
			-1, 1, 1 // pt7 - top back left
			};
	
	private float rgbaVals[] = {
			1, 1, 0, .5f, // color 1
			.25f, 0, 0.85f, 1, 
			0, 1, 1, 1,
			0.7f, 0, 0, 1,
			1, 1, 0, .5f, 
			.25f, 0, 0.85f, 1, 
			0, 0.4f, 1, 1,
			1, 0, 1, 1
			};
	
	private FloatBuffer vertBuff, colorBuff;
	private short[] pIndex = { 
			/*
			 * Points on cube look like this:
			
			 Front (standing on front-side looking at the front of the object)		
			3-----0
			|     |
			|     |
			2-----1
			
			 Back  (standing on front-side looking at the front of the object)
			7-----4		
			|     |
			|     |
			6-----5
			
			Points 4, 5, 6, and 7 would be hidden from view at this position.
			*/
			// break the cube into four smaller pyramid-looking objects composed only of
			// triangles on a side. Make sure points are listed in CW order from the
			// point of view of a person looking at the outside of the solid.
			3, 4, 0,  0, 4, 1,  3, 0, 1,
			3, 7, 4,  7, 6, 4,  7, 3, 6,
			3, 1, 2,  1, 6, 2,  6, 3, 2,
			1, 4, 5,  5, 6, 1,  4, 6, 5
	};
	private ShortBuffer pBuff;

	public GLCube() {
		ByteBuffer bBuffer = ByteBuffer.allocateDirect(verts.length * 4); // float
																			// is
																			// 4
																			// bytes
		bBuffer.order(ByteOrder.nativeOrder());
		vertBuff = bBuffer.asFloatBuffer();
		vertBuff.put(verts).position(0);

		ByteBuffer pBuffer = ByteBuffer.allocateDirect(pIndex.length * 2); // short
																			// is
																			// 2
																			// bytes
		pBuffer.order(ByteOrder.nativeOrder());
		pBuff = pBuffer.asShortBuffer();
		pBuff.put(pIndex).position(0);
		
		ByteBuffer cBuffer = ByteBuffer.allocateDirect(rgbaVals.length * 4); // float is 4 bytes
		cBuffer.order(ByteOrder.nativeOrder());
		colorBuff = cBuffer.asFloatBuffer();
		colorBuff.put(rgbaVals).position(0);
	}

	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnable(GL10.GL_CULL_FACE); // hide unseen faces to save processor power.
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length,
				GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}

}
