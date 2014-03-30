package com.ourhfuu.mobilehfuu.webservice.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ourhfuu.mobilehfuu.entity.LostThing;

import java.util.List;

/**
 * User: sam
 * Date: 11/23/13
 * Time: 1:27 PM
 */
public class LostThingParser extends JParser<LostThing>{

    @Override
    protected LostThing parserDate(Gson gson, JsonElement element) {
        return gson.fromJson(element, LostThing.class);
    }

    @Override
    protected List<LostThing> parserListDate(Gson gson, JsonElement element) {
        return gson.fromJson(element, new TypeToken<List<LostThing>>(){}.getType());
    }
}
