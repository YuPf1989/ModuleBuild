package com.rain.baselib.base;

/**
 * Created by Meiji on 2017/5/7.
 */

public interface IBasePresenter<V extends IBaseView> {

    /**
     * presenter与view视图绑定
     */
    void attachView(V view);

    /**
     * presenter与view视图解绑
     */
    void detachView();

}
