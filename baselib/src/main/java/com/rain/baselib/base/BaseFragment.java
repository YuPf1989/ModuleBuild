package com.rain.baselib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:rain
 * Time:2017/6/15 17:44
 * Description:This is BaseFragment
 */

public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected Context mContext;
    private Unbinder bind;
    protected MultipleStatusView mLayoutStatusView;
    private static final String TAG = "BaseFragment";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        bind = ButterKnife.bind(this, rootView);
        mContext = getContext();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedInstanceState);
        initListener();
        start();
    }

    private void initListener() {
        if (mLayoutStatusView != null) {
            mLayoutStatusView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshData();
                }
            });
        }
    }

    protected void refreshData() {

    }

    protected void start() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null) {
            bind.unbind();
        }
    }

    /**
     * 初始化 Toolbar
     * 注意这里的toolbar在activity中
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((BaseActivity) getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

}
