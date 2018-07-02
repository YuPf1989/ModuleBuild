package com.rain.module_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rain.baselib.base.BaseActivity;
import com.rain.baselib.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:rain
 * Date:2018/7/2 11:32
 * Description:
 */
public class BottomNavigationActivity extends BaseActivity {

    private NoScrollViewPager view_pager;
    private BottomNavigationView navigation_view;
    private List<Fragment> fragments;
    private MyFragmentPagerAdapter pagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                view_pager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.navigation_dashboard) {
                view_pager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.navigation_notifications) {
                view_pager.setCurrentItem(2);
                return true;
            }
            return false;
        }
    };

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bottom_navigation;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        view_pager = findViewById(R.id.view_pager);
        navigation_view = findViewById(R.id.navigation_view);
        navigation_view.setOnNavigationItemSelectedListener(listener);
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        Fragment newsFragment = (Fragment) ARouter.getInstance().build("/news/fragment").navigation();
        Fragment girlsFragment = (Fragment) ARouter.getInstance().build("/girls/fragment").navigation();
        Fragment newsFragment2 = (Fragment) ARouter.getInstance().build("/news/fragment").navigation();
        fragments.add(girlsFragment);
        fragments.add(newsFragment);
        fragments.add(newsFragment2);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        view_pager.setPagerEnabled(false);
        view_pager.setAdapter(pagerAdapter);
    }
}
