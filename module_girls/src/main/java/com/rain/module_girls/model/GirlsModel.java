package com.rain.module_girls.model;

import com.rain.baselib.net.RetrofitHelper;
import com.rain.baselib.rx.scheduler.SchedulerUtils;

import java.io.File;

import io.reactivex.Observable;

/**
 * Author:rain
 * Date:2018/6/28 9:38
 * Description:
 */
public class GirlsModel {

    // http://gank.io/api/data/福利/page/size
    public static Observable<GirlsBean> getGirls(int size, int page) {
        return ((GirlsRetrofitService) RetrofitHelper.getInstance().getRetrofitService(GirlsRetrofitService.class))
                .getGirls("福利" + File.separator + size + File.separator + page)
                .compose(SchedulerUtils.<GirlsBean>ioToMain());
    }
}
