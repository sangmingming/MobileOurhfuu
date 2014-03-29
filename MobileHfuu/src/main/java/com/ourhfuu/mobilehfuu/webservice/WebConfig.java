package com.ourhfuu.mobilehfuu.webservice;

/**
 * Created by sam on 14-3-29.
 */
public class WebConfig {

    private final static String HOST = "";
    private final static String TEST_HOST = "";
    private boolean mIsTest = false;

    public String getHost() {
        if (!mIsTest) {
            return HOST;
        } else {
            return TEST_HOST;
        }
    }
}
