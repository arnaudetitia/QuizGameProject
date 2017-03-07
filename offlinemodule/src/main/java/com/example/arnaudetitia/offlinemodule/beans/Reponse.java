package com.example.arnaudetitia.offlinemodule.beans;

import com.example.arnaudetitia.offlinemodule.data.ReponseType;

/**
 * Created by Arnaud ETITIA on 21/12/2016.
 */
public class Reponse {

    int mId;
    String mReponse;
    ReponseType mTypes;

    public Reponse(int mId, String mReponse, ReponseType mTypes) {
        this.mId = mId;
        this.mReponse = mReponse;
        this.mTypes = mTypes;
    }

    public String getReponse() {
        return mReponse;
    }

    public ReponseType getTypes() {
        return mTypes;
    }

    public int getId() {
        return mId;
    }
}
