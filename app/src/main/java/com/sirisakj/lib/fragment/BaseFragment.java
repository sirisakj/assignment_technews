package com.sirisakj.lib.fragment;

/**
 * Created by Sirisak on 6/8/2559.
 */

        import android.app.Activity;
        import android.databinding.DataBindingUtil;
        import android.databinding.ViewDataBinding;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;


        import com.sirisakj.lib.constant.BaseConstants;

        import java.io.Serializable;



@SuppressWarnings("unused")
public abstract class BaseFragment<BINDING extends ViewDataBinding, ARGUMENT extends Serializable> extends Fragment implements BaseConstants {
    private ARGUMENT mArguments = null;
    private BINDING mBind = null;

    public BaseFragment() {
        super();
    }


    public void finishResult(int resultCode){
        Activity a = getActivity();

        a.setResult(resultCode);
        a.finish();
    }

    public void finish(){
        Activity a = getActivity();
        a.finish();
    }

    public void setArguments(ARGUMENT args) {


        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ARG, args);
        setArguments(bundle);
    }

    public ARGUMENT getArguments(ARGUMENT args) {
        if (mArguments != null) {
            return mArguments;
        }
        Object obj = getArguments().getSerializable(KEY_ARG);
        if (obj != null) {
            mArguments = (ARGUMENT) obj;
        }
        return mArguments;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBind = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        initInstances(mBind, savedInstanceState);
        return mBind.getRoot();
    }

    public BINDING getBinding() {
        return mBind;
    }

    public abstract int layoutId();

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    protected abstract void initInstances(BINDING pBinding, Bundle savedInstanceState);

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    /*
         * Save Instance State Here
         */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    protected abstract void onRestoreInstanceState(Bundle savedInstanceState);

    public interface FragmentCallbackAction {

    }

}