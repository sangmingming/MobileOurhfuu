package com.ourhfuu.mobilehfuu.entity;

/**
 * Created with IntelliJ IDEA.
 * User: sam
 * Date: 11/23/13
 * Time: 3:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ErrorResult {

    private int errorcode;
    private String msg;

    public ErrorResult(int errorcode, String msg) {
        this.errorcode = errorcode;
        this.msg = msg;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
