package com.app1.venkatesh.interactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void interactivitydemo (View view){
        Log.i("Info","Button pressed");
        ImageView iv = (ImageView)findViewById(R.id.iv);
        iv.setImageResource(R.drawable.bull1);
        iv.setImageResource(R.drawable.bull2);




    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
