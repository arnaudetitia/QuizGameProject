package com.example.arnaudetitia.quizgameproject.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arnaudetitia.quizgameproject.QuizApp;
import com.example.arnaudetitia.quizgameproject.R;

import com.example.arnaudetitia.quizgameproject.listener.OnGameStarted;
import com.example.arnaudetitia.quizgameproject.utils.Mode;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends Activity implements OnGameStarted {

    Button mAventureButton;
    Button mCLMButton;
    Button mSurvieButton;
    Button mSettingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().getAttributes().windowAnimations = R.style.HomeStyle;

        mAventureButton = (Button) findViewById(R.id.button_aventure);
        mAventureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,StartGameActivity.class);
                i.putExtra("mode",Mode.AVENTURE);
                startActivity(i);
            }
        });

        mCLMButton = (Button) findViewById(R.id.button_clm);
        mCLMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,StartGameActivity.class);
                i.putExtra("mode",Mode.CLM);
                startActivity(i);
            }
        });

        mSurvieButton = (Button) findViewById(R.id.button_survie);
        mSurvieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,StartGameActivity.class);
                i.putExtra("mode",Mode.SURVIE);
                startActivity(i);
            }
        });

        mSettingButton = (Button) findViewById(R.id.button_options);
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(i);
            }
        });



        changeColor();
    }

    protected void changeColor() {
        SharedPreferences settings = getSharedPreferences("game_settings",MODE_PRIVATE);
        int color = settings.getInt(QuizApp.BACKGROUND_SETTING, Color.RED);

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setBackgroundColor(color);
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeColor();
    }

    @Override
    public void done(String s) {
    }
}
