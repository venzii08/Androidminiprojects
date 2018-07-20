package com.app1.venkatesh.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int n;

    public void generateRandomNumber(){

        Random rand = new Random();
        n = rand.nextInt(20) + 1;

    }
    public void guess(View view){
        EditText editText2 = (EditText) findViewById(R.id.editText2);

        String number = editText2.getText().toString();
        int number2 = Integer.parseInt(number);
        String Message;

        if (n < number2){
            Message = " Go lower";
        }
        else if (n > number2){
            Message = " Go higher";

        }
        else{

            Message = " You have to found it right";
            generateRandomNumber();

        }

        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        Log.i("Entered Value", editText2.getText().toString());
        Log.i("info", "button pressed");
        Log.i("Random Number ", Integer.toString(n));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandomNumber();


    }
}
