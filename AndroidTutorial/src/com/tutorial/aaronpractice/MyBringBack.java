package com.tutorial.aaronpractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View{
	
	public static Bitmap ball;
	private float changingY = 0;
	public static Typeface font;
	
	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		// start - get an image
		/* An alternate way to do this:
		 * InputStream is = getResources().openRawResource(R.drawable.ic_menu_cloud);
		 * cloud = BitmapFactory.decodeStream(is);
		**/
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.aqua_ball);
		// end - get an image
		font = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		Paint textPaint = new Paint();
		textPaint.setARGB(50, 254, 10, 50);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		canvas.drawText("my bring back", ((float) (canvas.getWidth()/2)), ((float) (canvas.getHeight()/1.4)), textPaint);
		
		canvas.drawBitmap(ball, (canvas.getWidth()/2-ball.getWidth()/2), changingY, null);
		if(changingY < canvas.getHeight()){
			changingY += 10;
		} else{
			changingY = -ball.getHeight();
		}
		Rect middleRec = new Rect();
		//middleRec.set(left, top, right, bottom)
		middleRec.set((canvas.getWidth()/2 - 100), (canvas.getHeight()/2 - 100), (canvas.getWidth()/2 + 100), (canvas.getHeight()/2 + 100));
		Paint ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRec, ourBlue);
		invalidate();
	}
	
	
	
}
