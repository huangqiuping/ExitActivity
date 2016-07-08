package com.learn.terry.exitactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.message);
        assert textView != null;
        textView.setText(String.format(Locale.CHINA, "Task id = %d\nCurrent Activity is%s", getTaskId(), toString()));

        Button button = (Button) findViewById(R.id.exit_application);
        assert button != null;
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.start_activity);
        assert button != null;
        button.setText(R.string.start_activity_A);
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
                intent = new Intent(MainActivity.this, ActivityA.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime ) > 2000) {
            Toast.makeText(getApplicationContext(), "Press Back Key again to exit application!", Toast.LENGTH_SHORT)
                 .show();
            exitTime = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }
}
