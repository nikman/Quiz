package com.niku.andrew.quiz;

/**
 * Created by andrew on 12.11.16.
 */

public class Question {

    private int mQuestionText;
    private boolean mAnswerTrue;

    public int getImageResIs() {
        return mImageResIs;
    }

    private int mImageResIs;

    public Question(int questionText, boolean answerTrue, int ImageResIs) {
        mQuestionText = questionText;
        mAnswerTrue = answerTrue;
        mImageResIs = ImageResIs;
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
