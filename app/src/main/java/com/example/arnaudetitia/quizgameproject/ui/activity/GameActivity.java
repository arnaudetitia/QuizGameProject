package com.example.arnaudetitia.quizgameproject.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arnaudetitia.offlinemodule.logic.CheckAnswer;
import com.example.arnaudetitia.quizgameproject.QuizApp;
import com.example.arnaudetitia.quizgameproject.R;

import com.example.arnaudetitia.quizgameproject.action.ActionManager;
import com.example.arnaudetitia.quizgameproject.action.AventureActionManager;
import com.example.arnaudetitia.quizgameproject.action.CLMActionManager;
import com.example.arnaudetitia.quizgameproject.action.SurvieActionManager;
import com.example.arnaudetitia.quizgameproject.listener.OnAnswerChecked;
import com.example.arnaudetitia.quizgameproject.timer.Timer;
import com.example.arnaudetitia.quizgameproject.ui.dialog.QuitGameDialog;
import com.example.arnaudetitia.quizgameproject.utils.CheckAnswerManager;
import com.example.arnaudetitia.quizgameproject.utils.Mode;
import com.example.arnaudetitia.quizgameproject.utils.QuestionManager;

public class GameActivity extends Activity {

    public static final String QUESTION_TEXT = "question_text";
    public static final String ANSWER_TEXT = "answer_text";
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
    GameReciever mGameReciever;

    FragmentManager mFragManager;

    int mMode;
    boolean normalQuestionMode;

    public class GameReciever extends BroadcastReceiver{
        public static final String ACTION_RESP ="com.example.arnaudetitia.quizgameproject.TEXT_TO_DISPLAY";


        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("GameActivity","changeQuestion()");
            String qresult =  intent.getStringExtra(QuestionManager.QUESTION_RESULT);
            if (qresult != null){
                String sautLigne = "#";
                String[] data = qresult.split(sautLigne);

                mQuestionField.setText(data[0]);
                mLeftButton.setText(data[1]);
                mRightButton.setText(data[2]);

                return;
            }

            String aresult = intent.getStringExtra(CheckAnswerManager.CHECK_ANSWER_RESULT);
            if (aresult != null){
                boolean goodAnswer;
                goodAnswer = (Integer.parseInt(aresult)== 1);

                System.out.println( goodAnswer + " " + normalQuestionMode);
                if (goodAnswer == normalQuestionMode){
                    mManager.goodAction();
                }
                else {
                    mManager.badAction();
                }
                changeQuestion();
                return;
            }

        }
    }


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

        mQuestionManager = new QuestionManager();

        mCheckAnswerManager = new CheckAnswerManager();

        mGameReciever = new GameReciever();

        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                mCheckAnswerManager.setQuestion(mQuestionField.getText().toString());
                mCheckAnswerManager.setAnswer(b.getText().toString());
                Intent caservice = new Intent(GameActivity.this,CheckAnswerManager.class);
                caservice.putExtra(QUESTION_TEXT,mQuestionField.getText().toString());
                caservice.putExtra(ANSWER_TEXT,b.getText().toString());
                startService(caservice);
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                mCheckAnswerManager.setQuestion(mQuestionField.getText().toString());
                mCheckAnswerManager.setAnswer(b.getText().toString());
                Intent caservice = new Intent(GameActivity.this,CheckAnswerManager.class);
                caservice.putExtra(QUESTION_TEXT,mQuestionField.getText().toString());
                caservice.putExtra(ANSWER_TEXT,b.getText().toString());
                startService(caservice);
            }
        });

        changeQuestion();
        initRules();

        mFragManager = getFragmentManager();
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
        mTimer.startTimer();

        IntentFilter filter = new IntentFilter(GameReciever.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mGameReciever,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGameReciever);
    }

    private void initRules() {
        if (mMode == Mode.AVENTURE){
            mAventureLayout.setVisibility(View.VISIBLE);
            mTimer.setTimer(45);
            mAventureManager = new AventureActionManager(this,mTimer,mAventureProgressBar);
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

        Log.d("GameActivity","changeQuestion()");
        Intent qManager = new Intent(this,QuestionManager.class);
        startService(qManager);
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

    public CheckAnswerManager getCheckAnswerManager() {
        return mCheckAnswerManager;
    }
}
