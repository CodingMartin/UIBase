package com.martin.framework.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jaeger.library.StatusBarUtil;
import com.martin.framework.R;
import com.martin.framework.common.RevealController;
import com.martin.framework.view.BaseRevealView;
import com.martin.framework.view.EmptyView;
import com.martin.framework.view.ToolBarX;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc: 活动基类
 * Author:Martin
 * Date:2016/8/15
 */
public abstract class BaseCompatActivity extends AppCompatActivity implements BaseRevealView, EmptyView.OnReloadListener {
    @Nullable
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @Nullable
    @BindView(R.id.id_empty_view) EmptyView mEmptyView;
    private RevealController mRevealController;
    private ToolBarX mToolBarX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetContentView();
        setContentView(getContentViewId());
        handleIntent(getIntent(), savedInstanceState);
        bindBase();
        setSystemStatus();
        bindView();
        bindEvent();
        bindData();
    }

    /**
     * 初始化视图
     */
    protected void bindView() {
        getToolBarX();
    }

    /**
     * 数据绑定
     */
    protected abstract void bindData();

    /**
     * 事件绑定
     */
    protected abstract void bindEvent();


    protected void handleIntent(Intent intent, Bundle savedInstanceState) {
    }

    protected RevealController getRevealController() {
        return mRevealController;
    }

    /**
     * 先于{@link #setContentView(int)}
     */
    protected void preSetContentView() {

    }

    /***
     * 这里用来绑定一些公共的内容,如...
     */
    private void bindBase() {
        ButterKnife.bind(this);
        mRevealController = new RevealController(this);
        mRevealController.bindEmptyView(mEmptyView);
        if (mEmptyView != null) mEmptyView.setOnReloadListener(this);
    }

    /**
     * 设置状态栏
     */
    protected void setSystemStatus() {
        StatusBarUtil.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    @LayoutRes
    public abstract int getContentViewId();

    @Override
    public void setTitle(CharSequence title) {
        getToolBarX().setTitle(title);
    }

    /**
     * 隐藏Loading
     */
    @Override
    public void hideProgress() {
        mRevealController.hideProgress();
    }

    /**
     * 显示一个Loading
     */
    @Override
    public void showProgress() {
        mRevealController.showProgress();
    }

    /**
     * 显示一个Loading
     *
     * @param message
     */
    @Override
    public void showProgress(String message) {
        mRevealController.showProgress(message);
    }

    @Override
    public void showTipToast(String message) {
        mRevealController.showTipToast(message);
    }

    @Override
    public void showToast(String message) {
        mRevealController.showToast(message);
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
    public void showMessageDialog(String message, String ok, DialogInterface.OnClickListener okClickListener) {
        mRevealController.showMessageDialog(message, ok, okClickListener);
    }

    @Override public void hideEmptyView() {
        mRevealController.hideEmptyView();
    }

    @Override
    public void showMessageDialog(String message, String okTip) {
        mRevealController.showMessageDialog(message, okTip);
    }

    @Override
    public void showEmptyView(@EmptyView.ViewState int state) {
        mRevealController.showEmptyView(state);
    }

    @Override
    public void onReload() {

    }

    /**
     * 返回一个Toolbar的操作类,如果有需要请扩展此类
     *
     * @return
     */
    public ToolBarX getToolBarX() {
        if (mToolBarX == null) {
            mToolBarX = new ToolBarX(mToolbar, this);
        }
        return mToolBarX;
    }
}
