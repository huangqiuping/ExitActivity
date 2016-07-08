package com.learn.terry.exitactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Terry on 2016/7/8.
 * email: hqp770@126.com
 */
public class ActivityB extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.message);
        assert textView != null;
        textView.setText(String.format(Locale.CHINA, "Task id = %d\nCurrent Activity is%s", getTaskId(), toString()));

        Button button = (Button) findViewById(R.id.start_activity);
        assert button != null;
        button.setText(R.string.start_activity_C);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.exit_application);
        assert button != null;
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.exit_application:
                exitApplication();
                break;

            case R.id.start_activity:
                intent = new Intent(ActivityB.this, ActivityC.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
