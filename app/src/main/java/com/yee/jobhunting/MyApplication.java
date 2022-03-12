package com.yee.jobhunting;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {

    private final String APP_ID = "f50de7f3acb2e28f3e169eb03ff22148";
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, APP_ID);
    }
}
