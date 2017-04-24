package com.example.administrator.a18master;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a18master.base.banner.BannerAdapter;
import com.example.administrator.a18master.base.banner.BannerLayout;
import com.example.administrator.a18master.base.banner.LocalBanner;
import com.example.administrator.a18master.home.FragmentZhuanAdapter;
import com.example.administrator.a18master.home.GridViewAdapter;
import com.example.administrator.a18master.home.Model;
import com.example.administrator.a18master.home.ViewPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, null);
        ButterKnife.bind(this, view);
//               轮播
        initBanner();
        initBanners();

//   专享推荐
        initViewQ();
//        计时器
        startRun();
//        社区初始化数据源
        //初始化数据源
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
        return view;

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
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
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
}
