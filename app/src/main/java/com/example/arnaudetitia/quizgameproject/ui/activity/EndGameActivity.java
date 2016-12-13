package com.example.arnaudetitia.quizgameproject.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.R;

public class EndGameActivity extends Activity {

    TextView endScoreView;
    Button backButton;

    int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        getWindow().getAttributes().windowAnimations = R.style.EndGameStyle;

        endScoreView = (TextView) findViewById(R.id.end_score_view);
        mScore = getIntent().getIntExtra("score",0);

        endScoreView.append(String.valueOf(mScore));

        backButton = (Button) findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

    }
}
