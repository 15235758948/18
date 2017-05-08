package com.example.administrator.a18master;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/18.
 */

public class ShopFragment extends Fragment {
    @BindView(R.id.sseditText)
    EditText edittext;
    @BindView(R.id.supplier_list_title_tv)
    TextView mSupplierListTitleTv;
    @BindView(R.id.supplier_list_product_tv)
    TextView mProductTv;
    @BindView(R.id.jiantou1)
    ImageView iv1;
    @BindView(R.id.supplier_list_product)
    LinearLayout mProduct;
    @BindView(R.id.supplier_list_sort_tv)
    TextView mSortTv;
    @BindView(R.id.jiantou2)
    ImageView iv2;
    @BindView(R.id.supplier_list_sort)
    LinearLayout mSort;
    @BindView(R.id.supplier_list_activity_tv)
    TextView mCategoryTv;
    @BindView(R.id.jiantou3)
    ImageView iv3;
    @BindView(R.id.supplier_list_activity)
    LinearLayout mCategory;
    @BindView(R.id.supplier_list_activity_tvjl)
    TextView supplier_list_activity_tvjl;
    @BindView(R.id.jiantou4)
    ImageView iv4;
    @BindView(R.id.supplier_list_activity_jl)
    LinearLayout supplier_list_activity_jl;
    private ArrayList<Map<String, String>> mMenuData1;
    private ArrayList<Map<String, String>> mMenuData2;
    private ArrayList<Map<String, String>> mMenuData3;
    private ArrayList<Map<String, String>> mMenuData4;

    private ListView mPopListview;
    private PopupWindow popupMenu;
    private SimpleAdapter mMenuAdapter1;
    private SimpleAdapter mMenuAdapter2;
    private SimpleAdapter mMenuAdapter3;
    private SimpleAdapter mMenuAdapter4;
    private int menuIndex = 0;

