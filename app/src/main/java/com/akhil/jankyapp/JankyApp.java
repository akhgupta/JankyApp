package com.akhil.jankyapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;


public class JankyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        Timber.plant(new Timber.DebugTree());
    }
}