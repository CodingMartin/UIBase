package com.martin.framework.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.martin.framework.common.RevealController;
import com.martin.framework.view.EmptyView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/26
 */
public abstract class BaseCompatFragment extends Fragment implements BaseRevealView {
    RevealController mRevealController;
    Context mContext;
    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof BaseCompatActivity) {
            mRevealController = ((BaseCompatActivity) context).getRevealController();
        }
        if (mRevealController == null) {
            mRevealController = new RevealController(context);
        }
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(getContentViewId(), container, false);
        mUnBinder = ButterKnife.bind(this, mView);
        bindView(mView, savedInstanceState);
        bindEvent();
        bindData();
        return mView;
    }

    /**
     * 绑定数据
     */
    protected abstract void bindData();

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 绑定视图
     *
     * @param mView
     * @param savedInstanceState
     */
    protected void bindView(View mView, Bundle savedInstanceState) {
    }


    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int getContentViewId();

    @Override
    public void showProgress(String message) {
        mRevealController.showProgress(message);
    }

    @Override
    public void showProgress() {
        mRevealController.showProgress();
    }

    @Override
    public void hideProgress() {
        mRevealController.hideProgress();
    }

    @Override
    public void showToast(String message) {
        mRevealController.showToast(message);
    }

    @Override
    public void showTipToast(String message) {
        mRevealController.showTipToast(message);
    }

    @Override
    public void cancelToast() {
        mRevealController.cancelToast();
    }

    @Override
    public void showMessageDialog(String message) {
        mRevealController.showMessageDialog(message);
    }

    @Override
    public void showMessageDialog(String message, String okTip) {
        mRevealController.showMessageDialog(message, okTip);
    }

    @Override
    public void showMessageDialog(String message, String ok, DialogInterface.OnClickListener okClickListener) {
        mRevealController.showMessageDialog(message, ok, okClickListener);
    }

    @Override
    public void showEmptyView(@EmptyView.ViewState int state) {
        mRevealController.showEmptyView(state);
    }

    @Override public void hideEmptyView() {
        mRevealController.hideEmptyView();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
