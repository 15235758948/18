package com.example.administrator.a18master.my.myqg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.database.DatabaseUtilsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.a18master.R;
import com.example.administrator.a18master.robshop.DetailsActivity;
import com.example.administrator.a18master.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/15.
 */

public class FragmentDaiFu extends Fragment {
    private ActivityUtils activityUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daifu, null);
         activityUtils=new ActivityUtils(this);
        ButterKnife.bind(this, view);
        return view;
    }

    public static FragmentDaiFu newInstance() {
        return new FragmentDaiFu();
    }

    @OnClick({R.id.qianggou_daifu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qianggou_daifu:
                activityUtils.startActivity(DetailsActivity.class);
                break;
        }
    }
}
