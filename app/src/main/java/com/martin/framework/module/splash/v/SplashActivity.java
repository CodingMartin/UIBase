package com.martin.framework.module.splash.v;

import android.support.annotation.NonNull;

import com.martin.framework.R;
import com.martin.framework.base.BaseIntricateActivity;
import com.martin.framework.module.splash.c.SplashContract;
import com.martin.framework.module.splash.p.SplashPresenter;

/**
 * 描述：
 */
public class SplashActivity extends BaseIntricateActivity<SplashPresenter> implements SplashContract.View {

    @Override public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @NonNull @Override public SplashPresenter bindPresenter() {
        return new SplashPresenter(this);
    }

    @Override protected void bindEvent() {

    }

    @Override protected CharSequence getMainTitle() {
        return getString(R.string.app_name);
    }
}
