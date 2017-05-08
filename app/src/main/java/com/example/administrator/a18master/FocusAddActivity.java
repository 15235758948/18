package com.example.administrator.a18master;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.a18master.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/5.
 */
public class FocusAddActivity extends Activity {
    private  ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focusadd);
        ButterKnife.bind(this);
        activityUtils= new ActivityUtils(this);

    }

    @OnClick({R.id.add_concern_fh,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_concern_fh:
               finish();

                break;

        }
    }
}
