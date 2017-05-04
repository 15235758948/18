package com.example.administrator.a18master.mvplogin.Utils.http;


import com.example.administrator.a18master.mvplogin.Bean.BaseBean.HttpResultO;
import com.example.administrator.a18master.mvplogin.Bean.User;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;
import static com.example.administrator.a18master.mvplogin.Config.Configs.BASE_URL;

public class HttpRetrofit {
    private static Retrofit retrofit;

    private HttpRetrofit() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new TokenInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(BASE_URL)
                .build();
    }

    private static class SingletonHolder {
        private static final HttpRetrofit INSTANCE = new HttpRetrofit();
    }

    public static HttpRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Observable<HttpResultO<Object>> getCaptcha(String mobile) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile", mobile);
        return retrofit.create(HttpService.LoginRequest.class).getCaptcha(jsonObject);
    }

    public Observable<HttpResultO<User>> login(String mobile, String captcha) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile", mobile);
        jsonObject.addProperty("code", captcha);
        return retrofit.create(HttpService.LoginRequest.class).login(jsonObject);
    }
}
