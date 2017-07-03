package jocelyn_test02.com.surfaceviewexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    OurView v;
    Bitmap ball,blob;
    float x = 0;
    float y = 0;



//重写onCreate方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = new OurView(this);
        v.setOnTouchListener(this);
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.a1);
        blob = BitmapFactory.decodeResource(getResources(), R.drawable.blob);
        setContentView(v);
    }

//    private void OnTuchListener() {
//        //setContentView(R.layout.activity_main);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        v.resume();
    }

//定义一个内部类 OurView 继承SurfaceView
    public class OurView extends SurfaceView implements Runnable {

    Thread t = null;
    SurfaceHolder holder;
    boolean isItOk = false;
    Sprite sprite;
    boolean spriteLoaded = false;

    //重写父类SurfaceView 构造器

        public OurView(Context context) {
            super(context);

            holder = getHolder();

        }

    //重写Runnable run();方法
        @Override
        public void run() {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sprite = new Sprite(OurView.this,blob);

            while(isItOk) {

                if (!holder.getSurface().isValid()) {
                    continue;
                }

//                if(!spriteLoaded){
//                     sprite = new Sprite(OurView.this,blob);
//                    spriteLoaded = true;
//                }

                Canvas c = holder.lockCanvas();
                onDraw(c);
                holder.unlockCanvasAndPost(c);
            }

        }
//绘画函数onDraw（Canvas canvas）
        protected void onDraw(Canvas canvas)
        {
            canvas.drawARGB(255, 165, 165, 165);
            canvas.drawBitmap(ball,x-(ball.getWidth()/2), y-(ball.getHeight()/2), null);
            sprite.onDraw(canvas);
        }

        public void pause() {
            isItOk = false;

            while(true) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume() {
            isItOk = true;
            t = new Thread(this);
            t.start();
        }


    }
    @Override
    public boolean onTouch(View v, MotionEvent me) {

//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        switch (me.getAction()){
         case MotionEvent.ACTION_UP:
             x = me.getX();
             y = me .getY();
         break;

         case MotionEvent.ACTION_DOWN:
             x = me.getX();
             y = me .getY();
             break;

         case MotionEvent.ACTION_MOVE:
             x = me.getX();
             y = me .getY();
             break;
        }
        return true;
    }
}


