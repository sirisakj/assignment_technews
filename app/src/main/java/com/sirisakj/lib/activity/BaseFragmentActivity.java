package com.sirisakj.lib.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by Sirisak on 6/8/2559.
 */
public abstract class BaseFragmentActivity<BINDING extends ViewDataBinding, PARAM extends Serializable> extends BaseActivity<BINDING, PARAM> {

    @Override
    public final void initial(Bundle savedInstanceState, BINDING binding, PARAM prms) {
        initialActivity(savedInstanceState,binding,prms);
        /*
        if(savedInstanceState == null){
            initialFragment(savedInstanceState,binding,prms, getFragmentTool());
        }
        //*/

        initialFragment(savedInstanceState,binding,prms, getFragmentTool());
    }

    public abstract void initialActivity(Bundle savedInstanceState, BINDING binding, PARAM prms);

    public abstract void initialFragment(Bundle savedInstanceState, BINDING binding, PARAM prms, FragmentTool fragmentTool);



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
