package com.example.administrator.a18master.mvplogin.Login;

import android.text.TextUtils;


import com.example.administrator.a18master.mvplogin.Bean.BaseBean.HttpResultO;
import com.example.administrator.a18master.mvplogin.Bean.User;
import com.example.administrator.a18master.mvplogin.Utils.AppContext;
import com.example.administrator.a18master.mvplogin.Utils.http.Funcs;
import com.example.administrator.a18master.mvplogin.Utils.http.HttpRetrofit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * 登录的业务逻辑
 */
class LoginBiz implements LoginContract.LoginBizImpl {
    private LoginContract.onLoginListener mOnLoginListen;
    private CompositeSubscription mCompositeSubscription;

    LoginBiz(LoginContract.onLoginListener onLoginListen, CompositeSubscription compositeSubscription) {
        this.mOnLoginListen = onLoginListen;
        this.mCompositeSubscription = compositeSubscription;
    }

    @Override
    public void login(final String mobile, final String captcha) {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                subscriber.onNext(new String[]{mobile, captcha});
            }
        }).filter(new Func1<String[], Boolean>() {
            @Override
            public Boolean call(String[] strings) {
                if (TextUtils.isEmpty(strings[0]) || TextUtils.isEmpty(strings[1])) {
                    mOnLoginListen.loginResultCallBack("电话号码或验证码不能为空");
                    return false;
                }
                if (strings[0].length() != 11) {
                    mOnLoginListen.loginResultCallBack("电话号码长度错误");
                    return false;
                }
                if (strings[1].length() != 6) {
                    mOnLoginListen.loginResultCallBack("验证码长度错误");
                    return false;
                }
                mOnLoginListen.progressDialogCallBack(true);
                return true;
            }
        }).flatMap(new Func1<String[], Observable<HttpResultO<User>>>() {
            @Override
            public Observable<HttpResultO<User>> call(String[] strings) {
                return HttpRetrofit.getInstance().login(strings[0], captcha);
            }
        }).map(new Funcs.HttpResultFuncO<User>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        AppContext.Log("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mOnLoginListen.progressDialogCallBack(false);
                        mOnLoginListen.loginResultCallBack(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        mOnLoginListen.progressDialogCallBack(false);
                        mOnLoginListen.loginResultCallBack("登录成功");
                    }
                });
        this.mCompositeSubscription.add(subscribe);
    }

    @Override
    public void getCaptcha(final String mobile) {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(mobile);
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                if (TextUtils.isEmpty(s) || s.length() != 11) {
                    mOnLoginListen.sendCaptchaCallBack("电话长度错误");
                    return false;
                }
                return true;
            }
        }).flatMap(new Func1<String, Observable<HttpResultO<Object>>>() {
            @Override
            public Observable<HttpResultO<Object>> call(String s) {
                return HttpRetrofit.getInstance().getCaptcha(s);
            }
        }).map(new Funcs.HttpResultFuncO<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        AppContext.Log("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mOnLoginListen.sendCaptchaCallBack(e.getMessage());
                    }

                    @Override
                    public void onNext(Object object) {
                        mOnLoginListen.sendCaptchaCallBack("验证码发送成功！");
                    }
                });
        this.mCompositeSubscription.add(subscribe);
    }
}
