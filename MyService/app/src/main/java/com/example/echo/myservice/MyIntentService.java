package com.example.echo.myservice;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        这些代码都在子线程里面执行了
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
