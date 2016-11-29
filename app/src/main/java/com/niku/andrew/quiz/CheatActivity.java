package com.niku.andrew.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private final String TAG = "CheatActivity";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.niku.andrew.quiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.niku.andrew.quiz.answer_shown";
    private static final String KEY_INDEX = "index";

    private boolean mAnswerIsTrue;
    private TextView mShowAnswer;
    private boolean mAnswerShown;

    public static Intent newIntent(Context context, boolean answerIsTrue) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mShowAnswer = (TextView) findViewById(R.id.answer_text);

        if (savedInstanceState != null) {
            mAnswerShown = savedInstanceState.getBoolean(KEY_INDEX, false);
        } else {
            mAnswerShown = false;
        }
    }

    public void btnShowAnswerOnClick(View view) {

        if (mAnswerIsTrue) {
            mShowAnswer.setText(R.string.btnTrueText);
        } else {
            mShowAnswer.setText(R.string.btnFalseText);
        }

        setAnswerShownResult(true);

    }

    private void setAnswerShownResult(boolean isAnswerShown) {

        mAnswerShown = isAnswerShown;

        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putBoolean(KEY_INDEX, mAnswerShown);
    }
}
