package com.martin.framework.view;

import android.content.DialogInterface;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public interface BaseRevealView {

    /**
     * 显示一个Loading
     */
    void showProgress(String message);

    /**
     * 显示一个Loading
     */
    void showProgress();

    /**
     * 隐藏Loading
     */
    void hideProgress();

    void showToast(String message);

    void showTipToast(String message);

    void cancelToast();

    void showMessageDialog(String message);

    void showMessageDialog(String message, String okTip);

    void showMessageDialog(String message, String ok, DialogInterface.OnClickListener okClickListener);

    void showEmptyView(@EmptyView.ViewState int state);

    void hideEmptyView();

}