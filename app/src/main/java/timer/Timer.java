package timer;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arnaudetitia.quizgameproject.ui.GameActivity;

/**
 * Created by Arnaud ETITIA on 06/09/2016.
 */
public class Timer {

    GameActivity mGameActivity;
    ProgressBar mProgressBar;
    int mProgress;
    int mMaxProgress;

    private boolean mWinner;

    Thread timerThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while(mProgress > 0) {
                    mProgress -= 100;
                    mProgressBar.setProgress(mProgress);
                    Thread.sleep(100);
                    if (mProgress == 0 || mWinner) {
                        throw new TimerException("Terminé");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimerException e) {
                e.printStackTrace();
                if (mWinner){
                    mWinner = false;
                }
                mGameActivity.launchEndGame();
            }
        }
    });

    public Timer(GameActivity gameActivity,ProgressBar progressBar) {
        this.mGameActivity = gameActivity;
        this.mProgressBar = progressBar;
        mWinner = false;
    }

    public void setTimer(int nbSecond){
        this.mMaxProgress = nbSecond*1000;
        this.mProgress = this.mMaxProgress;

        this.mProgressBar.setMax(this.mMaxProgress);
        this.mProgressBar.setProgress(this.mProgress);
    }

    public void startTimer(){
        timerThread.start();
    }



    public void win(){
        mWinner = true;
    }
}
