package com.martin.framework.base.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.martin.framework.base.BaseCompatActivity;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/6
 */
public abstract class BaseIntricateActivity<P extends BasePresenterImpl> extends BaseCompatActivity {

    private P mPresenter;


    @CallSuper @Override protected void bindData() {
        mPresenter = bindPresenter();
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onAttached();
    }

    @CallSuper @Override protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetached();
    }

    /**
     * 创建Presenter
     *
     * @return
     */
    @NonNull
    public abstract P bindPresenter();

    /**
     * 返回一个Presenter实例
     *
     * @return
     */
    @CheckResult
    public P getPresenter() {
        return mPresenter;
    }
}
