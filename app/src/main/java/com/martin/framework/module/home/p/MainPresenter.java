package com.martin.framework.module.home.p;

import com.martin.framework.module.home.c.MainContract;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public class MainPresenter implements MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }
}
