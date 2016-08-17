package com.martin.framework;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
