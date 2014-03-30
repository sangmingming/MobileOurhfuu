package com.ourhfuu.mobilehfuu.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ourhfuu.mobilehfuu.entity.LostThing;
import com.ourhfuu.mobilehfuu.util.CLog;
import com.ourhfuu.mobilehfuu.util.CToast;
import com.ourhfuu.mobilehfuu.util.StringUtil;
import com.ourhfuu.mobilehfuu.webservice.LostThingService;
import com.ourhfuu.mobilehfuu.webservice.parser.LostThingParser;
import com.ourhfuu.mobilehfuu.webservice.parser.ParserException;


public class LostDetailActivity extends ActionBarActivity {

    public final static String LOST_TING = "lost_thing";

    private TextView mTitle, mContent, mLianxi;
    private LostThing mLostThing;
    private LostThingParser mParser;
    private LostThingService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_detail);
        mLostThing = getIntent().getParcelableExtra(LOST_TING);

        if (mLostThing == null) {
            CToast.showToast(this, "传递的Lostthing无效");
            return;
        }

        mTitle = (TextView) findViewById(R.id.title);
        mContent = (TextView) findViewById(R.id.content);
        mLianxi = (TextView) findViewById(R.id.lianxi);
        String s = mLostThing.getFlag() == LostThing.FOUND ? "[招领]" : "[寻物]" ;
        String st = mLostThing.getFlag() == LostThing.FOUND ? "捡到" : "丢失" ;
        getSupportActionBar().setTitle(s + mLostThing.getName());
        mTitle.setText(mLostThing.getUsername() + StringUtil.friendlyTime(mLostThing.getTime()) +
                "在" + mLostThing.getPlace() + st + mLostThing.getName());

        mService = new LostThingService(this);
        mParser = new LostThingParser();
        mService.getLostThing(mLostThing.getId(), mListener, mErrorListener);

    }

    protected static void openLostDetail(Context context, LostThing thing) {
        Intent intent = new Intent(context, LostDetailActivity.class);
        intent.putExtra(LOST_TING, thing);
        context.startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lost_detail, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mService.stop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Response.Listener<String> mListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                LostThing lost = mParser.parse(response);
                CLog.i(lost);
                mLostThing.setDescribe(lost.getDescribe());
                mLostThing.setLianxi(lost.getLianxi());
                mContent.setText("补充信息:" + mLostThing.getDescribe());
                mLianxi.setText("联系方式:" + mLostThing.getLianxi());
            } catch (ParserException e) {
                CToast.showToast(LostDetailActivity.this, e.getMessage());
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            CToast.showToast(LostDetailActivity.this, error.getMessage());
        }
    };

}
