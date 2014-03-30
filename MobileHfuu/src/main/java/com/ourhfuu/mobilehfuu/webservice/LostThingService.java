package com.ourhfuu.mobilehfuu.webservice;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ourhfuu.mobilehfuu.util.CLog;


/**
 * User: sam
 * Date: 11/23/13
 * Time: 1:23 PM
 */
public class LostThingService {
    private Context mContext;
    private RequestQueue mRequestQueue;
    private UrlBuilder mUrlBuilder;

    public LostThingService(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
        mUrlBuilder = new UrlBuilder(new WebConfig());
    }


    public void getLostThing(int id, Response.Listener listener, Response.ErrorListener errorListener) {
        if (id <= 0) {
            return;
        }
        mRequestQueue.add(new StringRequest(mUrlBuilder.buildUrlForGetLostDetail(id), listener, errorListener));
        mRequestQueue.start();
    }

    public void getLostThingList(int flag, int sinceid, Response.Listener listener, Response.ErrorListener errorListener) {
        CLog.i(mUrlBuilder.builderUrlForGetLostList(flag, sinceid));
        mRequestQueue.add(new StringRequest(mUrlBuilder.builderUrlForGetLostList(flag, sinceid), listener, errorListener));
        mRequestQueue.start();
    }

    public void stop() {
        if (mRequestQueue != null) {
            mRequestQueue.stop();
        }
    }


//
//    public void postLostThing(LostThing thing, ResponseHandlerInterface responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("action", "lost_add");
//        params.put("uid", thing.getUid());
//        params.put("username", thing.getUsername());
//        params.put("describe", thing.getDescribe());
//        params.put("flag", thing.getFlag());
//        params.put("place", thing.getPlace());
//        params.put("tid", thing.getTid());
//        params.put("lianxi", thing.getLianxi());
//        params.put("name", thing.getName());
//        mHttpClient.post(mContext, mContext.getString(R.string.host), params, responseHandler);
//    }

}
