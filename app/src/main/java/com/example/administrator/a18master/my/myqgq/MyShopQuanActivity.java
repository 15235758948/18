package com.example.administrator.a18master.my.myqgq;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.a18master.R;
import com.example.administrator.a18master.my.myjdyy.FragmentDianFU;
import com.example.administrator.a18master.my.myjdyy.FragmentZaiXian;
import com.example.administrator.a18master.my.myqg.TitleDownAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyShopQuanActivity extends AppCompatActivity {
    @BindView(R.id.id_playlayout)
    TabLayout idPlaylayout;
    @BindView(R.id.id_playviewpager)
    ViewPager idPlayviewpager;

    private ArrayList<Fragment> fragmentsList = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<String>();
    private FragmentManager fragmentManager;
    private FragmentAllQ fragmentAllQ;
    private FragmentNoUse fragmentNoUse;
    private FragmentUse fragmentUse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop_quan);
        ButterKnife.bind(this);
        //        隐藏Actionbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
    }

    private void initView() {
        mTitles.add("全部");
        mTitles.add("未使用");
        mTitles.add("已使用");

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentAllQ = FragmentAllQ.newInstance();
        fragmentNoUse=FragmentNoUse.newInstance();
        fragmentUse=FragmentUse.newInstance();

        fragmentsList.add(fragmentAllQ);
        fragmentsList.add(fragmentNoUse);
        fragmentsList.add(fragmentUse);


        TitleDownAdapter myPagerAdapter = new TitleDownAdapter(getSupportFragmentManager(), fragmentsList, mTitles);
        idPlaylayout.setSelected(true);
        idPlaylayout.setTabsFromPagerAdapter(myPagerAdapter);
        idPlayviewpager.setAdapter(myPagerAdapter);
        idPlaylayout.setupWithViewPager(idPlayviewpager);


        idPlaylayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                idPlayviewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @OnClick({R.id.drawer_wodeqgq, R.id.id_playlayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawer_wodeqgq:
                finish();
                break;
        }
    }

}
