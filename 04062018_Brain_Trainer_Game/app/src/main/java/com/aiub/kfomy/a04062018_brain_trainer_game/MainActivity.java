package com.aiub.kfomy.a04062018_brain_trainer_game;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button op1,op2,op3,op4;
    TextView timerText,scoreText,expression,finalScore,gameOver;
    CountDownTimer countDownTimer;
    Random rand;
    int num1,num2,result,locationOfAnswer,wrongAnswer,correctAnswer;
    Random op;
    int score;
    ArrayList<Button> option;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        option=new ArrayList<Button>();
        score=0;
        rand=new Random();
        btnStart=findViewById(R.id.btnStart);
        btnStart.setVisibility(View.VISIBLE);

        op1=findViewById(R.id.option1);
        option.add(op1);

        op2=findViewById(R.id.option2);
        option.add(op2);

        op3=findViewById(R.id.option3);
        option.add(op3);

        op4=findViewById(R.id.option4);
        option.add(op4);

        for(int i=0;i<4;i++){
            option.get(i).setVisibility(View.GONE);
        }

        finalScore=findViewById(R.id.score);
        finalScore.setVisibility(View.GONE);
        gameOver=findViewById(R.id.gameOver);
        gameOver.setVisibility(View.GONE);
        timerText=findViewById(R.id.timerText);
        timerText.setVisibility(View.GONE);
        scoreText=findViewById(R.id.scoreText);
        scoreText.setVisibility(View.GONE);
        expression=findViewById(R.id.expression);
        expression.setVisibility(View.GONE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(view);
            }
        });
    }

    public void startGame(View view ){
        btnStart.setVisibility(View.GONE);
        finalScore.setVisibility(View.GONE);
        gameOver.setVisibility(View.GONE);
        score=0;
        scoreText.setText("0");


        for(int i=0;i<4;i++){
            option.get(i).setVisibility(View.VISIBLE);
        }
        timerText.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        expression.setVisibility(View.VISIBLE);

        controllTimer();
        generateQuestion();
            for(int i=0;i<4;i++){
                option.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button b=(Button)view;
                        if(b.getText().equals(Integer.toString(correctAnswer))){
                            Toast.makeText(MainActivity.this,"Correct !!!",Toast.LENGTH_SHORT).show();
                            score=score+10;
                            scoreText.setText(Integer.toString(score));
                            generateQuestion();

                        }
                        else{
                            Toast.makeText(MainActivity.this,"Oops! Wrong Answer",Toast.LENGTH_SHORT).show();
                            generateQuestion();

                        }
                    }
                });

            }

            }




    public void generateQuestion() {
        num1=rand.nextInt(21);
        num2=rand.nextInt(21);
        correctAnswer=num1+num2;
        wrongAnswer=rand.nextInt(41);
        locationOfAnswer=rand.nextInt(4);
        expression.setText(Integer.toString(num1)+"+"+Integer.toString(num2));
        for(int i=0;i<4;i++){
            if(i==locationOfAnswer){
                option.get(i).setText(Integer.toString(correctAnswer));
            }
            else{
                wrongAnswer=rand.nextInt(41);
                while(correctAnswer==wrongAnswer){

                    wrongAnswer=rand.nextInt(41);
                }

                option.get(i).setText(Integer.toString(wrongAnswer));
            }

        }

    }
    public void controllTimer(){

        countDownTimer=new CountDownTimer(15*1000+100,1000) {
            @Override
            public void onTick(long l) {
               updateTimer(l);
            }

            @Override
            public void onFinish() {

                for(int i=0;i<4;i++){
                    option.get(i).setVisibility(View.GONE);
                }

                timerText.setVisibility(View.GONE);
                scoreText.setVisibility(View.GONE);
                expression.setVisibility(View.GONE);

                finalScore.setText("Score : "+Integer.toString(score));
                finalScore.setVisibility(View.VISIBLE);
                gameOver.setVisibility(View.VISIBLE);

                btnStart.setText("Start Over");
                btnStart.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void updateTimer (long leftSecond){
        int minute=(int)(leftSecond/60)/1000;
        int second=(int)(leftSecond/1000-minute*60);
        String  minuteString=Integer.toString(minute);
        String  secondString=Integer.toString(second);

        if(minute<=9){
            minuteString="0"+minuteString;
        }
        if(second<=9){
            secondString="0"+secondString;
        }

        timerText.setText(minuteString+":"+secondString);
    }


}
