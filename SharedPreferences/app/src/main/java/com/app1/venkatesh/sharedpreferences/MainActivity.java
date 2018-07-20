package com.app1.venkatesh.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.app1.venkatesh.sharedpreferences", Context.MODE_PRIVATE );

        sharedPreferences.edit().putString("username", "Venki").apply();

        String username = sharedPreferences.getString("username", "");

        Log.i("This is the username ", username);


    }
}
