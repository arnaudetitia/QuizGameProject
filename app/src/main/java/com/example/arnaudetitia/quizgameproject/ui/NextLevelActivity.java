package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.arnaudetitia.quizgameproject.R;
import com.example.arnaudetitia.quizgameproject.timer.Timer;

public class NextLevelActivity extends Activity {

    Button mNextLevel;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);

        getWindow().getAttributes().windowAnimations = R.style.NextLevelStyle;

        mNextLevel = (Button) findViewById(R.id.start_next_level_button);
        mNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
