package com.rain.module_girls;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.rain.baselib.base.BaseMvpActivity;
import com.rain.baselib.net.Exception.ErrorStatus;
import com.rain.baselib.util.ToastUtil;
import com.rain.module_girls.GirlsContract.View;
import com.rain.module_girls.model.GirlsBean;

import java.util.List;

import butterknife.ButterKnife;

@Route(path = "/girls/list")
public class GirlsActivity extends BaseMvpActivity implements View {
    private Toolbar toolbar;
    private RecyclerView recycler;
    private MultipleStatusView multipleStatusView;
    private SwipeRefreshLayout swipe;

    private static final int size = 10;
    private int default_page = 1;
    private GirlsAdapter girlsAdapter;
    private GirlsPresenter presenter;


    private void startLoad() {
        presenter.loadGirlData(size,1);
    }

    @Override
    public void onHideLoading() {
        multipleStatusView.showContent();
        super.onHideLoading();
        if (swipe.isRefreshing()) {
            swipe.setRefreshing(false);
        }
    }

    // 多状态view点击重新加载数据调用
    @Override
    protected void loadData() {
        multipleStatusView.showContent();
        onShowLoading();
        startLoad();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_girls;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        findView();
        mLayoutStatusView = multipleStatusView;
        initToolBar(toolbar,true,"girls");
        initRecyclerView();
        presenter.loadGirlData(default_page, size);
    }

    private void findView() {
        toolbar = findViewById(R.id.toolbar);
        recycler = findViewById(R.id.recycler);
        multipleStatusView = findViewById(R.id.multipleStatusView);
        swipe = findViewById(R.id.swipe);
    }

    private void initRecyclerView() {
        swipe.setColorSchemeResources(R.color.colorPrimary);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startLoad();
            }
        });
        girlsAdapter = new GirlsAdapter(R.layout.item_girl, null);
        recycler.setAdapter(girlsAdapter);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        girlsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                default_page++;
                presenter.loadMoreData(default_page);
            }
        }, recycler);
    }

    @Override
    protected GirlsPresenter creatPresenter() {
        presenter = new GirlsPresenter();
        return presenter;
    }

    @Override
    public void setGirlData(List<GirlsBean.ResultsBean> data) {
        girlsAdapter.setNewData(data);
    }

    @Override
    public void onShowNetError(String err_msg, int err_code) {
        if (err_code == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView.showNoNetwork();
        } else {
            mLayoutStatusView.showError();
        }
        ToastUtil.showToast(err_msg);
    }

    @Override
    public void onLoadEnd() {
        girlsAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadComplete() {
        girlsAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadFail() {
        girlsAdapter.loadMoreFail();
    }

    @Override
    public void setLoadMoreData(Object o) {
        girlsAdapter.addData((List<GirlsBean.ResultsBean>) o);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
