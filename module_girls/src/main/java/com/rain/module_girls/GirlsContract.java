package com.rain.module_girls;

import com.rain.baselib.base.IBasePresenter;
import com.rain.baselib.base.IBaseView;
import com.rain.baselib.base.ILoadMoreView;
import com.rain.module_girls.model.GirlsBean;

import java.util.List;

/**
 * Author:rain
 * Date:2018/6/28 11:39
 * Description:
 */
public interface GirlsContract {
    interface View extends IBaseView,ILoadMoreView{
        void setGirlData(List<GirlsBean.ResultsBean> data);

        @Override
        void onShowLoading();

        @Override
        void onHideLoading();

        @Override
        void onShowNetError(String err_msg, int err_code);

        @Override
        void onLoadEnd();

        @Override
        void onLoadComplete();

        @Override
        void onLoadFail();

        @Override
        void setLoadMoreData(Object o);
    }

    interface Presenter extends IBasePresenter<View>{
        void loadGirlData(int page,int size);

        void loadMoreData(int page);

    }

}
