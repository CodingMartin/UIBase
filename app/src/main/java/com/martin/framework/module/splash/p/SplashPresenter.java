package com.martin.framework.module.splash.p;

import android.support.annotation.NonNull;

import com.martin.framework.base.mvp.BasePresenterImpl;
import com.martin.framework.module.splash.c.SplashContract;
import com.martin.framework.module.splash.m.SplashDataProvider;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/6
 */
public class SplashPresenter extends BasePresenterImpl<SplashContract.View, SplashContract.DataProvider>
        implements SplashContract.Presenter {
    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @NonNull @Override protected SplashContract.DataProvider bindDataProvider() {
        return new SplashDataProvider();
    }
}
