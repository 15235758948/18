package com.example.administrator.a18master;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a18master.baidumap.LocationDemo;
import com.example.administrator.a18master.base.banner.BannerAdapter;
import com.example.administrator.a18master.base.banner.BannerLayout;
import com.example.administrator.a18master.base.banner.LocalBanner;
import com.example.administrator.a18master.home.FragmentZhuanAdapter;
import com.example.administrator.a18master.home.GridViewAdapter;
import com.example.administrator.a18master.home.Model;
import com.example.administrator.a18master.home.ViewPagerAdapter;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/18.
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.layout_banner)
    BannerLayout layoutBanner;
    @BindView(R.id.layout_banners)
    BannerLayout layoutBanners;
    @BindView(R.id.list_zhuan)
    GridView listZhuan;
    //   计时器
    @BindView(R.id.hours_tv)
    TextView hoursTv;
    @BindView(R.id.minutes_tv)
    TextView minutesTv;
    @BindView(R.id.seconds_tv)
    TextView secondsTv;
    @BindView(R.id.countdown_layout)
    RelativeLayout countDown;
    @BindView(R.id.viewpager)
    ViewPager mPager;
    @BindView(R.id.ll_dot)
    LinearLayout mLlDot;
    @BindView(R.id.home_xsqg_zhuti1)
    LinearLayout homeXsqgZhuti1;
    @BindView(R.id.home_xsqg_zhuti2)
    LinearLayout homeXsqgZhuti2;
    @BindView(R.id.home_xsqg_zhuti3)
    LinearLayout homeXsqgZhuti3;
    @BindView(R.id.home_xsqg_zhuti4)
    LinearLayout homeXsqgZhuti4;
    @BindView(R.id.home_xsqg_zhuti5)
    LinearLayout homeXsqgZhuti5;
    @BindView(R.id.home_xsqg_zhuti6)
    LinearLayout homeXsqgZhuti6;
    @BindView(R.id.home_xsqg_zhuti7)
    LinearLayout homeXsqgZhuti7;
    @BindView(R.id.home_xsqg_zhuti8)
    LinearLayout homeXsqgZhuti8;
    @BindView(R.id.home_xsqg_yuan1)
    TextView homeXsqgYuan1;
    @BindView(R.id.home_xsqg_yuan2)
    TextView homeXsqgYuan2;
    @BindView(R.id.home_xsqg_yuan3)
    TextView homeXsqgYuan3;
    @BindView(R.id.home_xsqg_yuan4)
    TextView homeXsqgYuan4;
    @BindView(R.id.home_xsqg_yuan5)
    TextView homeXsqgYuan5;
    @BindView(R.id.home_xsqg_yuan6)
    TextView homeXsqgYuan6;
    @BindView(R.id.home_xsqg_yuan7)
    TextView homeXsqgYuan7;
    @BindView(R.id.home_xsqg_yuan8)
    TextView homeXsqgYuan8;
    @BindView(R.id.toolbar_editText)
    EditText toolbarEditText;

    //    计时器
    private long mHour = 23;
    private long mMin = 59;
    private long mSecond = 60;// 天 ,小时,分钟,秒
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
                if (mHour == 0 && mMin == 0 && mSecond == 0) {
                    countDown.setVisibility(View.GONE);
                }
            }
        }
    };
    private String[] titles = {"抢购", "外卖", "农家乐", "购物", "众筹", "酒店", "商家服务", "部落", "订座", "贴吧",
            "约会", "优惠券", "逛街", "1元云购", "微店", "生活信息", "家政", "积分商城", "全民推广", "活动", "榜单", "附近工作"};
    private List<View> mPagerList;
    private List<Model> mDatas;
    private LayoutInflater inflater1;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 10;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;
    private ActivityUtils activityUtils;
    // Fragment管理对象
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction ft;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, null);
        ButterKnife.bind(this, view);
        activityUtils = new ActivityUtils(this);
//               轮播
        initBanner();
        initBanners();

//        专享推荐
        initViewQ();
//        计时器
        startRun();
