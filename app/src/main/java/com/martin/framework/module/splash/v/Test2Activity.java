package com.martin.framework.module.splash.v;

import android.support.annotation.NonNull;

import com.martin.framework.R;
import com.martin.framework.base.mvp.BaseIntricateActivity;
import com.martin.framework.module.splash.c.LoginContract;
import com.martin.framework.module.splash.p.LoginPresenter;

/**
 * 描述：
 */
public class Test2Activity extends BaseIntricateActivity<LoginPresenter> implements LoginContract.View{

    @Override public int getContentViewId() {
        return R.layout.activity_test2;
    }


    @Override protected void bindEvent() {

    }


    @NonNull @Override public LoginPresenter bindPresenter() {
        return new LoginPresenter(this);
    }

    @Override public String getAccount() {
        return null;
    }

    @Override public String getPassword() {
        return null;
    }

    @Override public void actionHomePage() {

    }
}
