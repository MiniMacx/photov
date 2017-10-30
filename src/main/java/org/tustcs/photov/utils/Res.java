package org.tustcs.photov.utils;

import org.json.JSONObject;

/**
 * Created by polykickshaw on 17-6-6.
 */
public class Res<T> {
    private String msg;
    private int status = 0;
    private T data;

    public static String jsonToString(JSONObject jsonObject){
        return jsonObject.toString();
    }

    public Res() {
    }

    public Res(String msg, int status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public Res setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Res setStatus(int status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public Res setData(T data) {
        this.data = data;
        return this;
    }
}
