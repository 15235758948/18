package com.example.administrator.a18master.my;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.administrator.a18master.MainActivity;
import com.example.administrator.a18master.R;
import com.example.administrator.a18master.commons.AvatarLoadOptions;
import com.example.administrator.a18master.mvplogin.Login.LoginMvpActivity;
import com.example.administrator.a18master.network.EasyShopApi;
import com.example.administrator.a18master.register.ProgressDialogFragment;
import com.example.administrator.a18master.utils.ActivityUtils;
import com.feicuiedu.apphx.model.HxUserManager;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hyphenate.easeui.controller.EaseUI;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ZHXxiActivity extends MvpActivity<PersonView, PersonPersenter> implements PersonView {
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.iv_user_head)
    ImageView ivUserHead;
    private ActivityUtils activityUtils;
    private ProgressDialogFragment progressDialogFragment;
    private List<ItemShow> list = new ArrayList<>();
    private PersonAdapter adapter;
    private PicWindow picWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        activityUtils = new ActivityUtils(this);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("个人信息");
        adapter = new PersonAdapter(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);

        //获取用户头像
        updataAvatar(CachePreferences.getUser().getHead_Image());
//        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < pNumber.length(); i++) {
//                char c = pNumber.charAt(i);
//                if (i >= 3 && i <= 6) {
//                    sb.append('*');
//                } else {
//                    sb.append(c);
//                }
//            }
//
//            zhanghxxSjh.setText(sb.toString());
//        }
    }

    @NonNull
    @Override
    public PersonPersenter createPresenter() {
        return new PersonPersenter();
    }

    //方便修改完昵称，回来更改数据
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        init();//数据初始化
        adapter.notifyDataSetChanged();
    }

    //数据初始化
    private void init() {

        User user = CachePreferences.getUser();
        list.add(new ItemShow(getResources().getString(R.string.username), user.getName()));
        list.add(new ItemShow(getResources().getString(R.string.nickname), user.getNick_name()));
        list.add(new ItemShow(getResources().getString(R.string.hx_id), user.getHx_Id()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                //用户名
                case 0:
                    activityUtils.showToast(getResources().getString(R.string.username_update));
                    break;
                //昵称
                case 1:
                    activityUtils.startActivity(NickNameActivity.class);
                    break;
                //环信ID
                case 2:
                    activityUtils.showToast(getResources().getString(R.string.id_update));
                    break;
            }
        }
    };

    @OnClick({R.id.btn_login_out, R.id.iv_user_head,R.id.xingxi_linear})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击头像
            case R.id.iv_user_head:
                //头像来源选择（相册，拍照）
                //如果为空，创建实例（实现监听）
                if (picWindow == null) {
                    picWindow = new PicWindow(this, listener);
                }
                //如果已经显示，则关闭
                if (picWindow.isShowing()) {
                    picWindow.dismiss();
                    return;
                }
                picWindow.show();//显示来源选择框
                break;
            //点击退出登录
            case R.id.btn_login_out:
                //清空本地配置
                CachePreferences.clearAllData();
                //清除所有旧的Activity
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                //退出环信相关
                HxUserManager.getInstance().asyncLogout();
                //登出关掉通知栏中的通知
                EaseUI.getInstance().getNotifier().reset();
            case R.id.xingxi_linear:
                Intent intent1=new Intent(this, LoginMvpActivity.class);
                startActivity(intent1);
                break;
        }
    }

    //图片选择弹窗的自定义监听
    private PicWindow.Listener listener = new PicWindow.Listener() {
        @Override
        public void toGallery() {
            //从相册中选择
            //清空裁剪的缓存
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCropFromGalleryIntent(cropHandler.getCropParams());
            startActivityForResult(intent, CropHelper.REQUEST_CROP);
        }

        @Override
        public void toCamera() {
            //从相机中选择
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
            Intent intent = CropHelper.buildCaptureIntent(cropHandler.getCropParams().uri);
            startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
        }
    };

    //图片裁剪的handler
    private CropHandler cropHandler = new CropHandler() {
        @Override
        public void onPhotoCropped(Uri uri) {
            //通过uri拿到图片文件
            File file = new File(uri.getPath());
            //业务类上传头像
            presenter.updataAvatar(file);
        }

        @Override
        public void onCropCancel() {
        }

        @Override
        public void onCropFailed(String message) {
        }

        @Override
        public CropParams getCropParams() {
            //自定义裁剪大小参数
            CropParams cropParams = new CropParams();
            cropParams.aspectX = 200;
            cropParams.aspectY = 200;
            return cropParams;
        }

        @Override
        public Activity getContext() {
            return ZHXxiActivity.this;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //帮助我们去处理结果（裁剪完的图像）
        CropHelper.handleResult(cropHandler, requestCode, resultCode, data);
    }

    // ######################    视图接口相关    #####################
    @Override
    public void showPrb() {
        if (progressDialogFragment == null) progressDialogFragment = new ProgressDialogFragment();
        if (progressDialogFragment.isVisible()) return;
        progressDialogFragment.show(getSupportFragmentManager(), "progress_dialog_fragment");
    }

    @Override
    public void hidePrb() {
        progressDialogFragment.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void updataAvatar(String url) {
        //头像加载操作
        ImageLoader.getInstance()
                //参数，“头像路径（服务器）”，“头像显示的控件”,“加载选项”
                .displayImage(EasyShopApi.IMAGE_URL + url, ivUserHead,
                        AvatarLoadOptions.build());
    }
}