package com.ourhfuu.mobilehfuu.webservice.parser;

import com.android.volley.VolleyError;
import com.ourhfuu.mobilehfuu.entity.ErrorResult;

/**
 * User: sam
 * Date: 11/23/13
 * Time: 3:07 PM
 */
public class ParserException extends VolleyError {
    private ErrorResult mResult;

    public ParserException() {
    }

    public ParserException(String detailMessage) {
        super(detailMessage);
    }

    public ParserException(ErrorResult result) {
        super("error-code:" + result.getErrorcode() + "  msg:" + result.getMsg());
        mResult = result;
    }

    public ParserException(int code, String msg) {
        super("error-code:"+code+"  msg:"+msg);
        mResult = new ErrorResult(code,msg);
    }

    public ErrorResult getError() {
        return mResult;
    }


}
