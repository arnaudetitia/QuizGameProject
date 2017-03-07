package com.example.arnaudetitia.quizgameproject.action;

import android.widget.TextView;

/**
 * Created by Arnaud ETITIA on 09/09/2016.
 */
public class CLMActionManager extends ActionManager{

    TextView mScoreField;

    public CLMActionManager(TextView view) {
            mScoreField = view;
            this.mScore = 0;
    }

    @Override
    public void goodAction() {
        this.mScore += 10 ;
        mScoreField.setText("Score : " + this.mScore);
    }

    @Override
    public void badAction() {
        this.mScore /= 2;
        mScoreField.setText("Score : " + this.mScore);
    }

    @Override
    public int getScore() {
        return mScore;
    }
}
