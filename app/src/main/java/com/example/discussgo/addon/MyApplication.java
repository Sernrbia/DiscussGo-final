package com.example.discussgo.addon;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesWrapper.createInstance(this);
    }

}