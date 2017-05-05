package com.example.administrator.a18master;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.a18master.my.CachePreferences;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.example.popmenu.PopupMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.rbChat)
    RadioButton rbChat;
    @BindView(R.id.rbAddress)
    RadioButton rbAddress;
    @BindView(R.id.rbFind)
    RadioButton rbFind;
    @BindView(R.id.rbMe)
    RadioButton rbMe;
    @BindView(R.id.tab_menu)
    RadioGroup tabMenu;

    private HomeFragment homeFragment;
    private ShopFragment shopFragment;
    private FocusFragment focusFragment;
    private MyFragment myFragment;
    private RadioGroup myTabRg;
    private PopupMenuView mCustomMenuView;
    //点击2次返回，退出程序
    private int mBackKeyPressedTimes = 0;
//    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        activityUtils= new ActivityUtils(this);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
//        默认不弹出小键盘，点击才弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        底部导航+Fragment切换
        init();
//        底部更多
        initMore();
    }

    private void initMore() {
        mCustomMenuView = new PopupMenuView(this, R.layout.layout_custom_menu);
        mCustomMenuView.inflate(R.menu.menu_pop, new MenuBuilder(this));
    }

    public void init() {
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment)
                .commit();
        myTabRg = (RadioGroup) findViewById(R.id.tab_menu);
        myTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbChat:
                        homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.rbAddress:
                        if (shopFragment == null) {
                            shopFragment = new ShopFragment();
                        }
                        if (CachePreferences.getUser().getName() == null) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, shopFragment)
                                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        }
                        break;
                    case R.id.rbFind:
                        if (CachePreferences.getUser().getName() == null) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            focusFragment = new FocusFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, focusFragment)
                                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        }
                        break;
                    case R.id.rbMe:
                        if (CachePreferences.getUser().getName() == null) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            myFragment = new MyFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, myFragment)
                                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbMore:
//                mPopupMenuView.setOrientation(LinearLayout.VERTICAL);
                mCustomMenuView.setOrientation(LinearLayout.VERTICAL);
                mCustomMenuView.show(v);
                break;
        }
    }
    //点击两次返回退出程序
    @Override
    public void onBackPressed() {
        if (mBackKeyPressedTimes == 0) {
            Toast.makeText(this, "再按一次退出程序 ", Toast.LENGTH_SHORT).show();
            mBackKeyPressedTimes = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mBackKeyPressedTimes = 0;
                    }
                }
            }.start();
            return;
        } else {
           finish();
        }
        super.onBackPressed();
    }
}