//package com.komisonaerdhakabivagerkagaloy.Utils;
//
///**
// * Created by User on 7/26/2016.
// */
//import android.app.Application;
//import android.support.v7.app.AppCompatActivity;
//
//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.GoogleApiClient;
//
//public class MyApplication extends Application {
//    private GoogleApiClient mGoogleApiClient;
//    private GoogleSignInOptions gso;
//    public AppCompatActivity activity;
//    public GoogleSignInOptions getGoogleSignInOptions(){
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        return gso;
//    }
//    public GoogleApiClient getGoogleApiClient(AppCompatActivity activity, GoogleApiClient.OnConnectionFailedListener listener){
//        this.activity = activity;
//
//       mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this.activity, listener)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, getGoogleSignInOptions())
//                .build();
//
//
//
//        return mGoogleApiClient;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        //
//
//    }
//}