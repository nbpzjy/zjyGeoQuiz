package com.nbp.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButtonYes;
    private Button mButtonNo;
    private Button mButtonStar;
    private Button mNextbtn;
    private Button mPreviousbtn;
    private TextView mTextViewShowQuestion;
    private int mCurrentIndex = 0;
    private static final String TAG = "GeoQuizActivity";
    private static final String KEY_INDEX = "index";

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

    //封装显示问题的公用代码
    private void updateQuestion(){

        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mTextViewShowQuestion.setText(question);

    }

    //判断点击是不是正确
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.toast_correct;
        } else {
            messageResId = R.string.toast_wrong;
        }

        Toast.makeText(MainActivity.this,messageResId,Toast.LENGTH_SHORT).show();
    }

    //保存InstanceState到Bundle
    @Override
    protected void onSaveInstanceState(Bundle savedIstanceState) {
        super.onSaveInstanceState(savedIstanceState);
        Log.i(TAG,"onSaveInstanceState被调用了");
        savedIstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"---------onCreate(Bundle) 调用了");
        setContentView(R.layout.activity_main);

        mButtonYes = (Button) findViewById(R.id.main_yes_btn);
        mButtonNo = (Button) findViewById(R.id.main_no_btn);
        mButtonStar = (Button) findViewById(R.id.main_star_btn);
        mNextbtn = (Button) findViewById(R.id.main_next_btn);
        mTextViewShowQuestion = (TextView) findViewById(R.id.main_question_tv);
        mPreviousbtn = (Button) findViewById(R.id.main_previous_btn);


        //调用之前的Bundle数据，先检查是不是获取成功
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        //调用updateQuestion（）显示初始的问题内容
        updateQuestion();



        //Log a message at "debug" log level
        Log.d(TAG,"当前的问题的标签是：" + mCurrentIndex);

        Question question;
        try{
            question = mQuestionsBank[mCurrentIndex];
        } catch (ArrayIndexOutOfBoundsException ex) {
            Log.e(TAG, "Index was out of bounds", ex);
        }



        //Yes button click action
        mButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,R.string.toast_click_yes_btn,Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });

        //No button click action
        mButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,R.string.toast_click_no_btn,Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        //Star button click action
        mButtonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.toast_click_star_btn,Toast.LENGTH_LONG).show();
            }
        });

        //实现下一题
        mNextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionsBank.length;

                //调用updateQuestion（）显示点击下一题之后的问题内容
                updateQuestion();
            }
        });


        //实现上一题
        mPreviousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;

                    //调用updateQuestion（）显示点击上一题之后的问题内容
                    updateQuestion();
                }else{
                    mCurrentIndex = (mCurrentIndex+mQuestionsBank.length-1) % mQuestionsBank.length;
                    updateQuestion();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"---------onStart() 调用了");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"---------onPause() 调用了");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"---------onResume() 调用了");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"---------onStop() 调用了");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"---------onDestroy() 调用了");
    }


}
