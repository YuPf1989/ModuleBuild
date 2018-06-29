package com.rain.module_girls.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Author:rain
 * Date:2018/6/28 9:05
 * Description:
 */
public interface GirlsRetrofitService {

    /**
     * 获取女生图片
     * @param url 动态url 格式// http://gank.io/api/data/福利/page/size
     */
    @GET()
    Observable<GirlsBean> getGirls(@Url String url);
}
