package com.example.administrator.a18master;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.a18master.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/5/8.
 */
public class SignInActivity extends Activity {

    @BindView(R.id.sign_day)
    TextView signDay;
    @BindView(R.id.button_sign)
    Button buttonSign;
    //    //定义共享优先数据及基础字段
    private String signDays = "signDay";
    private String TodayTime = "TodayTime";
    private ActivityUtils activityUtils;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        activityUtils=new ActivityUtils(this);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        initSign();

    }

    private void initSign() {
//        final Button bt_jz = (Button) findViewById(R.id.bt_jz);
        //读取共享数据
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        SharedPreferences my_rmb_data = getSharedPreferences(signDays, 0);

        Time t = new Time();
        t.setToNow();
        int lastmonth = t.month + 1;
        final String str = t.year + "年" + lastmonth + "月" + t.monthDay + "日";
        final String nowtime = my_rmb_data.getString(TodayTime, "").toString();
        int i = 0;

        if (nowtime.equals(str) == true) {
            if (i > -1) {
                i = i + 1;
                signDay.setText("您已连续签到" + i + "天");

            }
//            signDay.setText("日期：" + nowtime + "已签到！");
//            buttonSign.setBackgroundResource(R.drawable.aa);
        } else {
            signDay.setText("今日未签到");
//            buttonSign.setBackgroundResource(R.drawable.b);
        }
        i = i + 1;
        String signDay1 = "您已连续签到" + i + "天";
        editor.putInt("i", i);
        editor.putString("signDay", signDay1);
        editor.commit();

        //按钮操作部分

        //签到功能
        buttonSign.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences my_rmb_data = getSharedPreferences(signDays, 0);
                if (my_rmb_data.getString(TodayTime, "").toString().equals(str) == true) {
                    Toast.makeText(SignInActivity.this, "今日已签到！", Toast.LENGTH_SHORT).show();
                } else {
                    my_rmb_data.edit()
                            .putString(TodayTime, str)
                            .commit();
                    int i = preferences.getInt("i", 1);
                    i = i + 1;
                    String signDay1 = preferences.getString("signDay", "您已连续签到" + i + "天");
                    signDay.setText(signDay1);

//                    signDay.setText("日期：" + str + "已签到！");
//                    buttonSign.setBackgroundResource(R.drawable.aa);
                    Toast.makeText(SignInActivity.this, "签到成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.sign_user, R.id.sign_jifen, R.id.sign_day, R.id.button_sign,R.id.sign_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_back:
               finish();
                break;

//            case R.id.sign_user:
//                break;
//            case R.id.sign_jifen:
//                break;
//            case R.id.sign_day:
//                break;
//            case R.id.button_sign:
//                break;
        }
    }
}
