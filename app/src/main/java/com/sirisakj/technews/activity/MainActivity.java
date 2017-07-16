package com.sirisakj.technews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sirisakj.lib.activity.BaseFragmentActivity;
import com.sirisakj.lib.activity._06FragmentManagerActivity;
import com.sirisakj.technews.R;
import com.sirisakj.technews.activity.bind.MainActivityBind;
import com.sirisakj.technews.databinding.ActivityMainBinding;
import com.sirisakj.technews.fragment.FeedsFragment;

import java.io.Serializable;

public class MainActivity extends BaseFragmentActivity<ActivityMainBinding, MainActivity.MainActivityArg> {

    @Override
    public void initialActivity(Bundle savedInstanceState, ActivityMainBinding binding, MainActivityArg prms) {
        binding.setBind(new MainActivityBind());
    }

    @Override
    public void initialFragment(Bundle savedInstanceState, ActivityMainBinding binding, MainActivityArg prms, FragmentTool fragmentTool) {
        fragmentTool.set(R.id.container, FeedsFragment.newInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public static class MainActivityArg implements Serializable {

    }
}
