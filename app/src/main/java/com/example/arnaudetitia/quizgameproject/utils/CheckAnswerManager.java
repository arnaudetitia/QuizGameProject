package com.example.arnaudetitia.quizgameproject.utils;

import com.example.arnaudetitia.quizgameproject.ui.GameActivity;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by Arnaud ETITIA on 26/09/2016.
 */
public class CheckAnswerManager {

    GameActivity mGameActivity;
    String mURL = "http://192.168.1.17:81/androidquizserver/checkGoodAnswer.php?q=";
    String mQuestion;
    String mAnswer;

    public CheckAnswerManager(GameActivity mGameActivity) {
        this.mGameActivity = mGameActivity;
    }

    public void checkAnswer(){
        try{
            mQuestion = mQuestion.replace(" ","%20");
            byte[] questionBytes = mQuestion.getBytes("ASCII");
            mQuestion = new String(questionBytes);
            mAnswer = mAnswer.replace(" ","%20");
            byte[] answerBytes = mQuestion.getBytes("ASCII");
            mAnswer = new String(answerBytes);

            URL url = new URL(mURL.concat(mQuestion).concat("&r=").concat(mAnswer));
            System.out.println(url);
            DBConnector connector = new DBConnector(mGameActivity,url);
            connector.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }
}
