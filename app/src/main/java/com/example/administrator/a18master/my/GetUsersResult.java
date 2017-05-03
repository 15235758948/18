package com.example.administrator.a18master.my;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class GetUsersResult {

    private int code;
    @SerializedName("msg")
    private String message;

    private List<User> datas;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<User> getDatas() {
        return datas;
    }
}
