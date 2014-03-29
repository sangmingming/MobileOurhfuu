package com.android.volley;

import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.ourhfuu.mobilehfuu.webservice.parser.ParserException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by sam on 14-3-29.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson mGson;
    private final Class<T> mClazz;
    private final Map<String, String> mHeaders;
    private final Response.Listener<T> mListener;

    public final static String KEY_DATA = "data";
    public final static String KEY_ERROR = "error";
    public final static String KEY_MSG = "msg";

    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mClazz = clazz;
        mHeaders = headers;
        mListener = listener;
        mGson = new GsonBuilder().create();

    }

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mClazz = clazz;
        mHeaders = headers;
        mListener = listener;
        mGson = new GsonBuilder().create();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));

            JsonObject object = mGson.fromJson(json, JsonObject.class);
            if (object.get(KEY_ERROR).getAsInt() != 0) {
                throw new ParserException(object.get(KEY_ERROR).getAsInt(),
                        object.get(KEY_MSG).getAsString());
            }
            return Response.success(
                    mGson.fromJson(object.get(KEY_DATA), mClazz), HttpHeaderParser.parseCacheHeaders(response));
        }catch(ParserException e) {
            return Response.error(new ParseError(e));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
