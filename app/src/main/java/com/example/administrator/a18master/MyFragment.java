package com.example.administrator.a18master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.a18master.my.ZHXxiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MyFragment extends Fragment {
    @BindView(R.id.my_black_text)
    TextView myBlackText;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_my, null);
        ButterKnife.bind(this, view);
        myBlackText.setAlpha(0.3f);
        return view;
    }

    @OnClick({R.id.my_image_toux, R.id.my_relative_dl})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.my_image_toux:
                intent = new Intent(getContext(), ZHXxiActivity.class);
                startActivity(intent);
                break;
            
            case R.id.my_relative_dl:
                intent = new Intent(getContext(), ZHXxiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
