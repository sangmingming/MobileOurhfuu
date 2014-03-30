package com.ourhfuu.mobilehfuu.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 11/23/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class LostThing implements Parcelable {

    public static final int LOST = 0;   //丢失东西
    public static final int FOUND = 1;  //捡到东西

    private int id;
    private long uid;
    private String username;
    private String name;
    private long time;
    private String place;
    private int flag;
    private String describe;
    private String lianxi;
    private int tid;

    public LostThing() {

    }

    public LostThing(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLianxi() {
        return lianxi;
    }

    public void setLianxi(String lianxi) {
        this.lianxi = lianxi;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "LostThing{" +
                "id=" + id +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", place='" + place + '\'' +
                ", flag=" + flag +
                ", describe='" + describe + '\'' +
                ", lianxi='" + lianxi + '\'' +
                ", tid=" + tid +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(uid);
        dest.writeInt(tid);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeLong(time);
        dest.writeString(place);
        dest.writeInt(flag);
        dest.writeString(lianxi);
        dest.writeString(describe);
    }

    public static final Creator<LostThing> CREATOR = new Creator<LostThing>(){

        @Override
        public LostThing createFromParcel(Parcel source) {
            LostThing thing = new LostThing();
            thing.id = source.readInt();
            thing.uid = source.readLong();
            thing.tid = source.readInt();
            thing.username = source.readString();
            thing.name = source.readString();
            thing.time = source.readLong();
            thing.place = source.readString();
            thing.flag = source.readInt();
            thing.lianxi = source.readString();
            thing.describe = source.readString();
            return thing;
        }

        @Override
        public LostThing[] newArray(int size) {
            return new LostThing[0];
        }
    };

}
