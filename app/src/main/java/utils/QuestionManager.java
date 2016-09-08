package utils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    TextView mQuestionField;
    Button mRightButton;

    String mURL = "http://192.168.1.17/androidquizserver/getQuestion.php?id=";

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

    @Override
    public void done(String result) {
        try {
            JSONArray array = new JSONArray(result);
            JSONObject object = array.getJSONObject(0);
            mQuestion = object.getString("question");
            mRightAnswer = object.getString("reponse");

            mQuestionField.setText(mQuestion);
            mRightButton.setText(mRightAnswer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Button getRightButton() {
        return mRightButton;
    }
}
