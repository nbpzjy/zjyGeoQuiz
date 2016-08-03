#GeoQuiz应用的开发
---
>这个是AndroidProgramming的第一个实例工程，按照这本书来学习吧。

- 个人觉得非常不错，很多东西都可以规范起来，比如命名规范啊，不用硬编码啊什么的。这个应该是习惯问题，如果形成习惯了，估计之后就难改了。

- 从这里开始起航吧，看了很多视频，包括潭州学院的课，都不是很规范，不是说讲的不好。潭州学院的Dream老师比较牛逼，规范什么的都比较正统。

2016.8.2完成的是第一章，搭建基础的MainActivity，以及布局文件。做了一个简单的页面，实现点击之后Toast一个信息。这个以前虽然做过很多次，个人还是很认真地完成了。

![第一章完成的界面图](https://github.com/nbpzjy/zjyGeoQuiz/blob/master/Image-folder/screenshot_first_chapter.png)

----

2016.8.3完成第二章，总体讲，扩展了GeoQuiz的问题数量，采用Strings手创建；创建一个类来作为Question类，实现构造方法，getter和setter方法；修改Layout文件，增加上一题下一题的按钮；MainActivity中代码更新，采用封装公用代码做一个updateQuestion（）更新上一题下一题时的问题显示，新建一个方法checkAnsers（）方法来检验用户点击正确与否，并toast一个信息。

![第二章完成的界面图](https://github.com/nbpzjy/zjyGeoQuiz/blob/master/Image-folder/screenshot_second_chapter_1.png)

Question类，参数有两个

```
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

```

公用代码封装方法

```
 //封装显示问题的公用代码
    private void updateQuestion(){

        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mTextViewShowQuestion.setText(question);

    }

```

引用直接updateQuestion（）即可

```
mNextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionsBank.length;

                //调用updateQuestion（）显示点击下一题之后的问题内容
                updateQuestion();
            }
        });

```

判断用户选择正确与否的方法checkAnswer（）

```

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

```

-----