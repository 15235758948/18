package com.example.administrator.a18master.mvplogin.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.administrator.a18master.mvplogin.Base.App;
import com.example.administrator.a18master.mvplogin.Config.Configs;

public class AppContext {
    public static void LogException(String msg) {
        if (App.isDebug) {
            Log.e(Configs.LOG_TAG, msg);
        }
    }

    public static void Log(String msg) {
        if (App.isDebug) {
            Log.i(Configs.LOG_TAG, msg);
        }
    }

    //================================= SharedPreference相关 ========================================
    private static SharedPreferences getSharedPreferences() {
        return App.getAppContext().getSharedPreferences(Configs.SP_NAME, Context.MODE_PRIVATE);
    }

    public static void saveLoginToken(String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(Configs.SP_TOKEN, value);
        editor.apply();
    }

    public static String getLoginToken() {
        return getSharedPreferences().getString(Configs.SP_TOKEN, null);
    }


    public static void remove(String key) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }
    //================================= SharedPreference相关 ========================================
}
