package com.example.arnaudetitia.offlinemodule.logic;

import android.util.Log;

import com.example.arnaudetitia.offlinemodule.beans.Question;
import com.example.arnaudetitia.offlinemodule.beans.Reponse;
import com.example.arnaudetitia.offlinemodule.data.QuestionReponse;
import com.example.arnaudetitia.offlinemodule.data.Questions;
import com.example.arnaudetitia.offlinemodule.data.ReponseType;

import java.util.List;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class GetQuestion {

    public static String getQuestion(){
        Questions.initQuestions();
        int id = getRandomNumber(1,Questions.getNbQuestions());
        Log.d("GetQuestion:Id",Integer.toString(id));
        Question q = Questions.getQuestionById(id);
        return q.getQuestion()+"#"+GetResponses.getResponses(q.getTypes(),q.getId());
    }

    private static int getRandomNumber(int min,int max){
        Log.d("GetQuestion:min",Integer.toString(min));
        Log.d("GetQuestion:max",Integer.toString(max));
        return min+((int)(Math.random()*100))%max;
    }
}
