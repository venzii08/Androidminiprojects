package com.app1.venkatesh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
     public void fade(View view){

         Log.i("info", "ImageView tapped");

         ImageView imageView = (ImageView) findViewById(R.id.imageView);


         imageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setX(-1000);

        imageView.animate().translationXBy(1000).rotation(3600).setDuration(2000);


    }
}
