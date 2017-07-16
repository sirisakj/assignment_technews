package com.sirisakj.lib.activity;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.sirisakj.lib.activity.im.IBaseContext;
import com.sirisakj.lib.constant.BaseConstants;

import java.io.Serializable;

import static com.sirisakj.lib.constant.BaseConstants.KEY_PARAMS;

/**
 * Created by Sirisak on 1/6/2559.
 */
public abstract class BaseActivity<BINDING extends ViewDataBinding, PARAM extends Serializable> extends _06FragmentManagerActivity implements BaseConstants,IBaseContext {

    protected BINDING mBinding;
    protected PARAM mPrms;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,getLayoutId());


        mPrms = getParams();

        initial(savedInstanceState, mBinding, mPrms);



    }

    public PARAM getParams(){
        try
        {
            if(mPrms != null)
                return mPrms;
            return (PARAM) getIntent().getSerializableExtra(KEY_PARAMS);
        }
        catch (Throwable t){}
        return null;
    }

    public PARAM getParams(PARAM defaultValue){
        try
        {
            if(mPrms != null)
                return mPrms;
            PARAM parms = (PARAM) getIntent().getSerializableExtra(KEY_PARAMS);
            if(parms == null){
                return defaultValue;
            }
            else
            {
                return parms;
            }
        }
        catch (Throwable t){}
        return defaultValue;
    }


    public abstract int getLayoutId();

    public BINDING getBinding(){return mBinding;}


    public abstract void initial(final Bundle savedInstanceState, final BINDING binding, final PARAM prms);

    public void startActivityWithParams(Class<?> cls, Serializable prms ){
        startActivityWithParams(cls,prms, false);
    }

    public Activity getActivity(){return this;}

    public void startActivityWithParams(Class<?> cls, Serializable prms , boolean needFinish ){
        if(needFinish){
            finish();
        }
        Intent i = new Intent(this,cls);
        i.putExtra(KEY_PARAMS, prms);
        startActivity(i);
    }

    public void startActivityForResultWithParams(Class<?> cls, Serializable prms , int requestCode ){

        Intent i = new Intent(this,cls);
        i.putExtra(KEY_PARAMS, prms);
        startActivityForResult(i, requestCode);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
