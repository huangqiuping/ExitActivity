## 1. 使用广播方式
可以创建一个BaseActivity，在其中注册接收处理一个`退出广播`，在其中调用``finish()`方法，finish掉Activity，其他Activity都继承此BaseActivity，当需要退出应用的时候，发送`退出广播`，即可finish全部Activity，进而退出应用。
示例代码如下：

- BaseActivity.java

```
public class BaseActivity extends AppCompatActivity {
    public static final String EXIT_ACTION = "exit.action";
    private BroadcastReceiver mExitReceiver = new ExitReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EXIT_ACTION);

        registerReceiver(mExitReceiver, intentFilter);
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
        unregisterReceiver(mExitReceiver);
    }
}
```

- 其他Activity

```
Intent intent = new Intent();
intent.setAction(BaseActivity.EXIT_ACTION);
sendBroadcast(intent);
```

## 2. 建立保存Activity的List
所有启动的Activity都加到List里，这样，要退出应用时，可以从List里取出所有的Activity来调用`finish()`方法。

示例代码：

- ActivityStack.java
```
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
```

- 在BaseActivity中加入至ActivityStack，或从中移除：
加入
```
ActivityStack.addActivity(this);
```
移除
```
ActivityStack.removeActivity(this);
```

- 退出应用
```
ActivityStack.finishAllActivity();
```

## 3、使用launch mode实现
创建一个空的Activity，在里面调用`finish()`结束自己。在需要退出应用的地方以`FLAG_ACTIVITY_NEW_TASK`与`FLAG_ACTIVITY_CLEAR_TASK`启动该Activity。

示例代码：

- ExitActivity.java
```
public class ExitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log(getClass().getSimpleName() + " onCreate...");
        finish();
    }
}
```

- 退出代码
```
Intent intent = new Intent(this, ExitActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
startActivity(intent);
```
