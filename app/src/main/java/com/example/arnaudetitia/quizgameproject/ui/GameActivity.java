package com.example.arnaudetitia.quizgameproject.ui;

import android.app.Activity;
import android.app.FragmentManager;
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

import com.example.arnaudetitia.quizgameproject.action.ActionManager;
import com.example.arnaudetitia.quizgameproject.action.AventureActionManager;
import com.example.arnaudetitia.quizgameproject.action.CLMActionManager;
import com.example.arnaudetitia.quizgameproject.action.SurvieActionManager;
import com.example.arnaudetitia.quizgameproject.listener.OnAnswerChecked;
import com.example.arnaudetitia.quizgameproject.timer.Timer;
import com.example.arnaudetitia.quizgameproject.utils.CheckAnswerManager;
import com.example.arnaudetitia.quizgameproject.utils.Mode;
import com.example.arnaudetitia.quizgameproject.utils.QuestionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class GameActivity extends Activity implements OnAnswerChecked{

    TextView mScoreField;

    TextView mQuestionField;
    Button mLeftButton;
    Button mRightButton;

    View mAventureLayout;
    View mScoreLayout;

    Timer mTimer;
    ProgressBar mAventureProgressBar;

    ActionManager mManager;
    AventureActionManager mAventureManager;

    QuestionManager mQuestionManager;
    CheckAnswerManager mCheckAnswerManager;
    FragmentManager mFragManager;

    int mMode;
    boolean normalQuestionMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mMode = getIntent().getIntExtra("mode",0);

        mAventureLayout = findViewById(R.id.indication_aventure_field);
        mAventureLayout.setVisibility(View.GONE);

        mAventureProgressBar = (ProgressBar) findViewById(R.id.progress_aventure_bar);

        mScoreLayout = findViewById(R.id.indication_clm_field);
        mScoreLayout.setVisibility(View.GONE);

        mScoreField = (TextView) findViewById(R.id.score_field);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.timerBar);
        mTimer = new Timer(this,progressBar);

        mQuestionField = (TextView) findViewById(R.id.question_field);
        mLeftButton = (Button) findViewById(R.id.button_left_answer);
        mRightButton = (Button) findViewById(R.id.button_right_answer);

        mQuestionManager = new QuestionManager(mQuestionField);

        mQuestionManager.setRightButton(mLeftButton);
        mQuestionManager.setLeftButton(mRightButton);

        mCheckAnswerManager = new CheckAnswerManager(this);

        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                mCheckAnswerManager.setQuestion(mQuestionField.getText().toString());
                mCheckAnswerManager.setAnswer(b.getText().toString());
                mCheckAnswerManager.checkAnswer();
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                mCheckAnswerManager.setQuestion(mQuestionField.getText().toString());
                mCheckAnswerManager.setAnswer(b.getText().toString());
                mCheckAnswerManager.checkAnswer();
            }
        });

        changeQuestion();
        initRules();

        mFragManager = getFragmentManager();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mTimer.startTimer();
    }

    private void initRules() {
        if (mMode == Mode.AVENTURE){
            mAventureLayout.setVisibility(View.VISIBLE);
            mTimer.setTimer(45);
            mAventureManager = new AventureActionManager(this,mAventureProgressBar);
            mAventureManager.setGoal(5);
            mAventureManager.resetProgress();
            mAventureManager.setConsecutive(true);
            mManager = mAventureManager;
            return;
        }
        if (mMode == Mode.CLM){
            mTimer.setTimer(60);
            mScoreLayout.setVisibility(View.VISIBLE);
            mManager = new CLMActionManager(mScoreField);
            return;
        }
        if (mMode == Mode.SURVIE){
            mTimer.setTimer(10);
            mScoreLayout.setVisibility(View.VISIBLE);
            mManager = new SurvieActionManager(mScoreField,mTimer);
            return;
        }
    }

    @Override
    public void onBackPressed() {
        mTimer.pauseTimer();
        QuitGameDialog quitDialog = new QuitGameDialog();
        quitDialog.show(mFragManager,"Quit Game");
    }

    public void endGame() {
        Intent i = new Intent(GameActivity.this,EndGameActivity.class);
        if (mMode != Mode.AVENTURE){
            i.putExtra("score",mManager.getScore());
        }
        startActivity(i);
        finish();
    }

    private void changeQuestion() {
        GradientDrawable gd = (GradientDrawable) mQuestionField.getBackground();
        normalQuestionMode = Math.random() *2 > 1.0;

        gd.setStroke(10,normalQuestionMode ? Color.GREEN : Color.RED);

        mQuestionManager.setQuestion();
    }

    public void makeWin(){
        mAventureManager.setNextLevel();
        mManager = mAventureManager;
        mTimer.makeWin();
    }

    public void setTimeToTimer(int time){
        mTimer.setTimer(time);
    }

    public void quitGame(){
        finish();
    }

    public void restartGame(){
        mTimer.restartTimer();
    }

    @Override
    public void done(String result) {

        boolean goodAnswer = (Integer.parseInt(result)== 1);

        System.out.println( goodAnswer + " " + normalQuestionMode);

        if (goodAnswer == normalQuestionMode){
            mManager.goodAction();
        }
        else {
            mManager.badAction();
        }

        changeQuestion();

    }
}
