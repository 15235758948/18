package com.example.administrator.a18master.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.a18master.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ZHXxiActivity extends AppCompatActivity {
    @BindView(R.id.zhanghxx_sjh)
    TextView zhanghxxSjh;
    @BindView(R.id.zhxx_back)
    ImageView zhxxBack;
    private String pNumber = "13921564654";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhxxi_activity);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

            zhanghxxSjh.setText(sb.toString());
        }
    }

    @OnClick(R.id.zhxx_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.zhxx_back:
                finish();
                break;
        }
    }
}
