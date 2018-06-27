package com.rain.baselib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Author:rain
 * Date:2018/6/20 16:03
 * Description:
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements IBaseView{
    protected T presenter;
    private BaseActivity baseActivity;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = creatPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        baseActivity = ((BaseActivity) getActivity());
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract T creatPresenter();

    @Override
    public void onShowLoading() {
        baseActivity.showLoadingProgressbar();
    }

    @Override
    public void onHideLoading() {
        baseActivity.hideLoadingProgressbar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
