package com.sirisakj.technews.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.sirisakj.lib.component.BaseListView;
import com.sirisakj.technews.component.bind.FeedViewBind;

/**
 * Created by icsme on 16-Jul-17.
 */

public class FeedViewList extends BaseListView<FeedView,FeedViewBind>{
    public FeedViewList(Context context) {
        super(context);
    }

    public FeedViewList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FeedViewList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public FeedView onInflateDataBinding(Context c, ViewGroup parent, boolean attachToParent) {
        return new FeedView(c);
    }

    @Override
    public void onRowInitial(FeedView VIEW, FeedViewBind bind, int viewType, int position) {
        VIEW.setBind(bind);
    }
}
