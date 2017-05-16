package com.example.administrator.a18master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.a18master.commons.AvatarLoadOptions;
import com.example.administrator.a18master.my.CachePreferences;
import com.example.administrator.a18master.my.ZHXxiActivity;
import com.example.administrator.a18master.my.mydc.MyDingCanActivity;
import com.example.administrator.a18master.my.mydz.MyDingZuoActivity;
import com.example.administrator.a18master.my.myjdyy.MyJiuDianActivity;
import com.example.administrator.a18master.my.myqg.MyQiangGou;
import com.example.administrator.a18master.my.myqgq.MyShopQuanActivity;
import com.example.administrator.a18master.my.myshop.MyShop;
import com.example.administrator.a18master.my.myyg.MyYunGouActivity;
import com.example.administrator.a18master.network.EasyShopApi;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.feicuiedu.apphx.model.HxUserManager;
import com.hyphenate.easeui.controller.EaseUI;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MyFragment extends Fragment {
    @BindView(R.id.my_black_text)
    TextView myBlackText;
    @BindView(R.id.my_text_dengl)
    TextView myTextDengl;
    @BindView(R.id.my_text_denglsj)
    TextView myTextDenglsj;
    @BindView(R.id.my_image_toux)
    ImageView myImageToux;

    private ActivityUtils activityUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_my, null);
        ButterKnife.bind(this, view);
        myBlackText.setAlpha(0.3f);
        activityUtils = new ActivityUtils(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //判断是否登录，显示昵称和头像
        if (CachePreferences.getUser().getName() == null) return;//未登录，跳出
        //刚注册还没有昵称和头像
        if (CachePreferences.getUser().getNick_name() == null) {
            myTextDengl.setText("请输入昵称");
        } else {
            myTextDengl.setText(CachePreferences.getUser().getNick_name());
            myTextDenglsj.setText(CachePreferences.getUser().getNick_name());
        }
        ImageLoader.getInstance()
                .displayImage(EasyShopApi.IMAGE_URL + CachePreferences.getUser().getHead_Image()
                        , myImageToux, AvatarLoadOptions.build());

    }

    @OnClick({R.id.my_image_toux,
            R.id.my_text_dengl,
            R.id.my_text_denglsj,
            R.id.imageView4,
            R.id.my_wdqg_image,
            R.id.my_wdgw_image,
            R.id.my_wddc_image,
            R.id.my_wddz_image,
            R.id.my_wdyg_image,
            R.id.my_wdjdyy_image,
            R.id.my_list_qgq,
            R.id.my_list_yhq,
            R.id.my_list_zc,
            R.id.my_list_hd,
            R.id.my_list_sc,
            R.id.my_list_yhmd,
            R.id.my_list_yy,
            R.id.my_list_shxx,
            R.id.my_list_qmjjr,
            R.id.my_list_jfdh,
            R.id.my_list_kd,
            R.id.my_tuichu})
    public void onClick(View view) {
        // 需要判断是否登录，从而决定跳转位置
        if (CachePreferences.getUser().getName() == null) {
            activityUtils.startActivity(LoginActivity.class);
            return;
        }
        switch (view.getId()) {

            case R.id.my_image_toux:
            case R.id.my_text_denglsj:
            case R.id.my_text_dengl:
            case R.id.imageView4:
                activityUtils.startActivity(ZHXxiActivity.class);
//                intent = new Intent(getContext(), ZHXxiActivity.class);
//                startActivity(intent);
                break;
            case R.id.my_wdqg_image:
                activityUtils.startActivity(MyQiangGou.class);
                break;
            case R.id.my_wdgw_image:
                activityUtils.startActivity(MyShop.class);
                break;
            case R.id.my_wddc_image:
                activityUtils.startActivity(MyDingCanActivity.class);
                break;
            case R.id.my_wddz_image:
                activityUtils.startActivity(MyDingZuoActivity.class);
                break;
            case R.id.my_wdyg_image:
                activityUtils.startActivity(MyYunGouActivity.class);
                break;
            case R.id.my_wdjdyy_image:
                activityUtils.startActivity(MyJiuDianActivity.class);
                break;
            case R.id.my_list_qgq:
                activityUtils.startActivity(MyShopQuanActivity.class);
                break;
            case R.id.my_list_yhq:
                break;
            case R.id.my_list_zc:
                break;
            case R.id.my_list_hd:
                break;
            case R.id.my_list_sc:
                break;
            case R.id.my_list_yhmd:
                break;
            case R.id.my_list_yy:
                break;
            case R.id.my_list_shxx:
                break;
            case R.id.my_list_qmjjr:
                break;
            case R.id.my_list_jfdh:
                break;
            case R.id.my_list_kd:
                break;
//
//            case R.id.my_relative_dl:
//                intent = new Intent(getContext(), ZHXxiActivity.class);
//                startActivity(intent);
//                break;
            case R.id.my_tuichu:
                //清空本地配置
                CachePreferences.clearAllData();
                //清除所有旧的Activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //退出环信相关
                HxUserManager.getInstance().asyncLogout();
                //登出关掉通知栏中的通知
                EaseUI.getInstance().getNotifier().reset();
        }
    }

}
