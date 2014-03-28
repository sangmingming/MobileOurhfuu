package com.ourhfuu.mobilehfuu.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.analytics.MobclickAgent;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        MobclickAgent.updateOnlineConfig(this);
        MobclickAgent.openActivityDurationTrack(false); //禁用默认统计页面的样式
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen"); //统计页面
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); //统计页面
        MobclickAgent.onPause(this);
    }
}
