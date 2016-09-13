package com.example.arnaudetitia.quizgameproject.action;

import android.widget.TextView;

/**
 * Created by Arnaud ETITIA on 09/09/2016.
 */
public class CLMActionManager extends ActionManager{

    TextView mScoreField;
    int mScore;

    public CLMActionManager(TextView view) {
        mScoreField = view;
        this.mScore = 0;
    }

    @Override
    public void goodAction() {
        this.mScore++;
        mScoreField.setText("Score : " + this.mScore);
    }

    @Override
    public void badAction() {
    }

}