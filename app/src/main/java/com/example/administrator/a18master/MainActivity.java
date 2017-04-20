package com.example.administrator.a18master;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
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

public class MainActivity extends FragmentActivity {

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
    @BindView(R.id.layout_banner)
    BannerLayout layoutBanner;
    @BindView(R.id.layout_banners)
    BannerLayout layoutBanners;
    @BindView(R.id.layout_banner_shequ)
    ViewPager layoutBannerShequ;
    @BindView(R.id.home_xyd_2)
    ImageView homeXyd2;
    @BindView(R.id.home_xyd_1)
    ImageView homeXyd1;
    @BindView(R.id.home_xyd_3)
    ImageView homeXyd3;
    @BindView(R.id.list_zhuan)
    GridView listZhuan;

    @BindView(R.id.hours_tv)
    TextView hoursTv;
    @BindView(R.id.minutes_tv)
    TextView minutesTv;
    @BindView(R.id.seconds_tv)
    TextView secondsTv;
    @BindView(R.id.countdown_layout)
    RelativeLayout countDown;

    private HomeFragment homeFragment;
    private ShopFragment shopFragment;
    private FocusFragment focusFragment;
    private MyFragment myFragment;
    private RadioGroup myTabRg;
//    计时器
    private long mHour = 10;
    private long mMin = 30;
    private long mSecond = 00;// 天 ,小时,分钟,秒
    private boolean isRun = true;
    private Handler timeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                hoursTv.setText(mHour + "");
                minutesTv.setText(mMin + "");
                secondsTv.setText(mSecond + "");
                if ( mHour == 0 && mMin == 0 && mSecond == 0) {
                    countDown.setVisibility(View.GONE);
                }
            }
        }
    };

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
//       轮播
        initBanner();
        initBanners();
        initView();
        initViewQ();
        startRun();
    }

    public void init() {
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment)
                .commit();
        myTabRg = (RadioGroup) findViewById(R.id.tab_menu);
        myTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.rbChat:
                        homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment)
                                .commit();
                        break;
                    case R.id.rbAddress:
                        if (shopFragment == null) {
                            shopFragment = new ShopFragment();
                        }
                        Log.i("MyFragment", "FragmentAddress");
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, shopFragment)
                                .commit();
                        break;
                    case R.id.rbFind:
                        focusFragment = new FocusFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, focusFragment)
                                .commit();
                        break;
                    case R.id.rbMe:
                        myFragment = new MyFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, myFragment)
                                .commit();
                        break;
                    default:
                        break;
                }

            }
        });
    }

    // 轮播图
    private void initBanner() {

        BannerAdapter<LocalBanner> bannerAdapter = new BannerAdapter<LocalBanner>() {
            @Override
            protected void bind(ViewHolder holder, LocalBanner data) {
                Picasso.with(getApplicationContext()).load(data.getLocalRes()).into(holder.mImageView);
            }
        };
        layoutBanner.setAdapter(bannerAdapter);

        List<LocalBanner> localBanners = new ArrayList<>();
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        bannerAdapter.reset(localBanners);

    }

    private void initBanners() {

        BannerAdapter<LocalBanner> bannerAdapter = new BannerAdapter<LocalBanner>() {
            @Override
            protected void bind(ViewHolder holder, LocalBanner data) {
                Picasso.with(getApplicationContext()).load(data.getLocalRes()).into(holder.mImageView);
            }
        };
        layoutBanners.setAdapter(bannerAdapter);

        List<LocalBanner> localBanners = new ArrayList<>();
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        localBanners.add(new LocalBanner(R.drawable.banner));
        bannerAdapter.reset(localBanners);

    }

    //    初始化视图
    private void initView() {
        //viewPager适配器
        layoutBannerShequ.setAdapter(adapter);
        //viewPager监听->Button的切换
        layoutBannerShequ.addOnPageChangeListener(listener);
        //首次进入默认选中在线新闻btn
        homeXyd1.setSelected(true);
    }

    //    viewPager监听->Button的切换
    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //Button，UI改变
            homeXyd1.setSelected(position == 0);
            homeXyd2.setSelected(position == 1);
            homeXyd3.setSelected(position == 2);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //viewPager适配器
    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public int getCount() {
            //一共三页，写死3
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new HomeFragment();
                case 2:
                    return new HomeFragment();
                default:
                    throw new RuntimeException("未知错误");
            }
        }

    };

    @OnClick({R.id.home_xyd_2, R.id.home_xyd_1, R.id.home_xyd_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_xyd_1:
                //不要平滑效果，第二个参数传false
                layoutBannerShequ.setCurrentItem(0, false);
                break;
            case R.id.home_xyd_2:
                //不要平滑效果，第二个参数传false
                layoutBannerShequ.setCurrentItem(1, false);
                break;
            case R.id.home_xyd_3:
                //不要平滑效果，第二个参数传false
                layoutBannerShequ.setCurrentItem(2, false);
                break;
            default:
                throw new RuntimeException("未知错误");
        }
    }

    //专享推荐
    private void initViewQ() {

        List<Integer[]> list = new ArrayList<>();
        Integer[] imageIds = new Integer[]{
                R.drawable.zhuanxtj_1,
        };
        for (int i = 0; i < 8; i++) {
            list.add(imageIds);
        }
        FragmentZhuanAdapter fragmentZhuanAdapter = new FragmentZhuanAdapter(this, list);
        listZhuan.setAdapter(fragmentZhuanAdapter);

    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                }
            }
        }
    }
}