package com.example.arnaudetitia.quizgameproject.action;

/**
 * Created by Arnaud ETITIA on 09/09/2016.
 */
abstract public class ActionManager {

    int mScore;

    abstract public void goodAction();

    abstract public void badAction();

    abstract public int getScore();
}
