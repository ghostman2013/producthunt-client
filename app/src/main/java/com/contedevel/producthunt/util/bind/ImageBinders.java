package com.contedevel.producthunt.util.bind;

import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public final class ImageBinders {

    private ImageBinders() {

    }

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, @Nullable String url) {

        if (TextUtils.isEmpty(url)) {
            imageView.setImageDrawable(null);
        } else {
            Picasso.get()
                    .load(url)
                    .into(imageView);
        }
    }
}
