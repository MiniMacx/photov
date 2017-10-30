package org.tustcs.photov.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Airmacx on 2017/10/22.
 */
public class HttpsPost {

    public static String post(String url, RequestBody param) throws IOException {
        final OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).post(param).build();
        Response response=okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }
        else {
            throw new IOException("Unexpected code" + response);
        }
    }

    public static String post(String url) throws IOException{
        final OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Response response=okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }
        else {
            throw new IOException("Unexpected code" + response);
        }
    }
}
