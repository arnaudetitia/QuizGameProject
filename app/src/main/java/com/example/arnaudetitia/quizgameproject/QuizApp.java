package com.example.arnaudetitia.quizgameproject;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arnaud ETITIA on 14/12/2016.
 */
public class QuizApp extends Application {

    public final static String BACKGROUND_SETTING = "Background";

    private List<Integer> mColorsId;
    private SharedPreferences mSettings;

    @Override
    public void onCreate() {
        super.onCreate();

        mColorsId = new ArrayList<>();
        mColorsId.add(getColorId(R.color.purpleBackground));
        mColorsId.add(getColorId(R.color.orangeBackground));
        mColorsId.add(getColorId(R.color.turquoiseBackground));
        mColorsId.add(getColorId(R.color.yellowBackground));
        mColorsId.add(getColorId(R.color.greenBackground));

        mSettings = getSharedPreferences("game_settings",0);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(BACKGROUND_SETTING,mColorsId.get(0));
        editor.commit();
    }

    private int getColorId(int id){
        return ResourcesCompat.getColor(getResources(),id,null);
    }

    public void changeColor(int index){
        mSettings = getSharedPreferences("game_settings",0);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(BACKGROUND_SETTING,mColorsId.get(index));
        editor.commit();
    }
}
