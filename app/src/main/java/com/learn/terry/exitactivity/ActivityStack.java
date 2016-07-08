package com.learn.terry.exitactivity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvb-sky on 2016/7/8.
 */
public class ActivityStack {
    public static ArrayList<Activity> mActivityStacks = new ArrayList<>();

    public static void addActivity(Activity activity) {
        mActivityStacks.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mActivityStacks.remove(activity);
    }

    public static void finishAllActivity() {
        LogUtil.log("finish all activity...");
        for (Activity activity : mActivityStacks) {
            activity.finish();
        }

        mActivityStacks.clear();
    }
}
