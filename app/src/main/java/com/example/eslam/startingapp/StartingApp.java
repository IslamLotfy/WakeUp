package com.example.eslam.startingapp;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.*;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Eslam on 1/15/2017.
 */
public class StartingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        if(!FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
