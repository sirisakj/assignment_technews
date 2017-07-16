package com.sirisakj.technews.component;

import android.content.Context;
import android.util.AttributeSet;

import com.sirisakj.lib.component.BaseGroupView;
import com.sirisakj.technews.R;
import com.sirisakj.technews.component.bind.FeedViewBind;
import com.sirisakj.technews.databinding.CompFeedviewBinding;

/**
 * Created by icsme on 15-Jul-17.
 */

public class FeedView extends BaseGroupView<CompFeedviewBinding> {


    public FeedView(Context context) {
        super(context);
    }

    public FeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initial(Context c, CompFeedviewBinding binding, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        binding.setBind(new FeedViewBind());
    }




    @Override
    public int layoutId() {
        return R.layout.comp_feedview;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width  = MeasureSpec.getSize(widthMeasureSpec);

        int newSize = MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY);

        super.onMeasure(newSize, newSize);
        setMeasuredDimension(width,width);
    }

    public void setBind(FeedViewBind bind) {
        getBinding().setBind(bind);
    }
}
