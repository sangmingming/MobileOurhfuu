package com.ourhfuu.mobilehfuu.app;

import android.support.v4.app.Fragment;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by sam on 14-3-28.
 */
public abstract class BaseFragment extends Fragment{
    protected String mFragmentName;

    public BaseFragment() {
        super();
        setFragmentName();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mFragmentName); //统计页面
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mFragmentName);
    }

    /**
     * 在该方法中填写统计时用到的名称
     */
     protected abstract void setFragmentName();

}
