package com.app1.venkatesh.braintrainers;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    Button GoButton;
    Button PlayButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int NumberOfQuestions = 0;
    int a;
    int b;
    int addition;
    int mul;
    int div;
    int sub;

    TextView ScoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView SumTextView;
    TextView timeTextView;
    ConstraintLayout gameLayout;

    public void Play(View view){
        PlayButton.setVisibility(View.INVISIBLE);
        score = 0;
        NumberOfQuestions =0;
        timeTextView.setText("30s");
        ScoreTextView.setText(Integer.toString(score)+ "/"+Integer.toString(NumberOfQuestions));
        newQuestion();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timeTextView.setText(String.valueOf(l/1000)+ "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time up");
                PlayButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void ChooseAnswer(View view){
        resultTextView.setVisibility(View.VISIBLE);
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct Answer");
            score++;
        }else{
            resultTextView.setText("Incorrect Answer");

    }
        NumberOfQuestions++;
        ScoreTextView.setText(Integer.toString(score)+ "/"+Integer.toString(NumberOfQuestions));
        newQuestion();

    }


    public void start(View view){
        GoButton.setVisibility(View.INVISIBLE);
        Play(findViewById(R.id.timeTextView));
        gameLayout.setVisibility(View.VISIBLE);

    }
    public void newQuestion(){
     int operator = getRandomNumber(4);
         a = getRandomNumber(21);
        b = getRandomNumber(21);
        switch (operator) {
            case 0:
                setDisplayText(a,b,"-");

                validate(sub(a,b));
                break;
            case 1:
                setDisplayText(a,b,"+");
                validate(add(a,b));
                break;
            case 2:
                setDisplayText(a,b,"*");
                validate(mul(a,b));
                break;
            case 3:
                setDisplayText(a,b,"/");
                validate(div(a,b));
                break;
                default:SumTextView.setText("  ");
                break;

        }

            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));


        }
    public void setDisplayText(int a, int b,String operator){
        SumTextView.setText(a + operator + b);
    }
    public void validate(int resVal){
        //Random random = new Random();
        //locationOfCorrectAnswer = random.nextInt(4);
        //System.out.println("I AM HERE");

//manager.getEngineByName()
        locationOfCorrectAnswer = getRandomNumber(4);
        answers.clear();

       // String cal = a + operator + b;

    //try {
      //  ScriptEngineManager manager = new ScriptEngineManager();
        //ScriptEngine engine = manager.getEngineByExtension("js");
        //Object result = engine.eval(cal);
        //int resVal =Integer.parseInt(result.toString());
    //System.out.println(resVal);
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
               answers.add(resVal);
               // variable();

            } else {
                int wrongAnswers = getRandomNumber(10);

                while (wrongAnswers == resVal) {
                    wrongAnswers = getRandomNumber(10);
                }
                answers.add(wrongAnswers);
            }


        }
    //}catch (Throwable e){
      //  e.printStackTrace();
    //}

    }
private int getRandomNumber(int bound){
    Random random = new Random();
    return random.nextInt(bound);
}

public int add(int a , int b){
    return (a+b);
}
public int sub(int a, int b){
    return (a-b);
}
public int mul(int a, int b){
    return (a*b);
}
public int div(int a,int b){
    if (b>a){
        a = b;
        b = a;

    }
    return (a/b);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoButton = findViewById(R.id.GoButton);
        resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        SumTextView = findViewById(R.id.SumTextView);
        ScoreTextView = findViewById(R.id.scoreTextView);
        timeTextView = findViewById(R.id.timeTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        PlayButton = findViewById(R.id.PlayButton);
        GoButton.setVisibility(View.VISIBLE);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);









    }


}
