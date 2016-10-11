package com.martin.framework.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.martin.framework.R;
import com.martin.framework.utils.ToastUtil;
import com.martin.framework.view.BaseRevealView;
import com.martin.framework.view.EmptyView;

/**
 * Desc: 各种显示理论上都在这里面进行
 * Author:Martin
 * Date:2016/8/15
 */
public class RevealController implements BaseRevealView {


    private final Context mContext;
    private ProgressDialog mProgressDialog;
    private AlertDialog mMessageDialog;
    @Nullable
    private EmptyView mEmptyView;

    public RevealController(Context context) {
        this.mContext = context;
    }

    @Override
    public void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void showProgress() {
//        showProgress("正在加载...");
        ToastUtil.custom(mContext,"Success", R.mipmap.ic_launcher);
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String message) {
//        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        ToastUtil.show(mContext,"这是测试Toast");
    }


    @Override
    public void showTipToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelToast() {

    }

    @Override
    public void showMessageDialog(String message) {
        showMessageDialog(message, "确认");
    }

    @Override
    public void showMessageDialog(String message, String okTip) {
        showMessageDialog(message, okTip, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) dialog.dismiss();
            }
        });
    }

    @Override
    public void showMessageDialog(String message, String ok, DialogInterface.OnClickListener okClickListener) {
        mMessageDialog = new AlertDialog.Builder(mContext)
                .setMessage(message).setPositiveButton("确认", okClickListener).show();
    }


    @Override
    public void showEmptyView(@EmptyView.ViewState int state) {
        if (mEmptyView != null) {
            mEmptyView.show(state);
        }
    }

    public void emptyViewBelowToolbar(boolean b) {
        if (mEmptyView!=null) {
            mEmptyView.setLocation(b);
        }
    }

    @Override public void hideEmptyView() {
        if (mEmptyView != null) {
            mEmptyView.dismiss();
        }
    }

    public void bindEmptyView(EmptyView emptyView) {
        this.mEmptyView = emptyView;
    }

    @Nullable
    public EmptyView getEmptyView() {
        return mEmptyView;
    }
}
