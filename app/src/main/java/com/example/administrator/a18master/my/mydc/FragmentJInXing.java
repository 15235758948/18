package com.example.administrator.a18master.my.mydc;

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

public class FragmentJInXing extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_jinxing,null);

        return view;
    }

    public static FragmentJInXing newInstance() {
        return new FragmentJInXing();
    }
}