//        社区初始化数据源
        initDatas();
        inflater1 = LayoutInflater.from(getContext());
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            gridView.setAdapter(new GridViewAdapter(getContext(), mDatas, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    Toast.makeText(getContext(), mDatas.get(pos).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //设置适配器
        mPager.setAdapter(new ViewPagerAdapter(mPagerList));
        //设置圆点
        setOvalLayout();
        initDawlayout();
//        给搜索框设置宽度
        WindowManager wm = this.getActivity().getWindowManager();//获取屏幕宽高
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams para = toolbarEditText.getLayoutParams();
        para.width = (width1 / 2 * 1);//修改宽度
        para.height = height1;//修改高度
        toolbarEditText.setLayoutParams(para); //设置修改后的布局。
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getFragmentManager();
    }

    //获取屏幕的高宽
    private void initDawlayout() {
        WindowManager wm = this.getActivity().getWindowManager();//获取屏幕宽高
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams para1 = homeXsqgZhuti1.getLayoutParams();//获取drawerlayout的布局
        para1.width = (width1 - 10) / 3;//修改宽度
        para1.height = height1;//修改高度
        homeXsqgZhuti1.setLayoutParams(para1); //设置修改后的布局。
        homeXsqgYuan1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ViewGroup.LayoutParams para2 = homeXsqgZhuti2.getLayoutParams();//获取drawerlayout的布局
        para2.width = (width1 - 10) / 3;//修改宽度
        para2.height = height1;//修改高度
        homeXsqgZhuti2.setLayoutParams(para2); //设置修改后的布局。
        homeXsqgYuan2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ViewGroup.LayoutParams para3 = homeXsqgZhuti3.getLayoutParams();//获取drawerlayout的布局
        para3.width = (width1 - 10) / 3;//修改宽度
        para3.height = height1;//修改高度
        homeXsqgZhuti3.setLayoutParams(para3); //设置修改后的布局。
        homeXsqgYuan3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ViewGroup.LayoutParams para4 = homeXsqgZhuti4.getLayoutParams();//获取drawerlayout的布局
        para4.width = (width1 - 10) / 3;//修改宽度
        para4.height = height1;//修改高度
        homeXsqgZhuti4.setLayoutParams(para4); //设置修改后的布局。
        homeXsqgYuan4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ViewGroup.LayoutParams para5 = homeXsqgZhuti5.getLayoutParams();//获取drawerlayout的布局
        para5.width = (width1 - 10) / 3;//修改宽度
        para5.height = height1;//修改高度
        homeXsqgZhuti5.setLayoutParams(para5); //设置修改后的布局。
        homeXsqgYuan5.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ViewGroup.LayoutParams para6 = homeXsqgZhuti6.getLayoutParams();//获取drawerlayout的布局
        para6.width = (width1 - 10) / 3;//修改宽度
        para6.height = height1;//修改高度
        homeXsqgZhuti6.setLayoutParams(para6); //设置修改后的布局。
        homeXsqgYuan6.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ViewGroup.LayoutParams para7 = homeXsqgZhuti7.getLayoutParams();//获取drawerlayout的布局
        para7.width = (width1 - 10) / 3;//修改宽度
        para7.height = height1;//修改高度
        homeXsqgZhuti7.setLayoutParams(para7); //设置修改后的布局。
        homeXsqgYuan7.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ViewGroup.LayoutParams para8 = homeXsqgZhuti8.getLayoutParams();//获取drawerlayout的布局
        para8.width = (width1 - 10) / 3;//修改宽度
        para8.height = height1;//修改高度
        homeXsqgZhuti8.setLayoutParams(para8); //设置修改后的布局。
        homeXsqgYuan8.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * 初始化数据源
     */
    private void initDatas() {
        mDatas = new ArrayList<Model>();
        for (int i = 1; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("flco" + i, "mipmap", getResources().getResourcePackageName(getId()));
            mDatas.add(new Model(titles[i], imageId));
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater1.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_normal);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    //     轮播图
    private void initBanner() {

        BannerAdapter<LocalBanner> bannerAdapter = new BannerAdapter<LocalBanner>() {
            @Override
            protected void bind(ViewHolder holder, LocalBanner data) {
                Picasso.with(getActivity()).load(data.getLocalRes()).into(holder.mImageView);
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
                Picasso.with(getActivity()).load(data.getLocalRes()).into(holder.mImageView);
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


    //专享推荐
    private void initViewQ() {

        List<Integer[]> list = new ArrayList<>();
        Integer[] imageIds = new Integer[]{
                R.drawable.zhuanxtj_1,
        };
        for (int i = 0; i < 8; i++) {
            list.add(imageIds);
        }
        FragmentZhuanAdapter fragmentZhuanAdapter = new FragmentZhuanAdapter(getActivity(), list);
        listZhuan.setAdapter(fragmentZhuanAdapter);

    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
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

    @OnClick({R.id.toolbar_image, R.id.toolbar_qd_text,R.id.toolbar_city_text,R.id.countdown_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_image:
            case R.id.toolbar_qd_text:
                activityUtils.startActivity(SignInActivity.class);
                break;
            case R.id.toolbar_city_text:
                activityUtils.startActivity(LocationDemo.class);
                break;
            case R.id.countdown_layout:
                ShopFragment myJDEditFragment = new ShopFragment();
                ft = manager.beginTransaction();
                //当前的fragment会被myJDEditFragment替换
                ft.replace(R.id.main_content, myJDEditFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

        }
    }

}
