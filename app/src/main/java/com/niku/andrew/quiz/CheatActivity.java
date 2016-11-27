package com.niku.andrew.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String ANSWER_IS_TRUE = "com.niku.andrew.quiz.answer_is_true";
    private boolean mAnswerIsTrue;
    private TextView mShowAnswer;

    public static Intent newIntent(Context context, boolean answerIsTrue) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(ANSWER_IS_TRUE, answerIsTrue);
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

        mAnswerIsTrue = getIntent().getBooleanExtra(ANSWER_IS_TRUE, false);

        mShowAnswer = (TextView) findViewById(R.id.answer_text);
    }

    public void btnShowAnswerOnClick(View view) {
        if (mAnswerIsTrue) {
            mShowAnswer.setText(R.string.btnTrueText);
        } else {
            mShowAnswer.setText(R.string.btnFalseText);
        }
    }

}
