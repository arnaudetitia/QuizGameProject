package com.example.arnaudetitia.quizgameproject.utils;

import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.listener.OnQuestionSelected;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arnaud ETITIA on 07/09/2016.
 */
public class QuestionManager implements OnQuestionSelected {

    String mQuestion;
    String mRightAnswer;
    String mWrongAnswer;

    TextView mQuestionField;
    Button mRightButton;
    Button mWrongButton;

    String mURL = "http://192.168.1.17:81/androidquizserver/getQuestion.php?id=";

    public QuestionManager(TextView questionField) {
        mQuestionField = questionField;
    }

    public void setQuestion(int idQuestion){
        try {
            URL url = new URL(mURL.concat(String.valueOf(idQuestion)));
            DBConnector connector = new DBConnector(QuestionManager.this,url);
            connector.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setRightButton(Button button){
        mRightButton = button;
    }

    public void setWrongButton(Button button){ mWrongButton = button;}

    @Override
    public void done(String result) {
        try {
            JSONObject object = new JSONObject(result);
            mQuestion = object.getString("question");
            mRightAnswer = object.getString("right");
            JSONArray wrong = object.getJSONArray("wrong");
            JSONObject wrongAnswerSelected = wrong.getJSONObject((int)(Math.random()*wrong.length()));
            mWrongAnswer = wrongAnswerSelected.getString("reponse");

            mQuestionField.setText(mQuestion);
            mRightButton.setText(mRightAnswer);
            mWrongButton.setText(mWrongAnswer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Button getRightButton() {
        return mRightButton;
    }
}
