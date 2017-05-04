package com.example.administrator.a18master.mvplogin.Login;

import rx.subscriptions.CompositeSubscription;

public class LoginPresenter implements LoginContract.LoginPresenterImpl, LoginContract.onLoginListener {
    private LoginContract.LoginBizImpl mLoginBizImpl;
    private LoginContract.LoginView mLoginView;
    private CompositeSubscription mCompositeSubscription;

    public LoginPresenter(LoginContract.LoginView loginView, CompositeSubscription compositeSubscription) {
        this.mLoginView = loginView;
        mLoginBizImpl = new LoginBiz(this, compositeSubscription);
        this.mCompositeSubscription = compositeSubscription;
    }

    @Override
    public void login() {
        mLoginBizImpl.login(mLoginView.getMobile(), mLoginView.getCaptcha());
    }

    @Override
    public void getCaptcha() {
        mLoginBizImpl.getCaptcha(mLoginView.getMobile());
    }

    @Override
    public void unRegister() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void loginResultCallBack(String msg) {
        mLoginView.onLoginResultCallBack(msg);
    }

    @Override
    public void sendCaptchaCallBack(String msg) {
        mLoginView.sendCaptchaCallBack(msg);
    }

    @Override
    public void progressDialogCallBack(boolean isShow) {
        mLoginView.progressDialogCallBack(isShow);
    }
}
