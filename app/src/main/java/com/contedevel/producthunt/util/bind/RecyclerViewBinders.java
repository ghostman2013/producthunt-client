package com.contedevel.producthunt.util.bind;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.contedevel.producthunt.util.RecyclerConfig;

public final class RecyclerViewBinders {

    private RecyclerViewBinders() {}

    @BindingAdapter("app:config")
    public static void setRecyclerConfig(RecyclerView recyclerView, RecyclerConfig config) {
        config.apply(recyclerView);
    }
}
