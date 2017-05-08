package com.example.administrator.a18master;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/8.
 */
public class SignInActivity extends Activity {
    //    @BindView(R.id.sign_user)
//    TextView signUser;
//    @BindView(R.id.sign_jifen)
//    TextView signJifen;
    @BindView(R.id.sign_day)
    TextView signDay;
    @BindView(R.id.button_sign)
    Button buttonSign;
    //定义共享优先数据及基础字段
    private String MY_RMBCost = "MY_RMBCost";

    private String TodayTime = "TodayTime";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        initSign();

    }

    private void initSign() {
//        final Button bt_jz = (Button) findViewById(R.id.bt_jz);
        //读取共享数据
        SharedPreferences my_rmb_data = getSharedPreferences(MY_RMBCost, 0);

        Time t = new Time();
        t.setToNow();
        int lastmonth = t.month + 1;
        final String str = t.year + "年" + lastmonth + "月" + t.monthDay + "日";


        final String nowtime = my_rmb_data.getString(TodayTime, "").toString();

        if (nowtime.equals(str) == true) {
            signDay.setText("日期：" + nowtime + "已签到！");
//            buttonSign.setBackgroundResource(R.drawable.aa);
        } else {
            signDay.setText("日期：" + str);
//            buttonSign.setBackgroundResource(R.drawable.b);
        }

        //按钮操作部分

        //签到功能
        buttonSign.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SharedPreferences my_rmb_data = getSharedPreferences(MY_RMBCost, 0);
                if (my_rmb_data.getString(TodayTime, "").toString().equals(str) == true) {
                    Toast.makeText(SignInActivity.this, "今日已签到！", Toast.LENGTH_SHORT).show();
                } else {
                    my_rmb_data.edit()
                            .putString(TodayTime, str)
                            .commit();
                    signDay.setText("日期：" + str + "已签到！");
//                    buttonSign.setBackgroundResource(R.drawable.aa);
                    Toast.makeText(SignInActivity.this, "签到成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @OnClick({R.id.sign_user, R.id.sign_jifen, R.id.sign_day, R.id.button_sign})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.sign_user:
//                break;
//            case R.id.sign_jifen:
//                break;
//            case R.id.sign_day:
//                break;
//            case R.id.button_sign:
//                break;
//        }
//    }
}
