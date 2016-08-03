package com.nbp.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButtonYes;
    private Button mButtonNo;
    private Button mButtonStar;
    private Button mNextbtn;
    private TextView mTextViewShowQuestion;
    private int mCurrentIndex = 0;

    //手动生成问题数组
    private Question[] mQuestionsBank = new Question[]{

            new Question(R.string.question_beijing,true),
            new Question(R.string.question_guiyang,true),
            new Question(R.string.question_guiling,false),
            new Question(R.string.question_haerbing,false),
            new Question(R.string.question_hangzhou,true),
            new Question(R.string.question_lanzhou,true),
            new Question(R.string.question_ningbo,true),
            new Question(R.string.question_shanghai,false),
            new Question(R.string.question_xiamen,false),

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonYes = (Button) findViewById(R.id.main_yes_btn);
        mButtonNo = (Button) findViewById(R.id.main_no_btn);
        mButtonStar = (Button) findViewById(R.id.main_star_btn);
        mNextbtn = (Button) findViewById(R.id.main_next_btn);
        mTextViewShowQuestion = (TextView) findViewById(R.id.main_question_tv);

        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mTextViewShowQuestion.setText(question);


        //Yes button click action
        mButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.toast_click_yes_btn,Toast.LENGTH_SHORT).show();
            }
        });

        //No button click action
        mButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.toast_click_no_btn,Toast.LENGTH_SHORT).show();
            }
        });

        //Star button click action
        mButtonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.toast_click_star_btn,Toast.LENGTH_LONG).show();
            }
        });

        mNextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionsBank.length;
                int question = mQuestionsBank[mCurrentIndex].getTextResId();
                mTextViewShowQuestion.setText(question);
            }
        });

    }



}
