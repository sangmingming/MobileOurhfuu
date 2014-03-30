package com.ourhfuu.mobilehfuu.webservice.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ourhfuu.mobilehfuu.entity.Category;


import java.util.List;

/**
 * User: sam
 * Date: 11/23/13
 * Time: 1:27 PM
 */
public class CategoryParser extends JParser<Category>{

    @Override
    protected Category parserDate(Gson gson, JsonElement element) {
        return gson.fromJson(element, Category.class);
    }

    @Override
    protected List<Category> parserListDate(Gson gson, JsonElement element) {
        return gson.fromJson(element, new TypeToken<List<Category>>(){}.getType());
    }
}
