package com.example.administrator.a18master.zhifu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.administrator.a18master.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ZhiFuActivity extends Activity {
    @BindView(R.id.text_shuliang)
    TextView text_shuliang;
    @BindView(R.id.leixing)
    TextView text_leixing;
    @BindView(R.id.text_zongjia)
    TextView text_zongjia;
    @BindView(R.id.text_danjia)
    TextView text_danjia;
    @BindView(R.id.text_youhui)
    TextView text_youhui;
    @BindView(R.id.text_zongjin)
    TextView text_zongjin;
    private int int_shuliang = 1;
    private int int_danjia ;
    private int int_youhui ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhifu_activity);
        ButterKnife.bind(this);
        initView();
        text_zongjia.setText((int_danjia*int_shuliang)-int_youhui+"");
        text_zongjin.setText((int_danjia*int_shuliang)-int_youhui+"");
    }

    private void initView() {
        if (text_danjia!=null){
            String dan = text_danjia.getText().toString();
            if (dan != null && dan.length() > 0) {
                int_danjia = Integer.parseInt(dan);
            }
        }
        if (text_youhui!=null){
            String you = text_youhui.getText().toString();
            if (you != null && you.length() > 0) {
                int_youhui = Integer.parseInt(you);
            }
        }

    }


    @OnClick({R.id.yunsuan_jia, R.id.yunsuan_jian, R.id.button3,R.id.zhifu_fh})
    public void onViewClicked(View view) {
        if (text_shuliang!=null){
            String shu = text_shuliang.getText().toString();
            if (shu != null && shu.length() > 0) {
                int_shuliang = Integer.parseInt(shu);
            }
        }
        switch (view.getId()) {
            case R.id.yunsuan_jia:
                text_shuliang.setText(int_shuliang +1 + "");
                int_shuliang++;
                break;
            case R.id.yunsuan_jian:
                        if (int_shuliang>1){
                            text_shuliang.setText(int_shuliang - 1 + "");
                          int_shuliang--;
                        }
                break;
            case R.id.button3:
                String zongjia = text_zongjia.getText().toString();
                String leixing = text_leixing.getText().toString();
                String danjia = text_danjia.getText().toString();
                String shuliang = text_shuliang.getText().toString();
                Intent intent = new Intent(this, ZhiFu2Activity.class);
                intent.putExtra("leixing", leixing);
                intent.putExtra("danjia", danjia);
                intent.putExtra("zongjia",zongjia);
                intent.putExtra("shuliang",shuliang);
                startActivity(intent);
                break;
            case R.id.zhifu_fh:
                finish();
                break;
        }
        text_zongjia.setText((int_danjia*int_shuliang)-int_youhui+"");
        text_zongjin.setText((int_danjia*int_shuliang)-int_youhui+"");
    }
}
