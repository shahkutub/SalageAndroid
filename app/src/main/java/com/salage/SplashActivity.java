package com.salage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.icteuro.salage.R;

public class SplashActivity extends AppCompatActivity {



    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.splash);
        context=this;
        initUI();

    }

    private void initUI() {

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(PersistentUser.isLogged(context)){
//                    startActivity(new Intent(context, MainActivity.class));
//                }else{
//                    startActivity(new Intent(context, LoginActivity.class));
//                }
                startActivity(new Intent(context, MainActivityDemo.class));
                finish();
            }
        }, 3000);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (mAdView != null) {
//            mAdView.resume();
//        }
    }

    @Override
    public void onDestroy() {
//        if (mAdView != null) {
//            mAdView.destroy();
//        }
        super.onDestroy();
    }

}
