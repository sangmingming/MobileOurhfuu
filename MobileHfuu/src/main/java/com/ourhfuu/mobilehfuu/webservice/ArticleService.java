package com.ourhfuu.mobilehfuu.webservice;

import android.content.Context;
import com.android.volley.GsonRequest;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.ourhfuu.mobilehfuu.app.R;
import com.ourhfuu.mobilehfuu.entity.Article;

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

    public void getArticle(int id) {
        if (id <= 0) {
            return;
        }
        mRequestQueue.add(new StringRequest(mUrlBuilder.buildUrlForGetArticleDetail(id),null,null));
        mRequestQueue.start();
    }

//    public void getArticle(int id, ResponseHandlerInterface responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("action", "article_detail");
//        params.put("aid", String.valueOf(id));
//        mHttpClient.get(mContext, mContext.getString(R.string.host), params, responseHandler);
//    }
//
//    public void getArticleList(int cid,int sinceid, ResponseHandlerInterface responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("action", "article_list");
//        params.put("cid", String.valueOf(cid));
//        params.put("sinceid", String.valueOf(sinceid));
//        mHttpClient.get(mContext, mContext.getString(R.string.host), params, responseHandler);
//    }
//
//    public void getCategoryList(ResponseHandlerInterface responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("action", "category_list");
//        mHttpClient.get(mContext, mContext.getString(R.string.host), params, responseHandler);
//    }

}
