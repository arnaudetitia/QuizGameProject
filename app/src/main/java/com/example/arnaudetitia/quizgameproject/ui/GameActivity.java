package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.R;

import timer.Timer;
import utils.Mode;
import utils.QuestionManager;

public class GameActivity extends Activity {

    TextView mRulesField;
    View mGameStarted;
    View mGameToStart;
    Button mStartGameButton;
    TextView mQuestionField;
    Button mLeftButton;
    Button mRightButton;
    Timer mTimer;

    QuestionManager mManager;

    int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mMode = getIntent().getIntExtra("mode",0);
        mRulesField = (TextView) findViewById(R.id.rules_field);
        initRules();



        mGameToStart = findViewById(R.id.layout_game_to_start);

        mGameStarted = findViewById(R.id.layout_game_started);
        mGameStarted.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
        mTimer = new Timer(this,progressBar,20000);

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


        mManager = new QuestionManager(mQuestionField,mLeftButton,mRightButton);
        mManager.setQuestion(3);
    }

    private void initRules() {
        if (mMode == Mode.AVENTURE){
            mRulesField.append("mode Aventure");
            return;
        }
        if (mMode == Mode.CLM){
            mRulesField.append("mode Contre La montre");
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
}
