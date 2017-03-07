package com.example.arnaudetitia.offlinemodule.beans;

import com.example.arnaudetitia.offlinemodule.data.ReponseType;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class Question {

    int mId;
    String mQuestion;
    ReponseType mTypes;

    public Question(int mId, String mQuestion, ReponseType mTypes) {
        this.mId = mId;
        this.mQuestion = mQuestion;
        this.mTypes = mTypes;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public ReponseType getTypes() {
        return mTypes;
    }

    public int getId() {
        return mId;
    }
}
