package com.martin.framework.module.splash.c;

import com.martin.framework.base.mvp.BaseDataProvider;
import com.martin.framework.base.mvp.BasePresenter;
import com.martin.framework.base.mvp.BaseView;
import com.martin.framework.module.splash.m.LoginDataProvider;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public interface LoginContract {


    interface View extends BaseView {
        String getAccount();
        String getPassword();
        void actionHomePage();
    }

    interface Presenter extends BasePresenter {
        void onLogin();
    }

    interface DataProvider extends BaseDataProvider {
        void doLogin(String account, String password, LoginDataProvider.LoginCallback loginCallback);
    }
}
