package com.rain.baselib.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;
import com.rain.baselib.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:rain
 * Time:2017/6/14 10:13
 * Description:This is BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG  = BaseActivity.class.getSimpleName();
    private Unbinder bind;
    protected MultipleStatusView mLayoutStatusView;
    protected ImmersionBar immersionBar;
    protected ProgressDialog progressDialog;
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设制竖屏

        setContentView(getLayoutId());

        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();

        bind = ButterKnife.bind(this);

        initViews(savedInstanceState);
        initListener();
    }

    protected void initImmersionBar() {
        immersionBar = ImmersionBar.with(this);// 沉浸式状态栏设置
        immersionBar.fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary)
//                .navigationBarColor(R.color.colorPrimary)
                .keyboardEnable(true)
                .init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private void initListener() {
        if (mLayoutStatusView != null) {
            mLayoutStatusView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadData();
                }
            });
        }
    }

    // 开始请求数据
    protected abstract void loadData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showLoadingProgressbar(){
        mProgressBar = createCenterProgressBar();
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    protected void hideLoadingProgressbar(){
        if (null != mProgressBar && mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private ProgressBar createCenterProgressBar(){
        //整个Activity布局的最终父布局,参见参考资料
        FrameLayout rootFrameLayout=(FrameLayout) this.findViewById(android.R.id.content);
        FrameLayout.LayoutParams layoutParams=
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER;
        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(this);
        }
        mProgressBar.setLayoutParams(layoutParams);
        mProgressBar.setVisibility(View.VISIBLE);
        rootFrameLayout.addView(mProgressBar);
        return mProgressBar;
    }

}
