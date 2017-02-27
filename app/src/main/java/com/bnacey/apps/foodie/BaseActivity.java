package com.bnacey.apps.foodie;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;

/**
 * Created by Brian on 2/5/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected static final int RC_SIGN_IN = 1;

    protected FirebaseDatabase firebaseDatabase;
    protected DatabaseReference messagesDatabaseReference;
    protected ChildEventListener childEventListener;
    protected FirebaseAuth firebaseAuth;
    protected FirebaseAuth.AuthStateListener authStateListener;
    protected FirebaseStorage firebaseStorage;
    protected StorageReference storageReference;
    protected FirebaseRemoteConfig firebaseRemoteConfig;
    protected FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        messagesDatabaseReference = firebaseDatabase.getReference().child("restaurants");
        storageReference = firebaseStorage.getReference().child("Restaurants");
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {

                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
