package com.rain.baselib.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author:rain
 * Date:2018/5/29 15:10
 * Description:
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter<T>{
    protected T view;
    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(T view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    public void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter");
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
