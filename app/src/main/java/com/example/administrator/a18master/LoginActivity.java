package com.example.administrator.a18master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.a18master.login.LoginPresenter;
import com.example.administrator.a18master.login.LoginView;
import com.example.administrator.a18master.register.ProgressDialogFragment;
import com.example.administrator.a18master.register.RegisterActivity;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/20.
 */

public class LoginActivity extends MvpActivity<LoginView,LoginPresenter> implements LoginView {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_userpass)
    EditText loginUserpass;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.btn_login)
    Button btnLogin;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    private String username;
    private String password;
    private ActivityUtils activityUtils;
    private ProgressDialogFragment dialogFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        loginUsername.addTextChangedListener(textWatcher);
        loginUserpass.addTextChangedListener(textWatcher);
//        //给左上角加一个返回图标
//        setSupportActionBar(toolbar);
//        //设置为ture，显示返回图标，但是点击效果需要实现菜单点击事件
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //如果点击的是返回键，则finish
//        if (item.getItemId() == android.R.id.home) finish();
//        return super.onOptionsItemSelected(item);
//    }

    private TextWatcher textWatcher = new TextWatcher() {
        //这里的s表示改变之前的内容，通常start和count组合，可以在s中读取本次改变字段中被改变的内容。
        //而after表示改变后新的内容的数量
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        //这里的s表示改变之后的内容，通常start和count组合，可以在s中读取本次改变字段中新的内容。
        //而before表示被改变的内容的数量。
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        //表示最终内容
        @Override
        public void afterTextChanged(Editable s) {
            username = loginUsername.getText().toString();
            password = loginUserpass.getText().toString();
            //判断用户名和密码是否为空
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password));
            btnLogin.setEnabled(canLogin);
        }
    };

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.btn_login, R.id.register,R.id.login_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //调用业务类的登录方法
                presenter.login(username, password);
                break;
            case R.id.register:
                activityUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.login_back:
                activityUtils.startActivity(MainActivity.class);
        }
    }
    //###############################  视图接口相关 ###############
    @Override
    public void showPrb() {
        activityUtils.hideSoftKeyboard();
        if (dialogFragment == null) dialogFragment = new ProgressDialogFragment();
        if (dialogFragment.isVisible()) return;
        dialogFragment.show(getSupportFragmentManager(),"progress_dialog_fragment");
    }

    @Override
    public void hidePrb() {
        dialogFragment.dismiss();
    }

    @Override
    public void loginFailed() {
        loginUsername.setText("");
    }

    @Override
    public void loginSuccess() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void showMsg(String msg) {
        activityUtils.showToast(msg);
    }
}
