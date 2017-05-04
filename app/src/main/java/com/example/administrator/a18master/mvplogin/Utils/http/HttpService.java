package com.example.administrator.a18master.mvplogin.Utils.http;



import com.example.administrator.a18master.mvplogin.Bean.BaseBean.HttpResultO;
import com.example.administrator.a18master.mvplogin.Bean.User;
import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import static com.example.administrator.a18master.mvplogin.Config.Configs.GET_CAPTCHA;
import static com.example.administrator.a18master.mvplogin.Config.Configs.PHONE_LOGIN;

interface HttpService {
    interface LoginRequest {
        @POST(PHONE_LOGIN)
        Observable<HttpResultO<User>> login(@Body JsonObject jsonObject);

        @POST(GET_CAPTCHA)
        Observable<HttpResultO<Object>> getCaptcha(@Body JsonObject jsonObject);
    }
}
