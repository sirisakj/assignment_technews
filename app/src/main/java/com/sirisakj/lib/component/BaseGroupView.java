package com.sirisakj.lib.component;

/**
 * Created by icsme on 15-Jul-17.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by Sirisak on 10/8/2559.
 */
public abstract class BaseGroupView<BINDING extends ViewDataBinding> extends FrameLayout {

    protected BINDING mBinding;

    public BaseGroupView(Context context) {
        super(context);
        _initial(context,  null, 0, 0);
    }

    public BaseGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);

        _initial(context,  attrs, 0, 0);
    }

    public BaseGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        _initial(context,  attrs, defStyleAttr, 0);
    }



    public void _initial(Context c, AttributeSet attrs, int defStyleAttr, int defStyleRes){


        if(isInEditMode()){
            inflate(getContext(),layoutId(),this);
        }
        else {
            LayoutInflater factory = LayoutInflater.from(getContext());
            mBinding = DataBindingUtil.inflate(factory, layoutId(), this, true);

            initial(c, mBinding, attrs, defStyleAttr, defStyleRes);
        }
    }

    public abstract void initial(Context c, BINDING binding, AttributeSet attrs, int defStyleAttr, int defStyleRes);

    public BINDING getBinding(){return mBinding;}

    public abstract int layoutId();


}