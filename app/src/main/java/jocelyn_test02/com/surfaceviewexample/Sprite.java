package jocelyn_test02.com.surfaceviewexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Jocelyn on 23/7/2016.
 */
public class Sprite{

    int x, y;
    int xSpeed, ySpeed;
    int hight, width;
    MainActivity.OurView ov;
    Bitmap b;
    int currentFrame = 0;
    int direction = 3;
        //构造函数
       public Sprite(MainActivity.OurView ourView, Bitmap blob){

           this.ov = ourView;
           this.b = blob;
           // 图片是4行4列 4rows 4cols
           this.hight = b.getHeight()/4;
           this.width = b.getWidth()/4;
           x = y =0;
           xSpeed = 8;
           ySpeed = 0;

       }

        private void update() {
            //0 = up
            //1 = down
            //2 = left
            //3 = right
            // facing down

           if(x > ov.getWidth()-width-xSpeed) {
               xSpeed = 0;
               ySpeed = 8;
               direction = 1;
           }
            //going left
            if(y > ov.getHeight()-hight-ySpeed) {
                ySpeed = 0;
                xSpeed = -8;
                direction = 2;
            }
            //facing up
            if(x + xSpeed < 0) {
                x = 0;
                xSpeed = 0;
                ySpeed = -8;
                direction = 0;
            }
            // going right
            if(y + ySpeed < 0) {
                y = 0;
                ySpeed = 0;
                xSpeed = 8;
                direction = 3;
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentFrame = ++currentFrame % 4;
            //System.out.println(currentFrame);
            x += xSpeed;
            y += ySpeed;
        }

        public void onDraw(Canvas canvas) {
            update();
            int srcX = currentFrame * width;
            int srcY = direction * hight;
            //控制画布中的截取位和大小
            Rect src = new Rect(srcX,srcY,srcX+width,srcY+hight);
            //控制在画布中截取到的图片在画布中实际显示位和大小
            Rect dst = new Rect(x,y,x+width,y+hight);
            canvas.drawBitmap(b,src,dst,null);

        }


}
