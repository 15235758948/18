package com.example.administrator.a18master.mvplogin.Utils;

import android.widget.Toast;

import com.example.administrator.a18master.mvplogin.Base.App;
public class ToastUtils {

    public static void showToast(String msg) {
        Toast.makeText(App.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
