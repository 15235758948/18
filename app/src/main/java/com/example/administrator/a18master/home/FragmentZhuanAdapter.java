package com.example.administrator.a18master.home;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.a18master.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class FragmentZhuanAdapter extends BaseAdapter {
    private List<Integer[]> list=new ArrayList<>();
    private Context context;
    public FragmentZhuanAdapter(FragmentActivity activity, List<Integer[]> list) {
        this.context=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder =null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_zhuanxtj_item,null);
            holder.im = (ImageView) convertView.findViewById(R.id.list_image);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.im.setImageResource(R.drawable.zhuanxtj_1);
        return convertView;
    }
    class ViewHolder{
        ImageView im;
    }
}
