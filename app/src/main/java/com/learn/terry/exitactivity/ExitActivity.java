package com.learn.terry.exitactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Terry on 2016/7/8.
 * email: hqp770@126.com
 */
public class ExitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log(getClass().getSimpleName() + " onCreate...");
        finish();
    }
}
