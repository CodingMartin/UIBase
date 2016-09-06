package com.martin.framework.module.splash.m;

import android.os.Handler;

import com.martin.framework.module.splash.c.LoginContract;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/1
 */
public class LoginDataProvider implements LoginContract.DataProvider {


    @Override public void doLogin(String account, String password, LoginCallback loginCallback) {
        final LoginCallback callback = loginCallback;
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean success = Math.random() >= 0.5f;
                if (callback == null) return;
                if (success) {
                    callback.onSuccess();
                } else {
                    callback.onFailed();
                }
            }
        }, 5 * 1000);


    }

    public interface LoginCallback {
        void onSuccess();

        void onFailed();
    }
}
