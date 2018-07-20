package com.app1.venkatesh.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertCurrency(View view){
        Log.i("info", "Button pressed");
        EditText editText = (EditText) findViewById(R.id.editText);
        String amountInPound = editText.getText().toString();
        double amountInPoundsDouble = Double.parseDouble(amountInPound);
        double amountInDollarsDouble = amountInPoundsDouble * 1.3;
        String amountInDollarsString = String.format("%.2f",amountInDollarsDouble);
        Toast.makeText(this, "pounds" + amountInPound + " is $" + amountInDollarsString, Toast.LENGTH_SHORT).show();
        Log.i("Amount in dollars", amountInDollarsString);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
