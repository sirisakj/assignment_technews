package com.sirisakj.lib.component.im;

import android.view.View;

/**
 * Created by Sirisak on 18/8/2559.
 */
public interface BaseListViewListener<VIEW extends View, BIND>  {
    void onRowInitial(VIEW bind, BIND itemData, int position);
}
