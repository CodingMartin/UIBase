package com.martin.framework.module.splash.p;

import com.martin.framework.module.splash.c.LoginContract;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
