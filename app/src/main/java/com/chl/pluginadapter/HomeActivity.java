package com.chl.pluginadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chl.pluginadapter.adapter.HomeAdapter;
import com.chl.pluginadapter.entity.HomeItem;

import java.util.ArrayList;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeActivity extends AppCompatActivity {
    private static final Class<?>[] ACTIVITY = {
            AnimationUseActivity.class,
            ChooseMultipleItemUseTypeActivity.class,
            HeaderAndFooterUseActivity.class,
            PullToRefreshUseActivity.class,
            SectionUseActivity.class,
            EmptyViewUseActivity.class,
            ItemDragAndSwipeUseActivity.class,
            ItemClickActivity.class,
            ExpandableUseActivity.class,
            DataBindingUseActivity.class
//            UpFetchUseActivity.class
    };
    private static final String[] TITLE = {
            "动画",
            "多元素",
            "头脚",
            "拉刷新",
            "部分",
            "空页面",
            "拖拽",
            "元素点击",
            "展开条目",
            "数据绑定"
//            "UpFetchData"
    };
    private static final int[] IMG = {
            R.mipmap.gv_animation,
            R.mipmap.gv_multipleltem,
            R.mipmap.gv_header_and_footer,
            R.mipmap.gv_pulltorefresh,
            R.mipmap.gv_section,
            R.mipmap.gv_empty,
            R.mipmap.gv_drag_and_swipe,
            R.mipmap.gv_item_click,
            R.mipmap.gv_expandable,
            R.mipmap.gv_databinding
//            R.drawable.gv_up_fetch
    };
    private ArrayList<HomeItem> mDataList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @SuppressWarnings("unchecked")
    private void initAdapter() {
        // 主体列表
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
        homeAdapter.openLoadAnimation();
        // 头部
        View top = getLayoutInflater().inflate(R.layout.top_view, (ViewGroup) mRecyclerView.getParent(), false);
        homeAdapter.addHeaderView(top);
        // 底部
        View footerView = getLayoutInflater().inflate(R.layout.bottom_view, (ViewGroup) mRecyclerView.getParent(), false);
        homeAdapter.addFooterView(footerView);

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeActivity.this, ACTIVITY[position]);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(homeAdapter);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }

}
