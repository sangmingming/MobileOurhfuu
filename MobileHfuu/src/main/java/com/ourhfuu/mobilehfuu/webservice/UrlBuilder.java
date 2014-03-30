package com.ourhfuu.mobilehfuu.webservice;

import android.net.Uri;

/**
 * Created by sam on 14-3-29.
 */
public class UrlBuilder {
    private WebConfig mConfig;
    private Uri mBaseUri;

    private final static String KEY_ACTION = "action";
    private final static String KEY_AID = "aid";
    private final static String KEY_CID = "cid";
    private final static String KEY_SINCEID = "sinceid";

    private final static String ACTION_ARTICLE_DETAIL = "article_detail";
    private final static String ACTION_ARTICLE_LIST = "article_list";

    public UrlBuilder(WebConfig config) {
        mConfig = config;
    }

    private Uri.Builder getBaseBuilder() {
        if (mBaseUri == null) {
            Uri.Builder builder = Uri.parse(mConfig.getHost()).buildUpon();
            mBaseUri = builder.build();
        }
        Uri.Builder builder = mBaseUri.buildUpon();
        return builder;
    }

    public String buildUrlForGetArticleDetail(int id) {
        Uri.Builder builder = getBaseBuilder();
        builder.appendQueryParameter(KEY_ACTION, ACTION_ARTICLE_DETAIL);
        builder.appendQueryParameter(KEY_AID, String.valueOf(id));
        return builder.toString();
    }

    public String buildUrlForGetArticleList(int cid, int sinceid) {
        Uri.Builder builder = getBaseBuilder();
        builder.appendQueryParameter(KEY_ACTION, ACTION_ARTICLE_LIST);
        builder.appendQueryParameter(KEY_CID, String.valueOf(cid));
        builder.appendQueryParameter(KEY_SINCEID, String.valueOf(sinceid));
        return builder.toString();
    }

}
