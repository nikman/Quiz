package com.niku.andrew.quiz;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class QuizActivity extends AppCompatActivity {

    private final String TAG = "QuizActivity";
    private final String KEY_INDEX_QUESTION_ID = "index_question_id";
    private final String KEY_INDEX_IS_CHEATER = "index_is_cheater";
    private final int REQUEST_CODE_CHEAT = 0;

    private int mCurrentIndex;
    private boolean mIsCheater;
    //private Integer CountDown = R.integer.MaxSecondsPerQuestion;
    private Integer mCountDownSeconds = 10;
    //private Thread threadCounter;

    private TextView mQuestionTextView;
    private ImageView mQuestionImage;
    private ProgressBar mProgressBar;

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

    /*public int getCurrentIndex() {
        return mCurrentIndex;
    }*/

    private void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

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

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX_QUESTION_ID, 0);
            Log.d(TAG, "Restoring index " + mCurrentIndex);
            mIsCheater = savedInstanceState.getBoolean(KEY_INDEX_IS_CHEATER, false);
            Log.d(TAG, "Restoring Is cheater flag " + mIsCheater);
        }

        mQuestionTextView = (TextView) findViewById(R.id.questionText);
        updateQuestionText();

        mQuestionImage = (ImageView) findViewById(R.id.questionImage);

        this.runOnUiThread(new WorkingClass());
        this.runOnUiThread(new WorkingClassProgressUpdate());

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        Log.d(TAG, "onCreate(), CountDownSeconds = " + mCountDownSeconds.toString());
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
        mIsCheater = false;
        int question = mQuestionBank[mCurrentIndex].getQuestionText();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;

        if (mIsCheater) {
            messageResId = R.string.judgment_toast_text;
        } else {

            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.message_correct;
            } else {
                messageResId = R.string.message_incorrect;
            }
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

        /*if (threadImageLoader != null) {
            threadImageLoader.interrupt();
            threadImageLoader = null;
        }*/

        if (mCurrentIndex < mQuestionBank.length - 1) {
            setCurrentIndex(mCurrentIndex + 1);
        }
        else {
            setCurrentIndex(0);
        }
        updateQuestionText();

        Thread threadImageLoader = new Thread(new WorkingClass());
        threadImageLoader.start();
        mCountDownSeconds = 10;
        new Thread(new WorkingClassProgressUpdate()).start();

    }

    public void btnPrevOnClick(View view) {

        if (mCurrentIndex > 0) {
            setCurrentIndex(mCurrentIndex - 1);
        }
        else {
            setCurrentIndex(mQuestionBank.length - 1);
        }
        updateQuestionText();

        new Thread(new WorkingClass()).start();
        mCountDownSeconds = 10;
        new Thread(new WorkingClassProgressUpdate()).start();

    }

    private class WorkingClass implements Runnable {

        @Override
        public void run() {
            mQuestionImage.post(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "Loading image...");
                    int questionImageId = mQuestionBank[mCurrentIndex].getImageResIs();
                    mQuestionImage.setImageResource(questionImageId);
                    Log.d(TAG, "Image loaded");
                }
            });
        }
    }

    class WorkingClassProgressUpdate implements Runnable {

        public static final int RELAUNCH = 1;
        private static final int DELAY = 1000;

        @Override
        public void run() {
            Log.d(TAG, "-------");
            //Log.d(TAG, String.format("id=%d", threadCounter.getId()));
            Log.d(TAG, mCountDownSeconds.toString());

            mProgressBar.post(new Runnable() {
                @Override
                public void run() {

                    //Log.d(TAG, new Integer(mCountDownSeconds).toString());
                    if (mCountDownSeconds > 0) {
                        mProgressBar.setProgress(--mCountDownSeconds);
                    }
                }
            });
            uiHandler.sendEmptyMessageDelayed(RELAUNCH, DELAY);
        }
    }

    private Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            //Log.d(TAG, "-------");
            //Log.d(TAG, mCountDownSeconds.toString());
            if (message.what == WorkingClassProgressUpdate.RELAUNCH && mCountDownSeconds > 0) {
                new Thread(new WorkingClassProgressUpdate()).start();
                return true;
            }
            return false;
        }
    });

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX_QUESTION_ID, mCurrentIndex);
        savedInstanceState.putBoolean(KEY_INDEX_IS_CHEATER, mIsCheater);
    }

    public void btnCheatOnClick(View view) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        Intent intent = CheatActivity.newIntent(getApplicationContext(), answerIsTrue);
        startActivityForResult(intent, REQUEST_CODE_CHEAT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mQuestionBank[mCurrentIndex].isQuestionCheated()) {
            mIsCheater = true;
            return;
        }

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }

        mQuestionBank[mCurrentIndex].setQuestionCheated(mIsCheater);

    }
}
