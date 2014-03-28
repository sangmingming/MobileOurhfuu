package com.ourhfuu.mobilehfuu.app;

import android.support.v7.app.ActionBarActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by sam on 14-3-28.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity {

    protected String mActivityName;
    protected boolean mIsPageRecord;

    public BaseActionBarActivity() {
        super();
        setPageRecord();
    }

    public void onResume() {
        super.onResume();
        if (mIsPageRecord) {
            MobclickAgent.onPageStart(mActivityName); //统计页面
        }
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        if (mIsPageRecord) {
            MobclickAgent.onPageEnd(mActivityName); //统计页面
        }
        MobclickAgent.onPause(this);
    }

    protected abstract void setPageRecord();
}
