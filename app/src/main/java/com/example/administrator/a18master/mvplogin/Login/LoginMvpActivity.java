package com.example.administrator.a18master.mvplogin.Login;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.a18master.R;
import com.example.administrator.a18master.mvplogin.Base.BaseActivity;
import com.example.administrator.a18master.mvplogin.Utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

public class LoginMvpActivity extends BaseActivity implements View.OnClickListener, LoginContract.LoginView {
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.btn_get_captcha)
    Button mBtnGetCaptcha;
    @BindView(R.id.et_captcha)
    EditText mEtCaptcha;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    private ProgressDialog mProgressDialog;
    private LoginContract.LoginPresenterImpl mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("登录中...");
        mProgressDialog.setCancelable(false);
        mLoginPresenter = new LoginPresenter(this, new CompositeSubscription());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_loginmvp;
    }

    @OnClick({R.id.btn_get_captcha, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_captcha:
                mLoginPresenter.getCaptcha();
                break;
            case R.id.btn_login:
                mLoginPresenter.login();
                break;
        }
    }

    @Override
    public String getMobile() {
        return mEtMobile.getText().toString().trim();
    }

    @Override
    public String getCaptcha() {
        return mEtCaptcha.getText().toString().trim();
    }

    @Override
    public void onLoginResultCallBack(final String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void sendCaptchaCallBack(final String msg) {
        mBtnGetCaptcha.setText("验证码");
        mBtnGetCaptcha.setEnabled(true);
//        ToastUtils.showToast(msg);
    }

    @Override
    public void progressDialogCallBack(boolean isShow) {
        if (isShow) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        mLoginPresenter.unRegister();
        super.onStop();
    }
}
