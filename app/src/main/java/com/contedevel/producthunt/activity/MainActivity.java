package com.contedevel.producthunt.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contedevel.producthunt.R;
import com.contedevel.producthunt.modal.ProductsFragment;

public class MainActivity extends AppCompatActivity {

    private ProductsFragment mProductsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
