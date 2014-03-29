package com.ourhfuu.mobilehfuu.webservice.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ourhfuu.mobilehfuu.entity.Article;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 11/23/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleParser extends JParser<Article>{

    @Override
    protected Article parserDate(Gson gson, JsonElement element) {
        return gson.fromJson(element, Article.class);
    }

    @Override
    protected List<Article> parserListDate(Gson gson, JsonElement element) {
        return gson.fromJson(element, new TypeToken<List<Article>>(){}.getType());
    }
}
