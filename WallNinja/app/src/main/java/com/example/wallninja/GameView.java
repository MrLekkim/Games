package com.example.wallninja;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.View;

public class GameView extends View {

    //This is our custom View class
    Handler handler;//Handler is required to schedule a runnable after some delay
    Runnable runnable;
    final int UPDATE_MILLIS=30;
    Bitmap background;
    Display display;
    Point point;
    int dWith, dHeight; // Device width and height respectively
    Rect rect;

    // Creating a bitmap array for Ninja:
    Bitmap[] ninjas;


    public GameView(Context context) {
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();//This will call onDraw()
            }
        };
        background = BitmapFactory.decodeResource(getResources(),R.drawable.skybackground);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWith = point.x;
        dHeight = point.y;
        rect = new Rect(0,0,dWith,dHeight);

        ninjas = new Bitmap[2];
        ninjas[0] = BitmapFactory.decodeByteArray(get)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // We'll draw our view inside onDrav()
        // Draw the background on canvas
        //canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(background,null,rect ,null); // fixed background
        handler.postDelayed(runnable,UPDATE_MILLIS);
    }
}
