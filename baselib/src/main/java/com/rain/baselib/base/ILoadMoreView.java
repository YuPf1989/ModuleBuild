package com.rain.baselib.base;

/**
 * Author:rain
 * Date:2018/6/14 11:11
 * Description:
 * 主要用于recycler分页加载
 */
public interface ILoadMoreView {
    // 加载完毕
    void onLoadEnd();

    // 数据当次请求完成，不代表数据全部加载完毕
    void onLoadComplete();

    // 当次数据加载失败
    void onLoadFail();

    // 设置加载更多的数据
    void setLoadMoreData(Object o);
}
