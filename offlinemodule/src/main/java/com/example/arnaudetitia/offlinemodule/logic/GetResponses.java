package com.example.arnaudetitia.offlinemodule.logic;

import com.example.arnaudetitia.offlinemodule.beans.Reponse;
import com.example.arnaudetitia.offlinemodule.data.QuestionReponse;
import com.example.arnaudetitia.offlinemodule.data.ReponseType;
import com.example.arnaudetitia.offlinemodule.data.Reponses;

import java.util.List;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class GetResponses {

    public static String getResponses(ReponseType rt, int idQuestion){
        Reponses.initReponses();

        QuestionReponse.initQuestionReponse();
        int idReponse = QuestionReponse.getReponseId(idQuestion);
        Reponse gr = Reponses.getReponseById(idReponse);

        List<Reponse> reponses = Reponses.getReponsesByType(rt);

        reponses.remove(gr);
        Reponse wr = reponses.get((int)(Math.random()*reponses.size()));
        int x = (int)(Math.random()*2);
        if (x == 0){
            return gr.getReponse() +"#"+wr.getReponse();
        }
        else {
            return wr.getReponse() +"#"+gr.getReponse();
        }
    }
}
