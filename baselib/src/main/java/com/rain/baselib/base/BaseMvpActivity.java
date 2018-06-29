package com.rain.baselib.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author:rain
 * Date:2018/6/20 15:47
 * Description:
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IBaseView {
    protected T presenter;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = creatPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected abstract T creatPresenter();

    @Override
    public void onShowLoading() {
        showLoadingProgressbar();
    }

    @Override
    public void onHideLoading() {
        hideLoadingProgressbar();
    }

}
