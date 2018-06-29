package com.rain.baselib.net.MyObserver;


import com.rain.baselib.base.BasePresenter;
import com.rain.baselib.base.IBaseView;
import com.rain.baselib.net.Exception.ExceptionHandle;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author:rain
 * Date:2017/11/13 14:20
 * Description:
 * 该observer封装loading
 * 配合baseMvpFragment使用，里边实现了onShowLoading()，onHideLoading()
 */

public abstract class MyObserver2<T> implements Observer<T> {
    private IBaseView view;
    private boolean isShowLoading;
    private BasePresenter presenter;

    public MyObserver2(IBaseView view, boolean isShowLoading) {
        this(view, isShowLoading, null);
    }

    public MyObserver2(IBaseView view, boolean isShowLoading, BasePresenter presenter) {
        this.view = view;
        this.isShowLoading = isShowLoading;
        this.presenter = presenter;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (isShowLoading) {
            view.onShowLoading();
        }
        if (presenter != null) {
            presenter.addSubscription(d);
        }
    }

    @Override
    public abstract void onNext(@NonNull T t);

    @Override
    public void onError(@NonNull Throwable e) {
        // 异常，统一交给该处理的类去处理
        ExceptionHandle.handleException(e);
    }

    @Override
    public void onComplete() {
        if (isShowLoading) {
            view.onHideLoading();
        }
    }
}
