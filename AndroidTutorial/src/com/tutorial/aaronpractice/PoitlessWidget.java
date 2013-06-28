package com.tutorial.aaronpractice;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class PoitlessWidget extends AppWidgetProvider{

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		String ran = String.valueOf(new Random().nextInt(10000));
		
		final int N = appWidgetIds.length;
		for(int i = 0; i < N; i++){
			RemoteViews v = new RemoteViews(context.getPackageName(), R.layout.simple_widget);
			v.setTextViewText(R.id.tvWidgetUpdate, ran);
			appWidgetManager.updateAppWidget(appWidgetIds[i], v);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
	}

}
