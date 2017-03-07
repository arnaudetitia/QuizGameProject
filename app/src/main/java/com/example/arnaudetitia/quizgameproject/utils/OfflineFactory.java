package com.example.arnaudetitia.quizgameproject.utils;

import com.example.arnaudetitia.offlinemodule.logic.CheckAnswer;
import com.example.arnaudetitia.offlinemodule.logic.GetQuestion;
import com.example.arnaudetitia.quizgameproject.listener.OnRequestExecuted;
import com.example.arnaudetitia.quizgameproject.ui.activity.GameActivity;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class OfflineFactory {

    public static String getString(OnRequestExecuted listener){
        String name = listener.getClass().getSimpleName();
        if (name.equals("QuestionManager")){
            return GetQuestion.getQuestion();
        }
        else {
            GameActivity ga = (GameActivity) listener;
            CheckAnswerManager cam = ga.getCheckAnswerManager();
            return Integer.toString(CheckAnswer.checkAnswer(cam.getmQuestion(),cam.getmAnswer()));
        }
    }
}
