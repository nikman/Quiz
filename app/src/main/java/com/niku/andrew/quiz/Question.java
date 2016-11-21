package com.niku.andrew.quiz;

/**
 * Created by andrew on 12.11.16.
 */

public class Question {

    private int mQuestionText;
    private boolean mAnswerTrue;

    public Question(int questionText, boolean answerTrue) {
        mQuestionText = questionText;
        mAnswerTrue = answerTrue;
    }

    public int getQuestionText() {
        return mQuestionText;
    }

    public void setQuestionText(int questionText) {
        mQuestionText = questionText;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
