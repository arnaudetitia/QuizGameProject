package com.example.arnaudetitia.quizgameproject.utils;

import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.listener.OnQuestionSelected;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by Arnaud ETITIA on 07/09/2016.
 */
public class QuestionManager implements OnQuestionSelected {

    String mQuestion;
    String mRightAnswer;
    String mLeftAnswer;

    TextView mQuestionField;
    Button mRightButton;
    Button mLeftButton;

    String mURL = "http://192.168.1.17/androidquizserver/getQuestion.php";

    public QuestionManager(TextView questionField) {
        mQuestionField = questionField;
    }

    public void setQuestion(){
        try {
            URL url = new URL(mURL);
            DBConnector connector = new DBConnector(QuestionManager.this,url);
            connector.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setRightButton(Button button){
        mRightButton = button;
    }

    public void setLeftButton(Button button){ mLeftButton = button;}

    @Override
    public void done(String result) {
        String sautLigne = "#";
        String[] data = result.split(sautLigne);

        mQuestion = data[0];
        mLeftAnswer = data[1];
        mRightAnswer= data[2];

        mQuestionField.setText(mQuestion);
        mLeftButton.setText(mLeftAnswer);
        mRightButton.setText(mRightAnswer);

    }

    private String utf8Decode(String s) {
        try {
            byte [] buf = s.getBytes("UTF-8");
            return new String(buf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "plop";
        }
    }

}
