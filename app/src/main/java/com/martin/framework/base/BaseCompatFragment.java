package com.martin.framework.base;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

import com.martin.framework.common.RevealController;
import com.martin.framework.view.PlaceholderPopWindow;
import com.martin.framework.view.PlaceholderView;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/26
 */
public class BaseCompatFragment extends Fragment implements BaseRevealView,PlaceholderView.OnReloadListener {
    RevealController mRevealController;
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof BaseCompatActivity) {
            mRevealController = ((BaseCompatActivity) context).getRevealController();
        }
        if (mRevealController==null){
            mRevealController = new RevealController(context);
            mRevealController.setPlaceHolderBuilder(setupPlaceHolderBuild());
        }
    }

    @CallSuper
    protected PlaceholderPopWindow.Builder setupPlaceHolderBuild() {
        PlaceholderPopWindow.Builder builder = new PlaceholderPopWindow.Builder(mContext);
        builder.setListener(this);
        return builder;
    }

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
        mRevealController.showMessageDialog(message,okTip);
    }

    @Override
    public void showMessageDialog(String message, String ok, DialogInterface.OnClickListener okClickListener) {
        mRevealController.showMessageDialog(message,ok,okClickListener);
    }

    @Override
    public void showPlaceholder(PlaceholderPopWindow.Builder builder, @PlaceholderView.ViewState int state) {
        mRevealController.showPlaceholder(builder,state);
    }

    @Override
    public void showPlaceholder(@PlaceholderView.ViewState int state) {
        mRevealController.showPlaceholder(state);
    }

    @Override public void onReload() {

    }
}
