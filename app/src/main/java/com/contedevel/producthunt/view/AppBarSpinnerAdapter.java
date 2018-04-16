package com.contedevel.producthunt.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.contedevel.producthunt.R;

public class AppBarSpinnerAdapter<T> extends ArrayAdapter<T> {

    public AppBarSpinnerAdapter(@NonNull Context context) {
        super(context, R.layout.app_bar_spinner_item);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
