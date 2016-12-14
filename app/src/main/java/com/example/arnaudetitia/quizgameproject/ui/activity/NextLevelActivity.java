package com.example.arnaudetitia.quizgameproject.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.QuizApp;
import com.example.arnaudetitia.quizgameproject.R;

public class NextLevelActivity extends Activity {

    Button mNextLevelButton;

    TextView mNextLevelField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);

        getWindow().getAttributes().windowAnimations = R.style.NextLevelStyle;

        mNextLevelButton = (Button) findViewById(R.id.start_next_level_button);
        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNextLevelField = (TextView) findViewById(R.id.next_level_field);
        int time = getIntent().getIntExtra("time",0);
        int goal = getIntent().getIntExtra("goal",0);
        String consecutive = getIntent().getBooleanExtra("consecutive",false) ? "consécutif" : "";
        mNextLevelField.append(String.format(getString(R.string.next_level_text),goal,consecutive,time));
        changeColor();
    }

    protected void changeColor() {
        SharedPreferences settings = getSharedPreferences("game_settings",MODE_PRIVATE);
        int color = settings.getInt(QuizApp.BACKGROUND_SETTING, Color.RED);

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setBackgroundColor(color);
    }
}
