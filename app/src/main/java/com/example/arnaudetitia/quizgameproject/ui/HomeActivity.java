package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.arnaudetitia.quizgameproject.R;

import com.example.arnaudetitia.quizgameproject.utils.Mode;

public class HomeActivity extends Activity {

    Button mAventureButton;
    Button mCLMButton;
    Button mSurvieButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().getAttributes().windowAnimations = R.style.HomeStyle;
        System.out.println("Debug " + getWindow().getAttributes().windowAnimations);
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
    }
}
