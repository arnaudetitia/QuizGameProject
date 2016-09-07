package utils;

import android.widget.Button;
import android.widget.TextView;

import com.example.arnaudetitia.quizgameproject.ui.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arnaud ETITIA on 07/09/2016.
 */
public class QuestionManager implements OnQuestionSelected{

    String mQuestion;
    String mRightAnswer;
    String mWrongAnswer;

    TextView mQuestionField;
    Button mRightButton;
    Button mWrongButton;

    String mURL = "http://192.168.1.17/androidquizserver/getQuestion.php?id=";

    public QuestionManager(TextView questionField, Button rightButton , Button wrongButton) {
        mQuestionField = questionField;
        mRightButton = rightButton;
        mWrongButton = wrongButton;
    }

    public void setQuestion(int idQuestion){
        try {
            URL url = new URL(mURL.concat(String.valueOf(4)));
            DBConnector connector = new DBConnector(QuestionManager.this,url);
            connector.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
            mWrongButton.setText("Pas là");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
