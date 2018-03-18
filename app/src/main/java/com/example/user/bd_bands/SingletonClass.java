package com.example.user.bd_bands;

import android.app.Application;

/**
 * Created by sazzad on 1/13/18.
 */

public class SingletonClass extends Application {

    private static SingletonClass singleton;
    public static int loginstate=0;
    public static int bandid=0;
    public static int userid=0;


    public static SingletonClass getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
