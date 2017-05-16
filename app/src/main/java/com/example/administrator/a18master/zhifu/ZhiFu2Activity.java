package com.example.administrator.a18master.zhifu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a18master.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;



/**
 * Created by Administrator on 2017/5/12.
 */
public class ZhiFu2Activity extends Activity {

    @BindView(R.id.ding_zonglei)
    TextView dingZonglei;
    @BindView(R.id.dingshu_danjia)
    TextView dingshuDanjia;
    @BindView(R.id.dingshu_shuliang)
    TextView dingshuShuliang;
    @BindView(R.id.dingshu_zongjia)
    TextView dingshuZongjia;
    @BindView(R.id.smoothCheckBox1)
    SmoothCheckBox smoothCheckBox1;
    @BindView(R.id.smoothCheckBox2)
    SmoothCheckBox smoothCheckBox2;
    @BindView(R.id.smoothCheckBox3)
    SmoothCheckBox smoothCheckBox3;
    @BindView(R.id.smoothCheckBox4)
    SmoothCheckBox smoothCheckBox4;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhifu2_activity);
        bind = ButterKnife.bind(this);
        final Intent intents = getIntent();
        dingZonglei.setText(intents.getStringExtra("leixing"));
        dingshuDanjia.setText(intents.getStringExtra("danjia"));
        dingshuShuliang.setText(intents.getStringExtra("shuliang"));
        dingshuZongjia.setText(intents.getStringExtra("zongjia"));
        String zongLei = dingZonglei.getText().toString();
        String danJia = dingshuDanjia.getText().toString();
        String shuLiang = dingshuShuliang.getText().toString();
        String zongJia = dingshuZongjia.getText().toString();
        smoothCheckBox1.setClickable(false);
        smoothCheckBox2.setClickable(false);
        smoothCheckBox3.setClickable(false);
        smoothCheckBox4.setClickable(false);
    }

    @OnClick({R.id.relat_zhifubao, R.id.relat_yue, R.id.relat_weixin, R.id.relat_hdfkuan,R.
             id.zhifu2_fh,R.id.zfdd_querzf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relat_zhifubao:
                smoothCheckBox2.setChecked(false);
                smoothCheckBox3.setChecked(false);
                smoothCheckBox4.setChecked(false);
                smoothCheckBox2.setVisibility(View.INVISIBLE);
                smoothCheckBox3.setVisibility(View.INVISIBLE);
                smoothCheckBox4.setVisibility(View.INVISIBLE);
                if (smoothCheckBox1.isChecked()) {
                    smoothCheckBox1.setChecked(false);
                    smoothCheckBox1.setVisibility(View.INVISIBLE);
                } else {
                    smoothCheckBox1.setChecked(true);
                    smoothCheckBox1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.relat_yue:
                smoothCheckBox1.setChecked(false);
                smoothCheckBox3.setChecked(false);
                smoothCheckBox4.setChecked(false);
                smoothCheckBox1.setVisibility(View.INVISIBLE);
                smoothCheckBox3.setVisibility(View.INVISIBLE);
                smoothCheckBox4.setVisibility(View.INVISIBLE);
                if (smoothCheckBox2.isChecked()) {
                    smoothCheckBox2.setChecked(false);
                    smoothCheckBox2.setVisibility(View.INVISIBLE);
                } else {
                    smoothCheckBox2.setChecked(true);
                    smoothCheckBox2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.relat_weixin:
                smoothCheckBox1.setChecked(false);
                smoothCheckBox2.setChecked(false);
                smoothCheckBox4.setChecked(false);
                smoothCheckBox1.setVisibility(View.INVISIBLE);
                smoothCheckBox2.setVisibility(View.INVISIBLE);
                smoothCheckBox4.setVisibility(View.INVISIBLE);
                if (smoothCheckBox3.isChecked()) {
                    smoothCheckBox3.setChecked(false);
                    smoothCheckBox3.setVisibility(View.INVISIBLE);
                } else {
                    smoothCheckBox3.setChecked(true);
                    smoothCheckBox3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.relat_hdfkuan:
                smoothCheckBox1.setChecked(false);
                smoothCheckBox2.setChecked(false);
                smoothCheckBox3.setChecked(false);
                smoothCheckBox1.setVisibility(View.INVISIBLE);
                smoothCheckBox2.setVisibility(View.INVISIBLE);
                smoothCheckBox3.setVisibility(View.INVISIBLE);
                if (smoothCheckBox4.isChecked()) {
                    smoothCheckBox4.setChecked(false);
                    smoothCheckBox4.setVisibility(View.INVISIBLE);
                } else {
                    smoothCheckBox4.setChecked(true);
                    smoothCheckBox4.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.zhifu2_fh:
                finish();
                break;
            case R.id.zfdd_querzf:
                if (smoothCheckBox1.isChecked()==true){
                    Toast.makeText(ZhiFu2Activity.this, "使用支付宝支付", Toast.LENGTH_SHORT).show();
                }else if (smoothCheckBox2.isChecked()==true){
                    Toast.makeText(ZhiFu2Activity.this, "使用余额支付", Toast.LENGTH_SHORT).show();
                }else if (smoothCheckBox3.isChecked()==true){
                    Toast.makeText(ZhiFu2Activity.this, "使用微信支付", Toast.LENGTH_SHORT).show();
                }else if (smoothCheckBox4.isChecked()==true){
                    Toast.makeText(ZhiFu2Activity.this, "使用货到付款支付", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
