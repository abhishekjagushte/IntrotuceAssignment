package com.abhishekjagushte.introtuceassignment;

import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //FirebaseOptions firebaseOptions = FirebaseOptions.fromResource(this.getApplicationContext());
        FirebaseApp.initializeApp(this);
        Log.d("Application", "onCreate: initialized");
    }
}
