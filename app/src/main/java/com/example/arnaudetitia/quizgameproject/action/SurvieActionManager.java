package com.example.arnaudetitia.quizgameproject.action;

import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.timer.Timer;

/**
 * Created by Arnaud ETITIA on 09/09/2016.
 */
public class SurvieActionManager extends CLMActionManager {

    Timer mTimer;
    int mMalus;

    public SurvieActionManager(TextView view,Timer t) {
        super(view);
        this.mTimer = t;
        this.mMalus = 1000;
    }

    @Override
    public void goodAction() {
        mScore += mTimer.getProgress()/1000;
        mTimer.addToTimer(1);
    }

    @Override
    public void badAction() {
        mTimer.subToTimer(mMalus);
        mMalus *= 110;
        mMalus /= 100;
        mScoreField.setText("Score : " + this.mScore);
    }

    @Override
    public int getScore() {
        return super.getScore();
    }
}
