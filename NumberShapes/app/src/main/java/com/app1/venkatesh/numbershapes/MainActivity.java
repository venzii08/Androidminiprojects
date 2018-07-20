package com.app1.venkatesh.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




            class Number {

                int number;

                public boolean isSquare() {

                    double squareRoot = Math.sqrt(number);

                    if (squareRoot == Math.floor(squareRoot)) {

                        return true;
                    } else {

                        return false;

                    }

                }

                public boolean isTriangular() {

                    int x = 1;

                    int triangularNumber = 1;

                    while (triangularNumber < number) {

                        x++;
                        triangularNumber = triangularNumber + x;
                    }

                    if (triangularNumber == number) {
                        return true;
                    } else {
                        return false;
                    }

                }
45

            }




    public void testNumber(View view){


        Log.i("info", "button pressed");

        EditText editText = (EditText) findViewById(R.id.editText);

        Number myNumber  = new Number();

        myNumber.number = Integer.parseInt(editText.getText().toString());

        String message = editText.getText().toString();

        if(editText.getText().toString().isEmpty()){

            message = " please enter the number";
        }else {


            if (myNumber.isSquare() && myNumber.isTriangular()) {4

                message += "is Square and triangular";

            } else if (myNumber.isSquare()) {

                message += " is square not triangular.";
            } else if (myNumber.isTriangular()) {

                message += " is triangular not Square.";
            } else {

                message += " is not both Triangular and Square.";
            }
        }
         Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        System.out.println(myNumber.isSquare());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
