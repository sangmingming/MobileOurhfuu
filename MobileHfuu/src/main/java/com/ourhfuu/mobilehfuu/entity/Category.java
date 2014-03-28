package com.ourhfuu.mobilehfuu.entity;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 11/23/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Category {
    private int catid;
    private String catname;

    public Category(int catid) {
        this.catid = catid;
    }

    public Category() {
    }

    public Category(int catid, String catname) {
        this.catid = catid;
        this.catname = catname;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catid=" + catid +
                ", catname='" + catname + '\'' +
                '}';
    }
}
