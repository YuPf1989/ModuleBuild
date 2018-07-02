package com.rain.module_girls;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.rain.baselib.base.BaseFragment;

/**
 * Author:rain
 * Date:2018/7/2 11:11
 * Description:
 */
@Route(path = "/girls/fragment")
public class GirlsFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girls;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public static GirlsFragment newInstance() {
        return new GirlsFragment();
    }
}
