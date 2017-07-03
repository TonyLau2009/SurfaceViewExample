package jocelyn_test02.com.surfaceviewexample;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jocelyn on 24/7/2016.
 */
public class Menu extends AppCompatActivity {

    private Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = (Button) findViewById(R.id.mybutton01);
        myButton.setOnClickListener(new myButtonListener());
    }

    class myButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent();
            //通过intent .putExtra(键值对)传递信息到另一个activity
           // intent.putExtra("textIntent","Welcome Game!");
            intent.setClass(Menu.this,MainActivity.class);
            Menu.this.startActivity(intent);
        }
    }



}
