package com.ourhfuu.mobilehfuu.webservice;

import android.content.Context;
import com.android.volley.GsonRequest;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.ourhfuu.mobilehfuu.app.R;
import com.ourhfuu.mobilehfuu.entity.Article;
import com.ourhfuu.mobilehfuu.util.CLog;

import java.util.HashMap;
import java.util.Map;

/**
 * User: sam
 * Date: 11/23/13
 * Time: 5:52 PM
 */
public class ArticleService {
    private Context mContext;
    private RequestQueue mRequestQueue;
    private UrlBuilder mUrlBuilder;

    public ArticleService(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
        mUrlBuilder = new UrlBuilder(new WebConfig());
    }

    public void getArticle(int id, Response.Listener listener, Response.ErrorListener errorListener) {
        if (id <= 0) {
            return;
        }
        mRequestQueue.add(new StringRequest(mUrlBuilder.buildUrlForGetArticleDetail(id), listener, errorListener));
        mRequestQueue.start();
    }

    public void getArticleList(int cid, int sinceid, Response.Listener listener, Response.ErrorListener errorListener) {
        CLog.i(mUrlBuilder.buildUrlForGetArticleList(cid, sinceid));
        mRequestQueue.add(new StringRequest(mUrlBuilder.buildUrlForGetArticleList(cid, sinceid), listener, errorListener));
        mRequestQueue.start();
    }

    public void stop() {
        if (mRequestQueue != null) {
            mRequestQueue.stop();
        }
    }

}
