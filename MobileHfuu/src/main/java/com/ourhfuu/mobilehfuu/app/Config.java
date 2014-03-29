package com.ourhfuu.mobilehfuu.app;

import java.util.*;

/**
 * User: sam
 * Date: 11/23/13
 * Time: 1:20 PM
 */
public class Config {


    public static List<Category> leftCategory;

    static {
        leftCategory = new ArrayList<Category>();
        leftCategory.add(new Category(1, "校园新闻"));
        leftCategory.add(new Category(2, "失物招领"));
    }


}

class Category {

    String name;
    int id;

    public Category() {
    }

    public Category(int cid, String cname) {
        name = cname;
        id = cid;
    }
}
