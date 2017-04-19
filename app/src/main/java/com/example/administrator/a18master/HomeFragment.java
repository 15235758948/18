package com.example.administrator.a18master;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.administrator.a18master.home.FragmentZhuanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/18.
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.list_zhuan)
    GridView listZhuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, null);
        ButterKnife.bind(this, view);
        initViewQ();
        return view;
    }

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
}