    String titles1 = "全部分类";
    String titles2 = "地区";
    String titles3 = "商圈";
    String titles4 = "离我最近";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_shop, null);
        initData();
        initPopMenu();
        ButterKnife.bind(this, view);

        WindowManager wm = this.getActivity().getWindowManager();//获取屏幕宽高
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams para = edittext.getLayoutParams();
        para.width = (width1 / 2 * 1);//修改宽度
        para.height = height1;//修改高度
        edittext.setLayoutParams(para); //设置修改后的布局。

        mProductTv.setText(titles1);
        mSortTv.setText(titles2);
        mCategoryTv.setText(titles3);
        supplier_list_activity_tvjl.setText(titles4);
        return view;
    }


    /***
     * 初始化popupwindow数据
     */
    private void initData() {
        mMenuData1 = new ArrayList<>();
        String[] menuStr1 = new String[]{"全部", "美食", "休闲娱乐", "酒店", "购物",
                "生活服务", "丽人", "亲子", "结婚", "水果", "旅游", "电影/在线选座"};
        Map<String, String> map1;
        for (int i = 0, len = menuStr1.length; i < len; ++i) {
            map1 = new HashMap<>();
            map1.put("name", menuStr1[i]);
            mMenuData1.add(map1);
        }

        mMenuData2 = new ArrayList<>();
        String[] menuStr2 = new String[]{"全部区域", "东城区", "西城区", "朝阳区"
                , "石景山区", "海淀区", "门头沟区", "房山区", "通州区", "顺义区"
                , "昌平区", "大兴区", "怀桑区"};
        Map<String, String> map2;
        for (int i = 0, len = menuStr2.length; i < len; ++i) {
            map2 = new HashMap<>();
            map2.put("name", menuStr2[i]);
            mMenuData2.add(map2);
        }

        mMenuData3 = new ArrayList<>();
        String[] menuStr3 = new String[]{"全部商圈", "东商圈", "西商圈",
                "南商圈", "北商圈"};
        Map<String, String> map3;
        for (int i = 0, len = menuStr3.length; i < len; ++i) {
            map3 = new HashMap<>();
            map3.put("name", menuStr3[i]);
            mMenuData3.add(map3);
        }

        mMenuData4 = new ArrayList<>();
        String[] menuStr4 = new String[]{"距离优先", "推荐排序", "热门排序"};
        Map<String, String> map4;
        for (int i = 0, len = menuStr4.length; i < len; ++i) {
            map4 = new HashMap<>();
            map4.put("name", menuStr4[i]);
            mMenuData4.add(map4);
        }
    }

    /***
     * 初始化popupwindow
     */
    private void initPopMenu() {
        View popView = LayoutInflater.from(getActivity()).inflate(R.layout.popwin_supplier_list, null);
        mPopListview = (ListView) popView.findViewById(R.id.popwin_supplier_list_lv);
        popupMenu = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT);
        popupMenu.setOutsideTouchable(true);
        popupMenu.setBackgroundDrawable(new BitmapDrawable());
        popupMenu.setFocusable(true);
        popupMenu.setAnimationStyle(R.style.popwin_anim_style);
        popupMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                iv1.setImageResource(R.drawable.ic_expand_less_black_24dp);
                iv2.setImageResource(R.drawable.ic_expand_less_black_24dp);
                iv3.setImageResource(R.drawable.ic_expand_less_black_24dp);
                iv4.setImageResource(R.drawable.ic_expand_less_black_24dp);
                mProductTv.setTextColor(Color.parseColor("#5a5959"));
                mSortTv.setTextColor(Color.parseColor("#5a5959"));
                mCategoryTv.setTextColor(Color.parseColor("#5a5959"));
                supplier_list_activity_tvjl.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupMenu.dismiss();
                    }
                });

        mMenuAdapter1 = new SimpleAdapter(getActivity(), mMenuData1, R.layout.item_listview_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mMenuAdapter2 = new SimpleAdapter(getActivity(), mMenuData2, R.layout.item_listview_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mMenuAdapter3 = new SimpleAdapter(getActivity(), mMenuData3, R.layout.item_listview_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mMenuAdapter4 = new SimpleAdapter(getActivity(), mMenuData4, R.layout.item_listview_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mPopListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupMenu.dismiss();
                switch (menuIndex) {
                    case 0:
                        String currentProduct = mMenuData1.get(i).get("name");
                        mSupplierListTitleTv.setText(currentProduct);
                        mProductTv.setText(currentProduct);
                        break;
                    case 1:
                        String currentSort = mMenuData2.get(i).get("name");
                        mSupplierListTitleTv.setText(currentSort);
                        mSortTv.setText(currentSort);
                        break;
                    case 2:
                        String currentAct = mMenuData3.get(i).get("name");
                        mSupplierListTitleTv.setText(currentAct);
                        mCategoryTv.setText(currentAct);
                        break;
                    case 3:
                        String currentActs = mMenuData4.get(i).get("name");
                        mSupplierListTitleTv.setText(currentActs);
                        supplier_list_activity_tvjl.setText(currentActs);
                        break;
                }
            }
        });
    }
    @OnClick({R.id.supplier_list_product, R.id.supplier_list_activity_jl,R.id.supplier_list_sort,R.id.supplier_list_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.supplier_list_product:
                mProductTv.setTextColor(Color.parseColor("#ea1010"));
                iv1.setImageResource(R.drawable.ic_expand_more_black_24dp);
                mPopListview.setAdapter(mMenuAdapter1);
                popupMenu.showAsDropDown(mProduct, 0, 2);
                menuIndex = 0;
                break;
            case R.id.supplier_list_sort:
                mSortTv.setTextColor(Color.parseColor("#ea1010"));
                iv2.setImageResource(R.drawable.ic_expand_more_black_24dp);
                mPopListview.setAdapter(mMenuAdapter2);
                popupMenu.showAsDropDown(mSort, 0, 2);
                menuIndex = 1;
                break;
            case R.id.supplier_list_activity:
                mCategoryTv.setTextColor(Color.parseColor("#ea1010"));
                iv3.setImageResource(R.drawable.ic_expand_more_black_24dp);
                mPopListview.setAdapter(mMenuAdapter3);
                popupMenu.showAsDropDown(mCategory, 0, 2);
                menuIndex = 2;
                break;
            case R.id.supplier_list_activity_jl:
                supplier_list_activity_tvjl.setTextColor(Color.parseColor("#ea1010"));
                iv4.setImageResource(R.drawable.ic_expand_more_black_24dp);
                mPopListview.setAdapter(mMenuAdapter4);
                popupMenu.showAsDropDown(supplier_list_activity_jl, 0, 2);
                menuIndex = 3;
                break;
        }
    }

}
