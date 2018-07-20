package com.app1.venkatesh.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView myListView;
    int j;


    public void calculate(int number){
        ArrayList<String> content = new ArrayList<String>();
         for(j=1; j <= 10; j++){
             content.add(Integer.toString( j * number));

         }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,content);
        myListView.setAdapter(arrayAdapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar numberControl = findViewById(R.id.volumeSeekBar);
        myListView = findViewById(R.id.myListView);
        int max = 20;
        int startingposition = 10;

        numberControl.setMax(max);
        numberControl.setProgress(startingposition);

        calculate(startingposition);

        numberControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min = 1;
                int number;
                if(i<min){
                    number = min;
                    numberControl.setProgress(min);
                }else{
                    number = i;

                }

                Log.i("Seekbar position", Integer.toString(number));
                calculate(number);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
