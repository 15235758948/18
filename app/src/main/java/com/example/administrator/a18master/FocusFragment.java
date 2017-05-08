package com.example.administrator.a18master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.a18master.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/18.
 */

public class FocusFragment extends Fragment {
     private  ActivityUtils activityUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_focus, null);
        ButterKnife.bind(this, view);
         activityUtils= new ActivityUtils(this);
        return view;
    }

    @OnClick({R.id.focus_add})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.my_concern_fh:
//                activityUtils.startActivity(MainActivity.class);
//                break;
            case R.id.focus_add:
                activityUtils.startActivity(FocusAddActivity.class);
                break;
        }
    }
}
