package com.example.administrator.a18master.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.a18master.MainActivity;
import com.example.administrator.a18master.R;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.example.administrator.a18master.utils.RegexUtils;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends MvpActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.et_username)
    EditText et_userName;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_pwdAgain)
    EditText et_pwdAgain;
    @BindView(R.id.btn_register)
    Button btn_register;
//    @BindView(R.id.register_toolbar)
//    Toolbar registerToolbar;

    private String username;
    private String password;
    private String pwd_again;
    private ActivityUtils activityUtils;
    private ProgressDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
        activityUtils = new ActivityUtils(this);
        init();
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();//创建注册的业务类
    }

    private void init() {
        //给左上角加一个返回图标，需要重写菜单点击事件，否则点击无效
//        setSupportActionBar(registerToolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("注册");

        }

        et_userName.addTextChangedListener(textWatcher);
        et_pwd.addTextChangedListener(textWatcher);
        et_pwdAgain.addTextChangedListener(textWatcher);
    }

    //给左上角加一个返回图标，需要重写菜单点击事件，否则点击无效
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //如果点击的是返回键，则finish
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            username = et_userName.getText().toString();
            password = et_pwd.getText().toString();
            pwd_again = et_pwdAgain.getText().toString();
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(pwd_again));
            btn_register.setEnabled(canLogin);
        }
    };

    @OnClick(R.id.btn_register)
    public void onClick() {
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            String msg = getString(R.string.username_rules);
            showUserPasswordError(msg);
            return;
        } else if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            String msg = getString(R.string.password_rules);
            showUserPasswordError(msg);
            return;
        } else if (!TextUtils.equals(password, pwd_again)) {
            String msg = getString(R.string.username_equal_pwd);
            showUserPasswordError(msg);
            return;
        }

        //业务类执行注册的业务
        presenter.register(username, password);

    }

    //##########################################     视图接口的实现    ######################

    @Override
    public void showPrb() {
        //关闭软键盘
        activityUtils.hideSoftKeyboard();
        //初始化“进度条”
        if (dialogFragment == null) dialogFragment = new ProgressDialogFragment();
        //如果已经显示，则跳出
        if (dialogFragment.isVisible()) return;
        //"进度条"显示
        dialogFragment.show(getSupportFragmentManager(), "progress_dialog_fragment");
    }

    @Override
    public void hidePrb() {
        dialogFragment.dismiss();
    }

    @Override
    public void registerSuccess() {
        //成功跳转到主页
        activityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void registerFailed() {
        et_userName.setText("");
    }

    @Override
    public void showMsg(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void showUserPasswordError(String msg) {
        //展示弹出，提示错误信息
        AlertDialogFragment fragment = AlertDialogFragment.newInstance(msg);
        fragment.show(getSupportFragmentManager(), getString(R.string.username_pwd_rule));
    }
}
