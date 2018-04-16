package com.contedevel.producthunt.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contedevel.producthunt.R;
import com.contedevel.producthunt.client.ServiceApi;
import com.contedevel.producthunt.client.Topic;
import com.contedevel.producthunt.databinding.ActivityMainBinding;
import com.contedevel.producthunt.view.AppBarSpinnerAdapter;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Topic[]> {

    private ActivityMainBinding mBinding;
    private AppBarSpinnerAdapter<Topic> mCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mCategoryAdapter = new AppBarSpinnerAdapter<>(this);
        initAppBar();
        getSupportLoaderManager()
                .initLoader(0, null, this)
                .forceLoad();
    }

    private void initAppBar() {
        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mBinding.toolbarLayout.spinner.setAdapter(mCategoryAdapter);
    }

    @NonNull
    @Override
    public Loader<Topic[]> onCreateLoader(int id, @Nullable Bundle args) {
        return new TopicsLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Topic[]> loader, Topic[] data) {
        mCategoryAdapter.clear();
        int position = 0;

        for (int i = 0; i < data.length; ++i) {
            Topic topic = data[i];
            mCategoryAdapter.add(topic);

            if ("tech".equals(topic.getName().toLowerCase())) {
                position = i;
            }
        }

        mCategoryAdapter.notifyDataSetChanged();

        if (data.length > 0) {
            mBinding.toolbarLayout.spinner.setSelection(position);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Topic[]> loader) {

    }

    private static class TopicsLoader extends AsyncTaskLoader<Topic[]> {

        private ServiceApi mApi = new ServiceApi();

        TopicsLoader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public Topic[] loadInBackground() {
            return mApi.getTopics();
        }
    }
}
