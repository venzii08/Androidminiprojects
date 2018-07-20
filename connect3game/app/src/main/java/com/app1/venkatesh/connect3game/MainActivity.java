package com.app1.venkatesh.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // 0: yellow , 1;red , 2:empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions ={{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},};

     int activeplayer = 0;

     boolean gameActive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activeplayer;

            counter.setTranslationY(-1500);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }


            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    //someone has won */

                    gameActive = false;

                    String winner = "";
                    if (activeplayer == 1) {
                        winner = "yellow";
                    } else  {
                        winner = "red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if(counterState == 2) gameIsOver = false;
                    }
                    if(gameIsOver) {
                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                        winnerTextView.setText("draw");

                        playAgainButton.setVisibility(View.VISIBLE);

                        winnerTextView.setVisibility(View.VISIBLE);

                }
        }
    }}}
    public void playAgain(View view){

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i =0; i<gridLayout.getChildCount(); i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for(int i = 0; i<gameState.length; i++){

            gameState [i] = 2;
        }


        activeplayer = 0;

        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
