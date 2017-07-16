package com.sirisakj.lib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
/**
 * Created by Sirisak on 6/8/2559.
 */
public abstract class _06FragmentManagerActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private FragmentTool mFragmentTool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();

        mFragmentTool = new FragmentTool();
    }

    public FragmentTool getFragmentTool(){ return mFragmentTool; }


    public class FragmentTool {
        public void add(int idViewGroup, Fragment fragment){

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(idViewGroup, fragment);
            transaction.commit();
        }

        public void set(int idViewGroup, Fragment fragment){

            Fragment prevFragment = mFragmentManager.findFragmentById(idViewGroup);


            if(prevFragment == null){
                add(idViewGroup,fragment);
            }
            else if(!prevFragment.getClass().equals(fragment.getClass())){
                FragmentTransaction transaction = mFragmentManager.beginTransaction();

                transaction.replace(idViewGroup,fragment);
                transaction.commit();
            }




        }
    }

    public interface OnTransactionFragment {
    }
}