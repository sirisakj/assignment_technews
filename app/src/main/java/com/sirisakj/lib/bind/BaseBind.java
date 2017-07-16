package com.sirisakj.lib.bind;

import android.databinding.BindingAdapter;

import com.sirisakj.lib.component.BaseImage;

/**
 * Created by icsme on 15-Jul-17.
 */

public class BaseBind {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(BaseImage view, String url) {
        view.loadUrl(url);
    }
}
