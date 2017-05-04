package com.example.administrator.a18master.mvplogin.Login;

public interface LoginContract {

    interface LoginPresenterImpl {
        void login();

        void getCaptcha();

        void unRegister();
    }

    interface onLoginListener {
        void loginResultCallBack(String msg);

        void sendCaptchaCallBack(String msg);

        void progressDialogCallBack(boolean isShow);
    }

    interface LoginBizImpl {
        void login(String mobile, String captcha);

        void getCaptcha(String mobile);
    }

    interface LoginView {
        String getMobile();

        String getCaptcha();

        void onLoginResultCallBack(String msg);

        void sendCaptchaCallBack(String msg);

        void progressDialogCallBack(boolean isShow);
    }
}
