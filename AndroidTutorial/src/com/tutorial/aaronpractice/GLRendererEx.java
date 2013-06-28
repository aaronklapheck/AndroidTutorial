package com.tutorial.aaronpractice;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class GLRendererEx implements Renderer{
	
	private GLTriangleEx tri;
	
	public GLRendererEx(){
		tri = new GLTriangleEx();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0.8f, 0f, 0.2f, 1f);
		gl.glClearDepthf(1f);
	}
	
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		// GLU.gluLookAt(gl, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
		GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 2, 0);
		
		tri.draw(gl);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width/height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		// sets rendering distances. zFar is the length that the object will no longer appear.
		// gl.glFrustumf(left, right, bottom, top, zNear, zFar)
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}

}
