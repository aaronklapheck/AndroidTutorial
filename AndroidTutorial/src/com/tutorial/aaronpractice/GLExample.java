package com.tutorial.aaronpractice;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLExample extends Activity{
	
	private static final int TRIANGLE = 0;
	private static final int CUBE = 1;
	private int option = 1;
	
	GLSurfaceView surface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		surface = new GLSurfaceView(this.getApplicationContext());
		switch(option){
		case TRIANGLE:
			surface.setRenderer(new GLRendererEx());
			break;
		case CUBE:
			surface.setRenderer(new GLCubeRendererEx());
			break;
		}
		setContentView(surface);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		surface.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		surface.onPause();
	}

}
