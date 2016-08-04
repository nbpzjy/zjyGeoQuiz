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

2016.8.4主要是Activity生命周期的问题，解决在横竖屏转换时出现当前问题呗重置的BUG，学会LOG过滤器的设置

>主要的原因是由于在应用的运行中，当当前的设备配置发生改变时，Android会销毁当前的activity，然后再重新创建，所以在横竖屏转换时，就是配置发生了改变，所以被系统销毁了activity，然后再重新创建，所以问题又回到了第一个。下图中是设置横竖屏转换后的LOG输出，可以看到当转换时，先调用onPause（），之后调用onsaveInstanceState（）来保存，然后onStop（）->onDestroy（），然后再onCreate（）-> onStart（）->onResume()

![第二章完成的界面图](https://github.com/nbpzjy/zjyGeoQuiz/blob/master/Image-folder/ss_third_chapter_loginfo.png)


##1

横屏的设置


![第二章完成的界面图](https://github.com/nbpzjy/zjyGeoQuiz/blob/master/Image-folder/ss_third_chapter_land.png)

先需要在res文件下创建一个Android resources directory文件夹，layout, orintation选择之后选择landscape，生成一个layout-land文件夹存放所以横屏时的layout资源文件，由系统自动调用。注意保证资源文件的名字一定要跟layout文件里的名字一样

横屏中采用了Framelayout，代码是这样，位置不是很好控制，以后再学学

```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    ...
    
</FrameLayout>    

```

##2
LOG


|LogLevel|Method|说明|
|--------|------|----|
|Error|Log.e(...)|错误信息|
|WARNNING|Log.w(...)|警告信息|
|INFO|Log.i(...)|信息型消息|
|DEBUG|Log.d(...)|调试输出：可能被过滤掉|
|VERBOSE|Log.v(...)|只用于开发|

验证生命周期的Log是这样的：

```
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"---------onCreate(Bundle) 调用了");
        setContentView(R.layout.activity_main);
	
	...


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

```

上面的LOG信息，除了`onCreate（）`中的必须在super之后之外，其他5个无所谓是不是在超类的前后


![第二章完成的界面图](https://github.com/nbpzjy/zjyGeoQuiz/blob/master/Image-folder/ss_third_chapter_loginfo.png)



##3

> 怎么实现在横竖屏转换时保持当前的题不变呢？

由于之前说过，横竖屏转换时会被销毁activity之后重建，所以需要保存当前的数据，在再创建的时候调用，来保证横竖屏转换后内容不变。这里用到了onSaveInstanceState（）方法。

就是重写onSaveInstanceState（）方法，保存一个Bundle数据，在重建使得onCreate（Bundle）方法中取出这个Bundle，在updateQuestion()之前，先判断是不是获取了之前的Bundle，否则就显示mCurrentInex的初始值0.

代码

首先创建一个全局常量

` private static final String KEY_INDEX = "index";`

然后在`onSaveInstanceState（Bundle）`中，把当前的mCurrent值赋予KEY_INDEX, `avedIstanceState.putInt(KEY_INDEX,mCurrentIndex);`


```

   //保存InstanceState到Bundle
    @Override
    protected void onSaveInstanceState(Bundle savedIstanceState) {
        super.onSaveInstanceState(savedIstanceState);
        Log.i(TAG,"onSaveInstanceState被调用了");
        savedIstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

```

之后在onCreate（）方法中调用
并把KEY_INDEX的数据传给mCurrent，这样就完成了保存Bundle用于创建新的Activity

```

        //调用之前的Bundle数据，先检查是不是获取成功
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        //调用updateQuestion（）显示初始的问题内容
        updateQuestion();

```

-----