package com.rain.module_news;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.rain.baselib.base.BaseFragment;

/**
 * Author:rain
 * Date:2018/7/2 11:09
 * Description:
 */
@Route(path = "/news/fragment")
public class NewsFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }
}
