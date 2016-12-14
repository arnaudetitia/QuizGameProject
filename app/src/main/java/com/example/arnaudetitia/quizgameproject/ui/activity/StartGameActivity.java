package com.example.arnaudetitia.quizgameproject.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.QuizApp;
import com.example.arnaudetitia.quizgameproject.R;
import com.example.arnaudetitia.quizgameproject.utils.Mode;

public class StartGameActivity extends Activity {


    private Button mStartGameButton;
    private int mMode;
    private TextView mRulesField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        getWindow().getAttributes().windowAnimations = R.style.StartGameSyle;

        mMode = getIntent().getIntExtra("mode",0);
        mRulesField = (TextView) findViewById(R.id.rules_field);
        
        mStartGameButton = (Button) findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartGameActivity.this,GameActivity.class);
                intent.putExtra("mode",mMode);
                startActivity(intent);
                finish();
            }
        });

        initRules();
        changeColor();
    }

    protected void changeColor() {
        SharedPreferences settings = getSharedPreferences("game_settings",MODE_PRIVATE);
        int color = settings.getInt(QuizApp.BACKGROUND_SETTING, Color.RED);

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setBackgroundColor(color);
    }
    
    private void initRules() {
        if (mMode == Mode.AVENTURE){
            mRulesField.append(getString(R.string.aventure_rules_field));
            return;
        }
        if (mMode == Mode.CLM){
            mRulesField.append(getString(R.string.string_clm_rule));
            return;
        }
        if (mMode == Mode.SURVIE){
            mRulesField.append(getString(R.string.string_survie_rule));
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
