package com.ourhfuu.mobilehfuu.webservice.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 11/23/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class JParser<T> {
    public final static String KEY_DATA = "data";
    public final static String KEY_ERROR = "error";
    public final static String KEY_MSG = "msg";
    private Gson mGson;

    public JParser() {
        mGson = new GsonBuilder().create();
    }

    public T parse(String inputString) throws ParserException {
        JsonObject object = mGson.fromJson(inputString, JsonObject.class);
        if (object.get(KEY_ERROR).getAsInt() != 0) {
            throw  new ParserException(object.get(KEY_ERROR).getAsInt(),
                    object.get(KEY_MSG).getAsString());
        }
        return parserDate(mGson, object.get(KEY_DATA));
    }

    abstract protected T parserDate(Gson gson, JsonElement element);

    public List<T> parseList(String inputString) throws ParserException {
        JsonObject object = mGson.fromJson(inputString, JsonObject.class);
        if (object.get(KEY_ERROR).getAsInt() != 0) {
            throw  new ParserException(object.get(KEY_ERROR).getAsInt(),
                    object.get(KEY_MSG).getAsString());
        }
        Type t = new TypeToken<T>(){}.getType();
        return parserListDate(mGson, object.get(KEY_DATA));
    }

    abstract protected List<T> parserListDate(Gson gson, JsonElement element);
}
