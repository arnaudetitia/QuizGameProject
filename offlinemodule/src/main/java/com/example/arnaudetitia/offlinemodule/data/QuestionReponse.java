package com.example.arnaudetitia.offlinemodule.data;

import java.util.HashMap;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class QuestionReponse {

    public static HashMap<Integer,Integer> mQuestionReponse= new HashMap<>();

    public static void initQuestionReponse(){
        mQuestionReponse.put(1,2);
        mQuestionReponse.put(2,18);
        mQuestionReponse.put(3,19);
        mQuestionReponse.put(4,16);
        mQuestionReponse.put(5,4);
        mQuestionReponse.put(6,13);
        mQuestionReponse.put(7,24);
        mQuestionReponse.put(8,33);
        mQuestionReponse.put(9,25);
        mQuestionReponse.put(10,37);
        mQuestionReponse.put(11,19);
        mQuestionReponse.put(12,21);
        mQuestionReponse.put(13,42);
        mQuestionReponse.put(14,46);
        mQuestionReponse.put(15,40);
        mQuestionReponse.put(16,26);
        mQuestionReponse.put(17,24);
        mQuestionReponse.put(18,29);
        mQuestionReponse.put(19,23);
        mQuestionReponse.put(20,49);
        mQuestionReponse.put(21,85);
        mQuestionReponse.put(22,51);
        mQuestionReponse.put(23,58);
        mQuestionReponse.put(24,52);
        mQuestionReponse.put(25,69);
        mQuestionReponse.put(26,76);
        mQuestionReponse.put(27,82);
        mQuestionReponse.put(28,77);
        mQuestionReponse.put(29,74);
        mQuestionReponse.put(30,84);

    }

    public static int getReponseId(int idQuestion){
        return mQuestionReponse.get(idQuestion);
    }
}
