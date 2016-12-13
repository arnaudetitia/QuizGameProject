package com.example.arnaudetitia.quizgameproject.timer;

import android.widget.ProgressBar;

import com.example.arnaudetitia.quizgameproject.ui.activity.GameActivity;

/**
 * Created by Arnaud ETITIA on 06/09/2016.
 */
public class Timer {

    GameActivity mGameActivity;
    ProgressBar mProgressBar;
    int mProgress;
    int mMaxProgress;

    private boolean mWinner;
    private boolean mOnPause;

    Thread timerThread;

    public Timer(GameActivity gameActivity,ProgressBar progressBar) {
        this.mGameActivity = gameActivity;
        this.mProgressBar = progressBar;
        mWinner = false;
        mOnPause = false;
    }

    public void setTimer(int nbSecond){
        this.mMaxProgress = nbSecond*1000;
        this.mProgress = this.mMaxProgress;

        this.mProgressBar.setMax(this.mMaxProgress);
        this.mProgressBar.setProgress(this.mProgress);
    }

    public void startTimer(){
        timerThread = new Thread(mTimerRunnable);
        timerThread.start();
    }

    public void addToTimer(int nbSecond){
        this.mProgress += nbSecond*1000;

        this.mProgressBar.setProgress(mProgress);
    }

    public void subToTimer(int nbMillis){
        this.mProgress -= nbMillis;

        this.mProgressBar.setProgress(mProgress);
    }

    public void makeWin(){
        mWinner = true;
    }

    public void pauseTimer(){
        mOnPause = true;
    }

    public void restartTimer(){
        mOnPause = false;
    }

    public int getProgress(){
        return mProgress;
    }

    Runnable mTimerRunnable = new Runnable(){
        @Override
        public void run() {
            try {
                while(true) {
                    mProgress -= mOnPause ? 0 : 100;
                    mProgressBar.setProgress(mProgress);
                    Thread.sleep(100);
                    if (mProgress == 0 || mWinner) {
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                if (!mWinner) {
                    mGameActivity.endGame();
                }
                else
                {
                    mWinner = false;
                }
            }
        }
    };
}
