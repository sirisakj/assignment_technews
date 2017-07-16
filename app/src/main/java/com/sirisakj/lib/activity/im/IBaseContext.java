package com.sirisakj.lib.activity.im;

import java.io.Serializable;

/**
 * Created by Sirisak on 13/6/2559.
 */
public interface IBaseContext {
    public void startActivityWithParams(Class<?> cls, Serializable prms);
}
