package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.R;
import com.example.arnaudetitia.quizgameproject.action.AventureActionManager;
import com.example.arnaudetitia.quizgameproject.action.CLMActionManager;
import com.example.arnaudetitia.quizgameproject.action.SurvieActionManager;
import com.example.arnaudetitia.quizgameproject.utils.Mode;

public class StartGameActivity extends Activity {


    private Button mStartGameButton;
    private int mMode;
    private TextView mRulesField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

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
}