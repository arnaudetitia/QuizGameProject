package com.example.arnaudetitia.quizgameproject.utils;

import com.example.arnaudetitia.quizgameproject.ui.GameActivity;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arnaud ETITIA on 26/09/2016.
 */
public class CheckAnswerManager {

    GameActivity mGameActivity;
    String mURL = "http://192.168.1.17/androidquizserver/checkGoodAnswer.php?q=";
    String mQuestion;
    String mAnswer;

    public CheckAnswerManager(GameActivity mGameActivity) {
        this.mGameActivity = mGameActivity;
    }

    public void checkAnswer(){
        try{
            formatToHTML();
            URL url = new URL(mURL.concat(mQuestion).concat("&r=").concat(mAnswer));
            System.out.println(url);
            DBConnector connector = new DBConnector(mGameActivity,url);
            connector.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    private void formatToHTML(){
        mQuestion = mQuestion.replace(" ","%20");
        mQuestion = mQuestion.replace("é","e");
        mQuestion = mQuestion.replace("è","e");
        mQuestion = mQuestion.replace("ê","e");
        mQuestion = mQuestion.replace("ç","c");
        mQuestion = mQuestion.replace("'","%27");

        mAnswer = mAnswer.replace(" ","%20");
        mAnswer = mAnswer.replace("é","e");
        mAnswer = mAnswer.replace("è","e");
        mAnswer = mAnswer.replace("ê","e");
        mAnswer = mAnswer.replace("ç","c");
        mAnswer = mAnswer.replace("'","%27");
    }
}
