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
        super.goodAction();
        mTimer.addToTimer(1);
    }

    @Override
    public void badAction() {
        super.badAction();
        mTimer.subToTimer(mMalus);
        mMalus *= 110;
        mMalus /= 100;
    }

    @Override
    public int getScore() {
        return super.getScore();
    }
}
