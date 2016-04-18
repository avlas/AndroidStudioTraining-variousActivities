package com.formation.activities;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class LeakCanaryApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
