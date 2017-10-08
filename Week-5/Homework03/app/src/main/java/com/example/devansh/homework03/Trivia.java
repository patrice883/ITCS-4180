package com.example.devansh.homework03;

import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Trivia extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Question> triviaQuestions = null;
    int count = 0, correct = 0;
    TextView txtQuestionNumber, txtTimeLeft, txtQuestion;
    ImageView photo;
    ScrollView sv;
    Button btnExit, btnNext;

    CountDownTimer ctdown = new CountDownTimer(1000*60*2,1000){


        public void onTick(long x ){
            Log.d("test","COUNTDOWN GOING ON ");
            txtTimeLeft.setText("Time Left : " + x/1000);
        }

        @Override
        public void onFinish() {
            Log.d("test","COUNTDOWN ENDS ");
            generateStats();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_trivia);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_trivia);
        setTitle("Trivia");

        if(getIntent() != null && getIntent().getExtras() != null) {
            triviaQuestions = (ArrayList<Question>) getIntent().getExtras().get("TRIVIA");
            generateScreen();





        }
        else{
            Log.d("test", "No Intent was passed or if passed was null");
        }



    }
    public void generateStats(){
        Intent intent = new Intent(Trivia.this, Stats.class);
        intent.putExtra("CORRECT", correct);
        intent.putExtra("TOTAL", triviaQuestions.size());
        ctdown.cancel();
        startActivityForResult(intent,0);


    }

    public void generateScreen(){


        sv = new ScrollView(this);
        sv.setId(View.generateViewId());
        sv.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        ConstraintLayout screen = new ConstraintLayout(this);
        screen.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        screen.setId(View.generateViewId());

        txtQuestionNumber = new TextView(this);
        txtQuestionNumber.setId(View.generateViewId());
        ConstraintLayout.LayoutParams currentParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100);
        currentParams.leftMargin = 16;
        currentParams.topMargin = 16;
        currentParams.goneStartMargin = 16;
        currentParams.leftToLeft = screen.getId();
        currentParams.topToTop = screen.getId();
        txtQuestionNumber.setLayoutParams(currentParams);
        txtQuestionNumber.setText(" Question " + (triviaQuestions.get(count).getNumber()+1));
        txtQuestionNumber.setTextSize(20);

        screen.addView(txtQuestionNumber);

        txtTimeLeft = new TextView(this);
        txtTimeLeft.setId(View.generateViewId());
        currentParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100);
        currentParams.leftMargin = 16;
        currentParams.topMargin = 16;
        currentParams.rightMargin = 16;
        currentParams.goneEndMargin = 16;
        currentParams.goneStartMargin = 16;
        currentParams.leftToRight = txtQuestionNumber.getId();
        currentParams.rightToRight = screen.getId();
        currentParams.topToTop = screen.getId();
        currentParams.horizontalBias = (float) 0.925;
        txtTimeLeft.setLayoutParams(currentParams);
        txtTimeLeft.setText("Time Left : " + "XXX");
        txtTimeLeft.setTextSize(20);

        screen.addView(txtTimeLeft);

        photo = new ImageView(this);
        photo.setId(View.generateViewId());
        currentParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        currentParams.leftMargin = 16;
        currentParams.topMargin = 16;
        currentParams.rightMargin = 16;
        currentParams.goneEndMargin = 16;
        currentParams.goneStartMargin = 16;
        currentParams.leftToLeft = screen.getId();
        currentParams.rightToRight = screen.getId();
        currentParams.topToBottom = txtQuestionNumber.getId();
        currentParams.horizontalBias = (float) 0.529;
        photo.setLayoutParams(currentParams);




        if(triviaQuestions.get(count).getUrl().length() > 3) {
            Log.d("url", "the url is :  " + triviaQuestions.get(count).getUrl());
            new LoadingImages(photo, Trivia.this).execute(triviaQuestions.get(count).getUrl());
        }else {
            Log.d("url","the url is :  " + triviaQuestions.get(count).getUrl());
            photo.setImageDrawable(getResources().getDrawable(R.drawable.trivia));
        }
        screen.addView(photo);


        txtQuestion = new TextView(this);
        txtQuestion.setId(View.generateViewId());
        currentParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        currentParams.leftMargin = 16;
        currentParams.topMargin = 8;
        currentParams.rightMargin = 16;
        currentParams.goneEndMargin = 16;
        currentParams.goneStartMargin = 16;
        currentParams.topToBottom = photo.getId();
        currentParams.rightToRight = screen.getId();
        currentParams.leftToLeft = screen.getId();
        currentParams.horizontalBias = (float) 0.0;
        txtQuestion.setLayoutParams(currentParams);
        txtQuestion.setText(triviaQuestions.get(count).getQuestion());
        txtQuestion.setTextSize(16);

        screen.addView(txtQuestion);

        int size = triviaQuestions.get(count).getOptions().size();
        Log.d("test", "Options size is " + size);
        int current_id = txtQuestion.getId();
        for(int i = 0; i < size; i++){

            ConstraintLayout currentOption = new ConstraintLayout(this);
            TextView current = new TextView(this);
            current.setId(View.generateViewId());
            currentParams = new ConstraintLayout.LayoutParams(screen.getMaxWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
            currentParams.leftMargin = 16;
            currentParams.topMargin = 32;
            currentParams.rightMargin = 16;
            currentParams.goneEndMargin = 16;
            currentParams.goneStartMargin = 16;
            currentParams.topToBottom = current_id;
            currentParams.rightToRight = screen.getId();
            currentParams.leftToLeft = screen.getId();
            currentParams.horizontalBias = (float) 0.0;
            current.setLayoutParams(currentParams);
            current.setText( ((char)(i+65)) + ") " + triviaQuestions.get(count).getOptions().get(i));
            current.setTextSize(16);
            current.setOnClickListener(this);

            screen.addView(current);
            current_id = current.getId();



        }

        btnNext = new Button(this);
        btnNext.setId(View.generateViewId());
        currentParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        currentParams.rightMargin = 16;
        currentParams.topMargin = 16;
        currentParams.bottomMargin = 16;
        currentParams.topToBottom = current_id;
        currentParams.rightToRight = screen.getId();
        currentParams.bottomToBottom = screen.getId();
        btnNext.setLayoutParams(currentParams);
        btnNext.setText("Next >");

        screen.addView(btnNext);

        btnExit = new Button(this);
        btnExit.setId(View.generateViewId());
        currentParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        currentParams.leftMargin = 16;
        currentParams.topMargin = 16;
        currentParams.bottomMargin = 16;
        currentParams.topToBottom = current_id;
        //currentParams.rightToLeft = btnNext.getId();
        currentParams.leftToLeft = screen.getId();
        currentParams.bottomToBottom = screen.getId();
        btnExit.setLayoutParams(currentParams);
        btnExit.setText("Exit");
        screen.addView(btnExit);

        sv.addView(screen);

        setContentView(sv);

        btnNext.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == btnNext.getId()){
            Log.d("test", "Next Button Was Clicked");
            count++;

            if(count < triviaQuestions.size())
                generateScreen();
            else{
                Log.d("test", "No more questions");
                Log.d("test", "Time to generate stats");
                generateStats();
            }

        }
        else if(view.getId() == btnExit.getId()){
            Log.d("test", "Exit Button Was Clicked");
            generateStats();
        }
        else{
            Log.d("test", "Some Option was Clicked");



            if(count < triviaQuestions.size()) {
                int size = triviaQuestions.get(count).getOptions().size();
                int answer = -1;
                for(int i = 0; i < size;i++){
                    if(((TextView) view).getText().toString().contains(triviaQuestions.get(count).getOptions().get(i))){
                        Log.d("test", triviaQuestions.get(count).getOptions().get(i) +" Was Clicked");
                        answer = i;
                        break;
                    }
                }

                if(answer == triviaQuestions.get(count).getAnswer()){
                    correct++;
                    Log.d("test", "You answered Correctly");
                }
                else{
                    Log.d("test", "You answered incorrect choice");
                }
            }

            if(count == 0){
//                TimerTask a = new TimerTask() {
//                    @Override
//                    public void run() {
//                        generateStats();
//                    }
//                };
//                Log.d("test","Current time " + System.currentTimeMillis());
//                Timer t = new Timer();
//                t.schedule(a,(long) 1000*10);
//
                Log.d("test","COUNTDOWN STARTS LOL ");
                ctdown.start();

            }

            count++;
            if(count < triviaQuestions.size())
                generateScreen();
            else{
                Log.d("test", "No more questions");
                Log.d("test", "Time to generate stats");
                Log.d("test", "You got Correct " + correct);

                generateStats();
            }



        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("test", "We come here");
        if(requestCode == 0){
            Log.d("test", "We come here TOO ");
            if(resultCode == RESULT_OK){

                Log.d("test", "GOING AGAINN!!");
                Intent goAgain = new Intent(Trivia.this, Trivia.class);
                goAgain.putExtra("TRIVIA", (ArrayList<Question>) getIntent().getExtras().get("TRIVIA"));
                startActivity(goAgain);
                finish();

            }
            else if(resultCode == RESULT_CANCELED){
                Log.d("test", "User wanted to quit");
                finish();
            }
            else{
                Log.d("test", "HGMMM A BUG?? OR OVERLOOK??");
            }
        }
    }

}
