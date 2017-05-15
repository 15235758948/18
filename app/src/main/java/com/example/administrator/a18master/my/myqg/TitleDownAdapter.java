package com.example.administrator.a18master.my.myqg;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public class TitleDownAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;
    /**
     * 构造方法
     * @param manager
     * @param fragments
     */
    public TitleDownAdapter(FragmentManager manager, List<Fragment> fragments, List<String> titles){
        super(manager);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public int getCount() {
        if (fragments!=null){
            return fragments.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments!=null){
            return fragments.get(position);
        }
        return null;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (titles!=null){
            return titles.get(position);
        }
        return "";
    }

}
