package com.example.administrator.a18master.my.myyg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.a18master.R;

/**
 * Created by Administrator on 2017/5/16.
 */

public class FragmentPublish extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_publish,null);
        return view;
    }

    public static FragmentPublish newInstance() {
        return new FragmentPublish();
    }
}
