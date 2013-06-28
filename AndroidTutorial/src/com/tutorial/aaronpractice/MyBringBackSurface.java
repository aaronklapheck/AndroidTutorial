package com.tutorial.aaronpractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * MyBringBackSurface is a surface view, so set your content view to this class, 
 * for example:
 * <pre class="prettyprint">
 * setContentView(new MyBringBackSurface(context, integer));
 * </pre>
 * MyBringBackSurface will display a moving ball. The only thing that this surface view 
 * needs is a supply of x and y values. The two main features of this program are outlined
 * below:
 * <ul>
 * 		<li>current position - displays a ball in real time, see {@link setPosition}</li>
 * 		<li>start/stop position - displays the balls starting and ending locations,
 * 			 see {@link setStartPosition} and {@link setEndPosition}</li>
 * </ul>
 * <p></p>
 * It is recommended to use pause this resource when it is not in use and resume it after it is.
 * this can be done using the following code:
 * <pre class="prettyprint">
 *	protected void onResume() {
 *		super.onResume();
 *		myBringBackSurface.resume();
 *	}
 *
 *	protected void onPause() {
 *		super.onPause();
 *		myBringBackSurface.pause();
 *	}
 * </pre>

 * @author Aaron Klapheck
 *
 */
public class MyBringBackSurface extends SurfaceView implements Runnable{

	private SurfaceHolder holder;
	private Thread animation = null;
	private boolean isRunning = false;
	private float x = 0, y = 0, // current ball location.
			sX = 0, sY = 0, fX = 0, fY = 0, // starting and ending positions of ball.
			dx = 0, dy = 0; // starting - ending positions of ball.
	private float aniX = 0, aniY = 0, scaledX = 0, scaledY = 0;
	private int scaleCon = 30;
	private final Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.aqua_ball);
	private final Bitmap plus = BitmapFactory.decodeResource(getResources(), R.drawable.aqua_ball_plus);
	
	//sets which program will be using this activity.
	// 1 = GFXSurface
	// 2 = Accelerate
	private int program; 
	private static final int GFXSURFACE = 1;
	private static final int ACCELERATE = 2;
	
	
	// Animation basics
	private static final int fps = 60;
	private long frameLength = 1000000000 / fps;
	private float xInt = 0, x1 = 0, x2 = 0, x3 = 0, x4 = 0;
	private float yInt = 0, y1 = 0, y2 = 0, y3 = 0, y4 = 0;
	
	public MyBringBackSurface(Context context, int i) {
		super(context);
		// TODO Auto-generated constructor stub
		program = i;
		holder = getHolder();
	}
	
	/**
	 * Sets the current position of the ball. This will display a round ball.
	 * 
	 * @param xPos - sets x position
	 * @param yPos - sets y position
	 */
	public void setPosition(float xPos, float yPos){
		x = xPos;
		y = yPos;
	}
	
	/**
	 * Sets the starting position of the ball. This will display a "+" sign at this location.
	 * 
	 * @param sxPos - sets x position
	 * @param syPos - sets y position
	 */
	public void setStartPosition(float sxPos, float syPos){
		sX = sxPos;
		sY = syPos;
		dx = dy = aniX = aniY = scaledX = scaledY = fX = fY = 0;
	}
	
	/**
	 * Sets the ending position of the ball. This will display a "+" sign at this location. 
	 * Sets the change in position.
	 * 
	 * @param fxPos - sets x position
	 * @param fyPos - sets y position
	 */
	public void setEndPosition(float fxPos, float fyPos){
		fX = fxPos;
		fY = fyPos;
		dx = fX - sX;
		dy = fY - sY;
		scaledX = dx/scaleCon;
		scaledY = dy/scaleCon;
		x = y = 0;
	}

	/**
	 * Destroys the animation thread from using resources when the user navigates away from it.
	 * 
	 */
	public void pause(){
		isRunning = false;
		while(true){
			try {
				animation.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		animation = null;
	}
	
	/**
	 * Starts the animation thread.
	 */
	public void resume(){
		isRunning = true;
		animation = new Thread(this);
		animation.start();
	}
	
	/**
	 * The main animation coding. This code runs in a new thread.
	 */
	public void run() {
		// TODO Auto-generated method stub
		Canvas canvas;
		
		long ns = System.nanoTime();
		
		while(isRunning){
			
			if(!holder.getSurface().isValid()){
				continue;
			}
			canvas = holder.lockCanvas();
			canvas.drawRGB(2, 2, 150);
			
			// Determine which program is calling this method.
			if(program == GFXSURFACE){
				drawToCanvasGFX(canvas);
			} else if(program == ACCELERATE){
				drawToCanvasAcc(canvas);
			}
			holder.unlockCanvasAndPost(canvas);
			
			try {
				ns += frameLength;
				Thread.sleep(Math.max(0, (ns - System.nanoTime())/10000000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Coding for activity Accelerate.
	 */
	private void drawToCanvasAcc(Canvas canvas) {
		// TODO Auto-generated method stub
		
		// Integration and variables.
		x4 = x3;
		x3 = x2;
		x2 = x1;
		x1 = x;
		xInt = (x1 + x2 + x3 + x4)/4;
		//xInt = x;
		
		y4 = y3;
		y3 = y2;
		y2 = y1;
		y1 = y;
		yInt = (y1 + y2 + y3 + y4)/4;
		//yInt = y;
		
		
		float centerX = canvas.getWidth()/2 - test.getWidth()/2;
		float centerY = canvas.getHeight()/2 - test.getHeight()/2;
		canvas.drawBitmap(test, (centerX - xInt*40), (centerY + yInt*55), null);
	}


	/**
	 * Coding for activity GFXSurface.
	 */
	private void drawToCanvasGFX(Canvas canvas){
		if(x != 0 && y != 0){
			canvas.drawBitmap(test, (x - test.getWidth()/2), (y - test.getHeight()/2), null);
		}
		if(sX != 0 && sY != 0){
			canvas.drawBitmap(plus, (sX - plus.getWidth()/2), (sY - plus.getHeight()/2), null);
		}
		if(fX != 0 && fY != 0){
			canvas.drawBitmap(test, (fX - test.getWidth()/2) - aniX, (fY - test.getHeight()/2) - aniY, null);
			canvas.drawBitmap(plus, (fX - plus.getWidth()/2), (fY - plus.getHeight()/2), null);
		}
		aniX += scaledX;
		aniY += scaledY;
	}

}
