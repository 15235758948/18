package com.example.administrator.a18master.my.myqgq;

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

public class FragmentUse extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_use,null);
        return view;
    }

    public static FragmentUse newInstance() {
        return new FragmentUse();
    }
}
