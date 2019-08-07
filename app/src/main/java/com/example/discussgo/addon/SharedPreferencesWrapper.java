package com.example.discussgo.addon;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesWrapper {
    private static SharedPreferencesWrapper ourInstance;
    private SharedPreferences sharedPreferences;

    public static SharedPreferencesWrapper getInstance() {
        return ourInstance;
    }

    public static void createInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SharedPreferencesWrapper(context);
        }
    }

    private SharedPreferencesWrapper(Context context) {
        sharedPreferences = context.getSharedPreferences("accessToken", Context.MODE_PRIVATE);
    }

}
