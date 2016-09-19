package com.example.arnaudetitia.quizgameproject.action;

import android.widget.ProgressBar;

import com.example.arnaudetitia.quizgameproject.listener.OnLevelSelected;
import com.example.arnaudetitia.quizgameproject.ui.GameActivity;
import com.example.arnaudetitia.quizgameproject.utils.DBConnector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arnaud ETITIA on 09/09/2016.
 */
public class AventureActionManager extends ActionManager implements OnLevelSelected{

    int mLevel;
    GameActivity mGameActivity;
    ProgressBar mProgressBar;
    int mGoal;
    boolean mConsecutive;
    String mURL = "http://192.168.1.17:81/androidquizserver/getLevel.php?level=";

    public AventureActionManager(GameActivity ga,ProgressBar mProgressBar) {
        this.mGameActivity = ga;
        this.mProgressBar = mProgressBar;
        mLevel = 0;
        setNextLevel();
    }

    public void setGoal(int goal){
        mGoal = goal;

        this.mProgressBar.setMax(mGoal);
    }

    public void resetProgress(){
        mProgressBar.setProgress(0);
    }

    public void setConsecutive(boolean mConsecutive) {
        this.mConsecutive = mConsecutive;
    }

    @Override
    public void goodAction() {
        mProgressBar.setProgress(mProgressBar.getProgress()+1);
        if (mProgressBar.getProgress() == mGoal){
            mGameActivity.makeWin();
        }
    }

    @Override
    public void badAction() {
        if (mConsecutive){
            resetProgress();
        }
    }

    public void setNextLevel() {
        mLevel++;
        try {
            URL url = new URL(mURL + String.valueOf(mLevel));
            DBConnector connector = new DBConnector(this,url);
            connector.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void done(String result) {
        try {
            JSONArray array = new JSONArray(result);
            JSONObject lvl = array.getJSONObject(0);

            this.setGoal(lvl.getInt("goal"));
            this.setConsecutive(lvl.getInt("consecutive") == 1);
            this.mGameActivity.setTimeToTimer(lvl.getInt("time"));

            resetProgress();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
