package com.example.arnaudetitia.quizgameproject.utils;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.arnaudetitia.quizgameproject.ui.activity.GameActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arnaud ETITIA on 26/09/2016.
 */
public class CheckAnswerManager extends IntentService{

    String mURL = "http://192.168.1.17/androidquizserver/checkGoodAnswer.php?q=";
    String mQuestion;
    String mAnswer;

    public static final String CHECK_ANSWER_RESULT = "check_answer_result";

    public CheckAnswerManager() {
        super("CheckAnswerManager");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
            mQuestion = intent.getStringExtra(GameActivity.QUESTION_TEXT);
            mAnswer = intent.getStringExtra(GameActivity.ANSWER_TEXT);
            formatToHTML();
            URL url = new URL(mURL.concat(mQuestion).concat("&r=").concat(mAnswer));
            Log.d("CheckAnswerManager",url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(200);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String result = getFromBufferReader(buffer);

            Intent answerIntent = new Intent();
            answerIntent.setAction(GameActivity.GameReciever.ACTION_RESP);
            answerIntent.addCategory(Intent.CATEGORY_DEFAULT);
            answerIntent.putExtra(CHECK_ANSWER_RESULT,result);
            sendBroadcast(answerIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFromBufferReader(BufferedReader buf){
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = buf.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public String getmAnswer() {
        return mAnswer;
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
