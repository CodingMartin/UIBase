package com.martin.framework.base.mvp;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public abstract class BasePresenterImpl<V extends BaseView, M extends BaseDataProvider> implements BasePresenter {

    private V mView;
    private M mDataProvider;

    public BasePresenterImpl(V v) {
        this.mView = v;
        mDataProvider = bindDataProvider();
    }

    /**
     * 绑定数据提供者(M)
     *
     * @return
     */
    @NonNull
    protected abstract M bindDataProvider();

    /**
     * 返回一个数据提供者
     *
     * @return
     */
    @NonNull
    protected M getDataProvider() {
        return mDataProvider;
    }

    protected V getView() {
        return mView;
    }

    @Override
    public void onAttached() {

    }

    @Override public void onDetached() {
        mView = null;
    }

    /**
     * 检测{@link #mView View是否为空}
     * @return
     */
    @CheckResult
    protected final boolean checkNotNull() {
        return mView != null;
    }


}
