package com.learn.terry.exitactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Terry on 2016/7/8.
 */
public class BaseActivity extends AppCompatActivity {
    public static final String EXIT_ACTION = "exit.action";
    private BroadcastReceiver mExitReceiver = new ExitReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.EXIT_TYPE.equalsIgnoreCase("broadcast")) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(EXIT_ACTION);

            registerReceiver(mExitReceiver, intentFilter);
        } else if (BuildConfig.EXIT_TYPE.equalsIgnoreCase("stack")) {
            ActivityStack.addActivity(this);
        }
    }

    private class ExitReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.log("action = " + action);
            if (action.equals(EXIT_ACTION)) {
                BaseActivity.this.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.EXIT_TYPE.equalsIgnoreCase("broadcast")) {
            unregisterReceiver(mExitReceiver);

        } else if (BuildConfig.EXIT_TYPE.equalsIgnoreCase("stack")) {
            ActivityStack.removeActivity(this);
        }
    }

    protected void exitApplication() {
        LogUtil.log("exit type = " + BuildConfig.EXIT_TYPE);
        if (BuildConfig.EXIT_TYPE.equalsIgnoreCase("broadcast")) {
            Intent intent = new Intent(BaseActivity.EXIT_ACTION);
            sendBroadcast(intent);
        } else if (BuildConfig.EXIT_TYPE.equalsIgnoreCase("stack")) {
            ActivityStack.finishAllActivity();
        } else if (BuildConfig.EXIT_TYPE.equalsIgnoreCase("launchMode")) {
            Intent intent = new Intent(this, ExitActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
