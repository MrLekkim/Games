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
import android.view.MotionEvent;
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
    // We need an integet variable to keep track of the ninja image/frame
    int ninjaFrame = 0;
    int velocity=0,gravity=3; //
    int ninjaX, ninjaY;


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
        ninjas[0] = BitmapFactory.decodeResource(getResources(),R.drawable.ninja);
        ninjas[1] = BitmapFactory.decodeResource(getResources(),R.drawable.ninja2);
        ninjaX = dWith/2 - ninjas[0].getWidth()/2; //Initally ninja will be in centre
        ninjaY = dHeight/2 - ninjas[0].getHeight()/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // We'll draw our view inside onDrav()
        // Draw the background on canvas
        //canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(background,null,rect ,null); // fixed background
        if(ninjaFrame==0){
            ninjaFrame = 1;
        }else{
            ninjaFrame = 0;
        }

        velocity += gravity;
        ninjaY += velocity;

        // This will place the ninja on the center of the screen.
        // Both ninjas[0] and ninjas[1] have same dimension.
        canvas.drawBitmap(ninjas[ninjaFrame],dWith/2 - ninjaX, ninjaY,null);
        handler.postDelayed(runnable,UPDATE_MILLIS);
    }
    //Get touch event


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){ //this is tap is detected on screen
            //Here we want the bird to move upwards by some unit
            velocity = - 30; // 30 units on onward direction

        }

        return true; //By returning true in indicates that we've done with touch event and no further action is required by Android
    }
}
