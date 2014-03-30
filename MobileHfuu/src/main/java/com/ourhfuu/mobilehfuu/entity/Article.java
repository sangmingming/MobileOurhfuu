package com.ourhfuu.mobilehfuu.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 11/23/13
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Article implements Parcelable {
    private int aid;
    private int catid;
    private int uid;
    private String username;
    private String title;
    private long dateline;
    private String content;

    public Article() {

    }

    public Article(int aid) {
        this.aid = aid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int adi) {
        this.aid = adi;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDateline() {
        return dateline;
    }

    public void setDateline(long dateline) {
        this.dateline = dateline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", catid=" + catid +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", dateline=" + dateline +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(aid);
        dest.writeInt(catid);
        dest.writeInt(uid);
        dest.writeString(username);
        dest.writeString(title);
        dest.writeLong(dateline);
        dest.writeString(content);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {

        @Override
        public Article createFromParcel(Parcel source) {
            Article article = new Article();
            article.aid = source.readInt();
            article.catid = source.readInt();
            article.uid = source.readInt();
            article.username = source.readString();
            article.title = source.readString();
            article.dateline = source.readLong();
            article.content = source.readString();
            return article;
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[0];
        }
    };
}
