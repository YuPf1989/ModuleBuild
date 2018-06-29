package com.rain.module_main;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rain.baselib.base.BaseActivity;
import com.rain.baselib.util.ToastUtil;

/**
 * Author:rain
 * Date:2018/6/29 9:44
 * Description:
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long exitTime = 0;

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        findViewById(R.id.news_button).setOnClickListener(this);
        findViewById(R.id.girls_button).setOnClickListener(this);
        findViewById(R.id.fragment_button).setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        long currentTime = SystemClock.currentThreadTimeMillis();
        if (currentTime - exitTime > 2000) {
            ToastUtil.showToast("再按一次退出！");
        } else {
            super.onBackPressed();
        }
        exitTime = currentTime;
    }

    @Override
    public void onClick(View view) {
        // note 不能使用switch，提示报错
        if (view.getId() == R.id.news_button) {

        } else if (view.getId() == R.id.girls_button) {
            ARouter.getInstance().build("/girls/list").navigation();
        }else if (view.getId() == R.id.fragment_button) {

        }
        ToastUtil.showToast("id:"+view.getId());
    }
}
