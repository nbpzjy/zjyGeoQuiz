package com.nbp.geoquiz;

/**
 * Created by zjygzc on 16/8/3.
 */
public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    //构造方法
    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    //Geter and setter方法
    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
