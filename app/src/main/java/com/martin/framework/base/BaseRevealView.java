package com.martin.framework.base;

import android.content.DialogInterface;

import com.martin.framework.view.PlaceholderPopWindow;
import com.martin.framework.view.PlaceholderView;

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

    void showPlaceholder(PlaceholderPopWindow.Builder builder,@PlaceholderView.ViewState int state);
    void showPlaceholder(@PlaceholderView.ViewState int state);
}
