package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.R;

import com.example.arnaudetitia.quizgameproject.timer.Timer;
import com.example.arnaudetitia.quizgameproject.utils.Mode;
import com.example.arnaudetitia.quizgameproject.utils.QuestionManager;
import com.example.arnaudetitia.quizgameproject.utils.WrongAnswerManager;

public class GameActivity extends Activity {

    TextView mRulesField;
    View mGameStarted;
    View mGameToStart;
    Button mStartGameButton;
    TextView mQuestionField;
    Button mLeftButton;
    Button mRightButton;
    Timer mTimer;

    QuestionManager mQuestionManager;
    WrongAnswerManager mWrongManager;

    int mMode;
    boolean normalQuestionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mMode = getIntent().getIntExtra("mode",0);
        mRulesField = (TextView) findViewById(R.id.rules_field);

        mGameToStart = findViewById(R.id.layout_game_to_start);

        mGameStarted = findViewById(R.id.layout_game_started);
        mGameStarted.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
        mTimer = new Timer(this,progressBar);
        initRules();

        mStartGameButton = (Button) findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGameToStart.setVisibility(View.GONE);
                mGameStarted.setVisibility(View.VISIBLE);
                mTimer.startTimer();
            }
        });

        mQuestionField = (TextView) findViewById(R.id.question_field);
        mLeftButton = (Button) findViewById(R.id.button_left_answer);
        mRightButton = (Button) findViewById(R.id.button_right_answer);

        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                boolean rightAnswer = b.equals(mQuestionManager.getRightButton());
                Log.d("Debug:Button","Point de plus : " + (rightAnswer == normalQuestionMode));
                changeQuestion();
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                boolean rightAnswer = b.equals(mQuestionManager.getRightButton());
                Log.d("Debug:Button","Point de plus : " + (rightAnswer == normalQuestionMode));
                changeQuestion();
            }
        });

        mQuestionManager = new QuestionManager(mQuestionField);
        mWrongManager = new WrongAnswerManager();


        changeQuestion();
    }



    private void initRules() {
        if (mMode == Mode.AVENTURE){
            mRulesField.append("mode Aventure");
            return;
        }
        if (mMode == Mode.CLM){
            mRulesField.append("mode Contre La montre");
            mTimer.setTimer(60);
            return;
        }
        if (mMode == Mode.SURVIE){
            mRulesField.append("mode Survie");
            return;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void launchEndGame() {
        Intent i = new Intent(GameActivity.this,EndGameActivity.class);
        startActivity(i);
        finish();
    }

    private void changeQuestion() {
        GradientDrawable gd = (GradientDrawable) mQuestionField.getBackground();
        normalQuestionMode = Math.random() *2 > 1.0;

        gd.setStroke(10,normalQuestionMode ? Color.GREEN : Color.RED);

        int index = (int)(Math.random()*9)+1;

        boolean leftRightanswer = Math.random() *2 > 1.0;

        if (leftRightanswer){
            mQuestionManager.setRightButton(mLeftButton);
            mWrongManager.setWrongButton(mRightButton);
        }
        else {
            mQuestionManager.setRightButton(mRightButton);
            mWrongManager.setWrongButton(mLeftButton);
        }

        mQuestionManager.setQuestion(index);

        mWrongManager.setWrongAnswer(index);
    }
}
