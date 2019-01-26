package com.zenith.voix;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


public class ChatHeadService extends Service {


    private WindowManager windowManager;
    private ImageView chatHead;
    private Button mybutton;
    WindowManager.LayoutParams params;
    protected static final int RESULT_SPEECH = 1;


    @Override
    public void onCreate() {
        super.onCreate();

       windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatHead = new ImageView(this);
        chatHead.setImageResource(R.drawable.mike);
       // gestureDetector = new GestureDetector(this, onSingleTapConfirmed());

       // button =new Button(this);


        params= new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
       /* chatHead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "this is onclick", Toast.LENGTH_SHORT).show();

            }
        });*/
        //this code is for dragging the chat head
        chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            boolean shouldClick;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        shouldClick = true;
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                       // Toast.makeText(getApplicationContext(), "this is down", Toast.LENGTH_LONG).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (shouldClick) {

                            Intent i = new Intent();
                            i.setClass(getApplicationContext(), Test.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                        }
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX
                                + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY
                                + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatHead, params);
                        shouldClick = false;
                        return true;
                }

               /* float x = event.getX();
                float y = event.getY();
                if (x > outRectHit.left && x < outRectHit.right &&
                        y > outRectHit.top && y < outRectHit.bottom){
                    ... Handle this accordingly*/
                         return false;
            }
        });
        windowManager.addView(chatHead, params);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null)
            windowManager.removeView(chatHead);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
