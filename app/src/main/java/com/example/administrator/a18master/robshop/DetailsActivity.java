package com.example.administrator.a18master.robshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.a18master.R;
import com.example.administrator.a18master.baidumap.LocationDemo;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.example.administrator.a18master.zhifu.ZhiFuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/11.
 */

public class DetailsActivity extends AppCompatActivity {
    private Activity basecontext;
    @BindView(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
         activityUtils= new ActivityUtils(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        basecontext = this;
        ButterKnife.bind(this);
        View headview = LayoutInflater.from(basecontext).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(basecontext).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(basecontext).inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headview);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
        TextView textView = (TextView) scrollView.getPullRootView().findViewById(R.id.details_yuanjia);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        Button button = (Button) scrollView.getPullRootView().findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsActivity.this, ZhiFuActivity.class));
            }
        });

    }

    @OnClick({R.id.qianggou_fh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qianggou_fh:
                finish();
                break;
//            case R.id.qianggou_daozheli:
//                activityUtils.startActivity(LocationDemo.class);
//                break;


        }
    }

}
