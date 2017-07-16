package com.sirisakj.lib.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by icsme on 15-Jul-17.
 */

public class BaseImage extends android.support.v7.widget.AppCompatImageView {
    public BaseImage(Context context) {
        super(context);
    }

    public BaseImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadUrl(String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.with(getContext())
                    .load(url)
                    .into(this);
        }
        else
        {
            Picasso.with(getContext()).cancelRequest(this);
        }
    }
}
