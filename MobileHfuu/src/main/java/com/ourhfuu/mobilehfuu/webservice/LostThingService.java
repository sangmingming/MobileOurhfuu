package com.ourhfuu.mobilehfuu.webservice;

import android.content.Context;


/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 11/23/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class LostThingService {
    private Context mContext;

    public LostThingService(Context context) {
        mContext = context;
    }

//    public void getLostThing(int id, ResponseHandlerInterface responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("action", "lost_detail");
//        params.put("id", String.valueOf(id));
//        mHttpClient.get(mContext, mContext.getString(R.string.host), params, responseHandler);
//    }
//
//    public void getLostThingList(int flag,int sinceid, ResponseHandlerInterface responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("action", "lost_list");
//        params.put("flag", flag);
//        params.put("sinceid", sinceid);
//        mHttpClient.get(mContext, mContext.getString(R.string.host), params, responseHandler);
//    }
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
