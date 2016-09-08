package utils;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Arnaud ETITIA on 07/09/2016.
 */
public class DBConnector extends AsyncTask<String,String,String>{

    URL mUrl;
    OnRequestExecuted listener;

    public DBConnector(OnRequestExecuted listener, URL mUrl) {
        this.mUrl = mUrl;
        this.listener = listener;
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            HttpURLConnection con = (HttpURLConnection) mUrl.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String result = getFromBufferReader(buffer);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    @Override
    protected void onPostExecute(String s) {
        listener.done(s);
    }
}
