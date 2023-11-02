package com.openclassrooms.arista;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
