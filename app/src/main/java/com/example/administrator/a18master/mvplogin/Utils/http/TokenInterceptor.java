package com.example.administrator.a18master.mvplogin.Utils.http;

import android.text.TextUtils;

import com.example.administrator.a18master.mvplogin.Utils.AppContext;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        String setCookie = proceed.header("Set-Cookie");
        if (!TextUtils.isEmpty(setCookie)) {
            //起始位置
            int startPos = setCookie.indexOf("t=");
            //截取位置
            int subPos = setCookie.indexOf(";", startPos);
            String token = setCookie.substring(startPos + 2, subPos);
            AppContext.saveLoginToken(token);
        }
        return proceed;
    }
}
