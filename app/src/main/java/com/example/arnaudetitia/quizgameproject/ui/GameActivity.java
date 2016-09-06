package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.R;

import timer.Timer;

public class GameActivity extends Activity {

    TextView mQuestionField;
    View mGameStarted;
    Button mStartGameButton;
    Button mLeftButton;
    Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameStarted = findViewById(R.id.layout_game_started);
        mGameStarted.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
        mTimer = new Timer(this,progressBar,20000);

        mStartGameButton = (Button) findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartGameButton.setVisibility(View.GONE);
                mGameStarted.setVisibility(View.VISIBLE);
                mTimer.startTimer();
            }
        });

        mLeftButton = (Button) findViewById(R.id.button_left_answer);
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimer.win();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
