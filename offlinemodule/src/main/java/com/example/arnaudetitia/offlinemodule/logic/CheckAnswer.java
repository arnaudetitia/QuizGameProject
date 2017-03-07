package com.example.arnaudetitia.offlinemodule.logic;

import android.util.Log;

import com.example.arnaudetitia.offlinemodule.beans.Question;
import com.example.arnaudetitia.offlinemodule.beans.Reponse;
import com.example.arnaudetitia.offlinemodule.data.QuestionReponse;
import com.example.arnaudetitia.offlinemodule.data.Questions;
import com.example.arnaudetitia.offlinemodule.data.Reponses;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class CheckAnswer {

    public static int checkAnswer(String question,String reponse){
        Questions.initQuestions();
        Reponses.initReponses();
        QuestionReponse.initQuestionReponse();
        Question q = Questions.getQuestionByText(question);
        Reponse r = Reponses.getReponseByText(reponse);
        return QuestionReponse.getReponseId(q.getId()) == r.getId() ? 1 : 0;
    }
}
