package com.contedevel.producthunt.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contedevel.producthunt.R;
import com.contedevel.producthunt.databinding.ActivityMainBinding;
import com.contedevel.producthunt.view.AppBarSpinnerAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private AppBarSpinnerAdapter<String> mCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mCategoryAdapter = new AppBarSpinnerAdapter<>(this);
        initAppBar();
    }

    private void initAppBar() {
        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mBinding.toolbarLayout.spinner.setAdapter(mCategoryAdapter);
        mCategoryAdapter.add("tech");
    }
}
