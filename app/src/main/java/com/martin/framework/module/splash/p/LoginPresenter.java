package com.martin.framework.module.splash.p;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.martin.framework.base.mvp.BasePresenterImpl;
import com.martin.framework.module.splash.c.LoginContract;
import com.martin.framework.module.splash.m.LoginDataProvider;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public class LoginPresenter extends BasePresenterImpl<LoginContract.View,LoginContract.DataProvider>
        implements LoginContract.Presenter, LoginDataProvider.LoginCallback {


    public LoginPresenter(LoginContract.View view) {
        super(view);
    }


    @NonNull @Override protected LoginContract.DataProvider bindDataProvider() {
        return new LoginDataProvider();
    }

    @Override public void onLogin() {
        String account = getView().getAccount();
        String password = getView().getPassword();
        if (checkLoginData(account, password)) {
            getView().showProgress("正在登录...");
            getDataProvider().doLogin(account, password, this);
        }
    }

    private boolean checkLoginData(String account, String password) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            getView().showToast("用户名或密码不能为空");
            return false;
        } else if (account.length() < 11) {
            getView().showToast("请输入正确的账号");
            return false;
        } else if (password.length() < 6) {
            getView().showToast("请输入正确的密码");
            return false;
        }
        return true;
    }

    @Override public void onSuccess() {
        if (!checkNotNull()) return;
        getView().hideProgress();
        getView().actionHomePage();
    }

    @Override public void onFailed() {
        if (!checkNotNull()) return;
        getView().hideProgress();
        getView().showMessageDialog("登录失败请重试!");
    }
}
