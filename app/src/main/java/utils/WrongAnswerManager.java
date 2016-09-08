package utils;

import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arnaud ETITIA on 08/09/2016.
 */
public class WrongAnswerManager implements OnWrongAnswerSelected {

    String mWrongAnswer;

    Button mWrongButton;

    String mURL = "http://192.168.1.17/androidquizserver/getWrongAnswers.php?id=";


    public void setWrongAnswer(int idQuestion){
        try {
            URL url = new URL(mURL.concat(String.valueOf(idQuestion)));
            DBConnector connector = new DBConnector(WrongAnswerManager.this,url);
            connector.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setWrongButton(Button button){
        mWrongButton = button;
    }

    @Override
    public void done(String result) {
        try{
            JSONArray array = new JSONArray(result);
            int l = array.length();
            int index = (int)(Math.random()*l);
            JSONObject object = array.getJSONObject(index);

            mWrongAnswer = new String(object.getString("reponse").getBytes(),"UTF-8");
            mWrongButton.setText(mWrongAnswer);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
