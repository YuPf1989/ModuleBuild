package com.rain.module_girls;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rain.baselib.glide.GlideApp;
import com.rain.module_girls.model.GirlsBean;

import java.util.List;

/**
 * Author:rain
 * Date:2018/6/28 14:54
 * Description:
 */
public class GirlsAdapter extends BaseQuickAdapter<GirlsBean.ResultsBean,BaseViewHolder> {
    public GirlsAdapter(int layoutResId, @Nullable List<GirlsBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GirlsBean.ResultsBean item) {
        GlideApp.with(mContext)
                .load(item.getUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into((ImageView) helper.getView(R.id.girl_img));
    }
}
