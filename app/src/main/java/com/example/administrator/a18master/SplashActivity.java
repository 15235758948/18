package com.example.administrator.a18master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.a18master.commons.CurrentUser;
import com.example.administrator.a18master.my.CachePreferences;
import com.example.administrator.a18master.my.User;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.feicuiedu.apphx.model.HxUserManager;
import com.feicuiedu.apphx.model.event.HxErrorEvent;
import com.feicuiedu.apphx.model.event.HxEventType;
import com.feicuiedu.apphx.model.event.HxSimpleEvent;
import com.hyphenate.easeui.domain.EaseUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        activityUtils = new ActivityUtils(this);

        EventBus.getDefault().register(this);

        /**
         * 账号冲突后，自动退出
         * 由apphx模块HxMainService发出
         * */
        if (getIntent().getBooleanExtra("AUTO_LOGIN",false)){
            //清除本地缓存的用户信息
            CachePreferences.clearAllData();
            //踢出时，登出环信
            HxUserManager.getInstance().asyncLogout();
        }
        //当前用户如果是已经登陆过但是未登出的，再次进入需要自动登录
        if (CachePreferences.getUser().getName() != null
                && !HxUserManager.getInstance().isLogin()){
            User user = CachePreferences.getUser();
            EaseUser easeUser = CurrentUser.convert(user);
            HxUserManager.getInstance().asyncLogin(easeUser,user.getPassword());
            return;
        }


        //1.5秒跳转到主页
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //跳转到主页
                activityUtils.startActivity(MainActivity.class);
                finish();
            }
        },1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxSimpleEvent event) {

        // 判断是否是登录成功事件
        if (event.type != HxEventType.LOGIN) return;
        activityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HxErrorEvent event) {

        // 判断是否是登录失败事件
        if (event.type != HxEventType.LOGIN) return;
        throw new RuntimeException("login error");
    }
}
