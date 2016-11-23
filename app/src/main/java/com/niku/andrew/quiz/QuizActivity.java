package com.niku.andrew.quiz;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class QuizActivity extends AppCompatActivity {

    private final String TAG = "QuizActivity";
    private final String KEY_INDEX = "index";
    private TextView mQuestionTextView;
    private ImageView mQuestionImage;

    private Question[] mQuestionBank = new Question[]{

            new Question(R.string.question_text_1,
                    true,
                    R.drawable.pacific),
            new Question(R.string.question_text_2,
                    false,
                    R.drawable.nile),
            new Question(R.string.question_text_3,
                    false,
                    R.drawable.en_city_of_constantinople),
            /*new Question(R.string.question_text_3,
                    false,
                    R.drawable.byzantine_constantinople_en)*/
    };

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

    private int mCurrentIndex;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate() called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            Log.d(TAG, "Restoring index " + mCurrentIndex);
        }

        mQuestionTextView = (TextView) findViewById(R.id.questionText);
        updateQuestionText();

        mQuestionImage = (ImageView) findViewById(R.id.questionImage);
        //updateQuestionImage();
        WorkingClass workingClass = new WorkingClass();
        /*Thread thread = new Thread(workingClass);
        thread.start();*/
        this.runOnUiThread(workingClass);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() called");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestionText() {
        int question = mQuestionBank[mCurrentIndex].getQuestionText();
        mQuestionTextView.setText(question);
    }

    /*private void updateQuestionImage() {
        int questionImageId = mQuestionBank[mCurrentIndex].getImageResIs();
        mQuestionImage.setImageResource(questionImageId);
    }*/

    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.message_correct;
        } else {
            messageResId = R.string.message_incorrect;
        }

        Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Quiz Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    public void btnFalseOnClick(View view) {
        checkAnswer(false);
    }

    public void btnTrueOnClick(View view) {
        checkAnswer(true);
    }

    public void btnNextOnClick(View view) {

        if (mCurrentIndex < mQuestionBank.length - 1) {
            setCurrentIndex(mCurrentIndex + 1);
        }
        else {
            setCurrentIndex(0);
        }
        updateQuestionText();
        //updateQuestionImage();
        WorkingClass workingClass = new WorkingClass();
        Thread thread = new Thread(workingClass);
        thread.start();

    }

    public void btnPrevOnClick(View view) {

        if (mCurrentIndex > 0) {
            setCurrentIndex(mCurrentIndex - 1);
        }
        else {
            setCurrentIndex(mQuestionBank.length - 1);
        }
        updateQuestionText();
        //updateQuestionImage();
        WorkingClass workingClass = new WorkingClass();
        Thread thread = new Thread(workingClass);
        thread.start();

    }

    class WorkingClass implements Runnable {

        @Override
        public void run() {
            mQuestionImage.post(new Runnable() {
                @Override
                public void run() {
                    int questionImageId = mQuestionBank[mCurrentIndex].getImageResIs();
                    mQuestionImage.setImageResource(questionImageId);
                    Log.d(TAG, "Loading image...");
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    /*@Override
    public Loader<Integer> onCreateLoader(int id, Bundle args) {

        //Integer i = (Integer) id;

        return new Integer(id);
    }

    @Override
    public void onLoadFinished(Loader<Integer> loader, Integer data) {

    }

    @Override
    public void onLoaderReset(Loader<Integer> loader) {

    }*/
}
