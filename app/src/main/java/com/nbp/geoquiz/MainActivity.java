package com.nbp.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButtonYes;
    private Button mButtonNo;
    private Button mButtonStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonYes = (Button) findViewById(R.id.main_yes_btn);
        mButtonNo = (Button) findViewById(R.id.main_no_btn);
        mButtonStar = (Button) findViewById(R.id.main_star_btn);

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

    }
}
