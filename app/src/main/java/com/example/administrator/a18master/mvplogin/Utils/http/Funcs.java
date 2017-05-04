package com.example.administrator.a18master.mvplogin.Utils.http;


import com.example.administrator.a18master.mvplogin.Bean.BaseBean.HttpResultA;
import com.example.administrator.a18master.mvplogin.Bean.BaseBean.HttpResultO;

import rx.functions.Func1;

public class Funcs {
    public static class HttpResultFuncO<T> implements Func1<HttpResultO<T>, T> {

        @Override
        public T call(HttpResultO<T> httpResultO) {
            if (httpResultO.getB() != 1) {
                throw new RuntimeException(httpResultO.getMsg());
            }
            return httpResultO.getO();
        }
    }

    public static class HttpResultFuncA<T> implements Func1<HttpResultA<T>, T> {

        @Override
        public T call(HttpResultA<T> httpResultA) {
            if (httpResultA.getB() != 1) {
                throw new RuntimeException(httpResultA.getMsg());
            }
            return httpResultA.getA();
        }
    }
}
