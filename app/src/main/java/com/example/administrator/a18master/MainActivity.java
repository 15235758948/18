package com.example.administrator.a18master;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.a18master.base.banner.BannerAdapter;
import com.example.administrator.a18master.base.banner.BannerLayout;
import com.example.administrator.a18master.base.banner.LocalBanner;
import com.example.administrator.a18master.home.FragmentZhuanAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity{

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
//        底部导航+Fragment切换
        init();

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
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, shopFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.rbFind:
                        focusFragment = new FocusFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, focusFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.rbMe:
                        myFragment = new MyFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, myFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    default:
                        break;
                }

            }
        });
    }




}