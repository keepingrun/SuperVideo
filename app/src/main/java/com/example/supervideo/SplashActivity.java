package com.example.supervideo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private static final int GUIDE_OR_HOEME=1001;
    private static final int ENTER_DURATION=2000;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case GUIDE_OR_HOEME:
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    }
                    finish();
                    break;
                    default:
                        break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences=getSharedPreferences("config",0);
        mHandler.sendEmptyMessageDelayed(GUIDE_OR_HOEME,ENTER_DURATION);
    }
    private boolean isFirst() {
        boolean isFirst=mSharedPreferences.getBoolean("isFirst",true);
        if(isFirst){
            //mSharedPreferences.edit().putBoolean("isFirst",false).apply();
            return true;
        }else{
            return false;
        }

    }
}
