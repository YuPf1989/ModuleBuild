package com.rain.module_girls;

import com.rain.baselib.base.BasePresenter;
import com.rain.baselib.net.Exception.ExceptionHandle;
import com.rain.module_girls.model.GirlsBean;
import com.rain.module_girls.model.GirlsModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author:rain
 * Date:2018/6/28 11:43
 * Description:
 */
public class GirlsPresenter extends BasePresenter<GirlsContract.View> implements GirlsContract.Presenter {

    private boolean isError;
    private int currentPage;

    @Override
    public void loadGirlData(int size, final int page) {
        Disposable disposable = GirlsModel.getGirls(size, page)
                .subscribe(new Consumer<GirlsBean>() {
                    @Override
                    public void accept(GirlsBean girlsBean) throws Exception {
                        isError = girlsBean.isError();
                        currentPage = page;
                        if (page > 1) {
                            view.setLoadMoreData(girlsBean.getResults());
                        } else {
                            view.setGirlData(girlsBean.getResults());
                        }
                        view.onLoadComplete();
                        view.onHideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (currentPage > 1) {
                            view.onLoadFail();
                        } else {
                            view.onShowNetError(ExceptionHandle.handleException(throwable),ExceptionHandle.getErrorCode());
                        }
                        view.onHideLoading();
                    }
                });
        addSubscription(disposable);
    }

    @Override
    public void loadMoreData(int page) {
        if (isError) {
            view.onLoadEnd();
            return;
        }
        loadGirlData(10,page);
    }
}
