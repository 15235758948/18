package com.example.administrator.a18master;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.popmenu.PopupMenuView;
import com.example.popmenu.PopupView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;
import static com.example.administrator.a18master.R.id.rbMore;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.rbChat)
    RadioButton rbChat;
    @BindView(R.id.rbAddress)
    RadioButton rbAddress;
    @BindView(R.id.rbFind)
    RadioButton rbFind;
    @BindView(R.id.rbMe)
    RadioButton rbMe;
    @BindView(R.id.tab_menu)
    RadioGroup tabMenu;

    private HomeFragment homeFragment;
    private ShopFragment shopFragment;
    private FocusFragment focusFragment;
    private MyFragment myFragment;
    private RadioGroup myTabRg;
    private PopupMenuView mCustomMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
//        默认不弹出小键盘，点击才弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        底部导航+Fragment切换
        init();
//底部更多
        initMore();
    }

    private void initMore() {
        mCustomMenuView = new PopupMenuView(this, R.layout.layout_custom_menu);
        mCustomMenuView.inflate(R.menu.menu_pop, new MenuBuilder(this));
    }

    public void init() {
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment)
                .commit();
        myTabRg = (RadioGroup) findViewById(R.id.tab_menu);
        myTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbChat:
                        homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.rbAddress:
                        if (shopFragment == null) {
                            shopFragment = new ShopFragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, shopFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.rbFind:
                        focusFragment = new FocusFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, focusFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
                    case R.id.rbMe:
                        myFragment = new MyFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, myFragment)
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                        break;
//                    case R.id.rbMore:
//                        rbMore.setOrientation(LinearLayout.VERTICAL);
//                        rbMore.show(R.menu.menu_pop);
                    default:
                        break;
                }

            }
        });
    }

//    private void showPopupMenu(int view) {
//        // View当前PopupMenu显示的相对View的位置
//        PopupMenu popupMenu = new PopupMenu(Context, view);
//        // menu布局
//        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
//        // menu的item点击事件
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        // PopupMenu关闭事件
//        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
//            @Override
//            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        popupMenu.show();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbMore:
//                mPopupMenuView.setOrientation(LinearLayout.VERTICAL);
                mCustomMenuView.setOrientation(LinearLayout.VERTICAL);
                mCustomMenuView.show(v);
            break;
        }}
}