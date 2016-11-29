package com.niku.andrew.quiz;

/**
 * Created by andrew on 12.11.16.
 */

class Question {

    private int mQuestionText;
    private boolean mAnswerTrue;
    private boolean mQuestionCheated;

    public void setQuestionCheated(boolean questionCheated) {
        mQuestionCheated = questionCheated;
    }

    public boolean isQuestionCheated() {
        return mQuestionCheated;
    }

    public int getImageResIs() {
        return mImageResIs;
    }

    private int mImageResIs;

    public Question(int questionText, boolean answerTrue, int ImageResIs, boolean IsCheated) {
        mQuestionText = questionText;
        mAnswerTrue = answerTrue;
        mImageResIs = ImageResIs;
        mQuestionCheated = IsCheated;
    }

    public Question(int questionText, boolean answerTrue, int ImageResIs) {
        mQuestionText = questionText;
        mAnswerTrue = answerTrue;
        mImageResIs = ImageResIs;
    }

    public int getQuestionText() {
        return mQuestionText;
    }

    /*public void setQuestionText(int questionText) {
        mQuestionText = questionText;
    }*/

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
