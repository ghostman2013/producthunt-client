package com.contedevel.producthunt.util;

import android.support.v7.widget.RecyclerView;

public class RecyclerConfig {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter<?> mAdapter;
    private boolean mHasFixedSize;

    private RecyclerConfig() {

    }

    public void apply(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(mHasFixedSize);
    }

    public static class Builder {
        private RecyclerConfig mConfig = new RecyclerConfig();

        public Builder setLayoutManager(RecyclerView.LayoutManager lm) {
            mConfig.mLayoutManager = lm;
            return this;
        }

        public Builder setAdapter(RecyclerView.Adapter<?> adapter) {
            mConfig.mAdapter = adapter;
            return this;
        }

        public Builder setHasFixedSize(boolean hasFixedSize) {
            mConfig.mHasFixedSize = hasFixedSize;
            return this;
        }

        public RecyclerConfig build() {
            return mConfig;
        }
    }
}
