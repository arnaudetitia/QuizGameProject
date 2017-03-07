package com.example.arnaudetitia.quizgameproject.utils;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.offlinemodule.beans.Question;
import com.example.arnaudetitia.offlinemodule.logic.GetQuestion;
import com.example.arnaudetitia.quizgameproject.listener.OnQuestionSelected;
import com.example.arnaudetitia.quizgameproject.ui.activity.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by Arnaud ETITIA on 07/09/2016.
 */
public class QuestionManager extends IntentService {


    String mURL = "http://192.168.1.17/androidquizserver/getQuestion.php";

    public static final String QUESTION_RESULT = "question_result";

    public QuestionManager() {
        super("QuestionManager");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("QuestionManager","onHandleIntent()");
        try {
            URL url = new URL(mURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(200);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String result = getFromBufferReader(buffer);

            Intent questionIntent = new Intent();
            questionIntent.setAction(GameActivity.GameReciever.ACTION_RESP);
            questionIntent.addCategory(Intent.CATEGORY_DEFAULT);
            questionIntent.putExtra(QUESTION_RESULT,result);
            sendBroadcast(questionIntent);
        } catch (Exception e) {
            System.out.println(e);
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
}
