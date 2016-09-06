package com.martin.framework.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.martin.framework.R;
import com.martin.framework.common.RevealController;
import com.martin.framework.view.EmptyView;

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
    @BindView(R.id.toolbar_title_view) View mTitleView;
    @Nullable
    @BindView(R.id.id_empty_view) EmptyView mEmptyView;
    private boolean defineToolbarSelf;
    private RevealController mRevealController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetContentView();
        setContentView(getContentViewId());
        handleIntent(getIntent(), savedInstanceState);
        bindBase();
        setupTopBar();
        bindView();
        setSystemStatus();
        bindEvent();
        bindData();
    }

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
        StatusBarUtil.setColorNoTranslucent(this, ContextCompat.getColor(this,R.color.colorPrimaryDark));
    }

    /**
     * 数据绑定
     */
    protected abstract void bindData();

    /**
     * 事件绑定
     */
    protected abstract void bindEvent();

    /**
     * 初始化视图
     */
    protected void bindView() {

    }

    @LayoutRes
    public abstract int getContentViewId();


    /**
     * 如果需要完全由自己定义TopBar则重写这个方法并干掉super
     */
    protected void setupTopBar() {
        setupToolbar();
        setupTitle();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (mTitleView != null) {
            if (mTitleView instanceof TextView) {
                ((TextView) mTitleView).setText(title);
            }
        }
    }

    private void setupTitle() {
        if (mTitleView != null) {
            if (mTitleView instanceof TextView) {
                ((TextView) mTitleView).setText(getMainTitle());
            } else {
                inflateTitleView(mTitleView);
            }
        }
    }

    /**
     * 设置标题
     *
     * @return
     */
    protected CharSequence getMainTitle() {
        return getTitle();
    }

    /**
     * 解析标题控件(含Toolbar)
     */
    protected void inflateTitleView(View view) {
    }

    private void setupToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            ActionBar mActionBar = getSupportActionBar();
            if (mActionBar != null) {
                mActionBar.setDisplayShowTitleEnabled(isUseSystemUI());
            }

            View childView = mToolbar.getChildAt(0);
            if (childView == null) {
                useSystemToolbar();
            } else {
                ViewGroup.LayoutParams lp = childView.getLayoutParams();
                if (lp.width == ViewGroup.LayoutParams.MATCH_PARENT) { //这种情况认为toolbar仅仅最为一个容器
                    defineToolbarSelf = true;
                    inflateTopBar(childView);
                } else { //这种情况认为需要保留左右两边的操作
                    useSystemToolbar();
                }
            }
        }
    }

    private void useSystemToolbar() {
        defineToolbarSelf = false;
        if (hasBackButton() && mToolbar != null) {
            mToolbar.setNavigationIcon(R.mipmap.ic_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackClick(v);
                }
            });
        }
    }

    /**
     * 完全由自己定义TopBar (含Toolbar)
     */
    protected void inflateTopBar(View view) {

    }

    /**
     * 左边按钮的点击 默认关闭当前页面 (含Toolbar)
     */
    protected void onBackClick(View v) {
        finish();
    }

    /**
     * 是否使用系统的特性 (含Toolbar)
     * 1.title使用系统的还是用自己的
     */
    protected boolean isUseSystemUI() {
        return false;
    }

    /**
     * 是否存在返回按钮 (含Toolbar)
     */
    protected boolean hasBackButton() {
        return true;
    }

    /***
     * 如果不需要默认实现直接重写掉就好了。
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!defineToolbarSelf && !isUseSystemUI())
            getMenuInflater().inflate(R.menu.menu_simple_toolbar, menu);
        return (!defineToolbarSelf) || super.onCreateOptionsMenu(menu);
    }


    /***
     * 如果不需要默认实现直接重写掉就好了。
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!defineToolbarSelf && !isUseSystemUI()) {
            MenuItem itemMore = menu.findItem(R.id.id_menu_more);
            MenuItem itemSub = menu.findItem(R.id.id_menu_sub);
            if (!setupOptionsMenu(menu, itemMore, itemSub)) {
                defaultMenuOptionsMenu(menu, itemMore, itemSub);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /***
     * 如果不需要默认实现直接重写掉就好了。
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!defineToolbarSelf && !setupOptionsItemSelected(item) && !isUseSystemUI()) {
            switch (item.getItemId()) {
                case R.id.id_menu_more:
                    onMoreItemSelected(item);
                    break;
                case R.id.id_menu_sub:
                    onSubItemSelected(item);
                    break;
            }
        }
        return true;
    }

    protected void onSubItemSelected(@Nullable MenuItem item) {
        Toast.makeText(this, "右边第二个按钮被点击", Toast.LENGTH_SHORT).show();
    }

    protected void onMoreItemSelected(@Nullable MenuItem item) {
        Toast.makeText(this, "右边按钮被点击", Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义OptionItemSelected实现(含Toolbar)
     */
    protected boolean setupOptionsItemSelected(@Nullable MenuItem item) {
        return false;
    }

    /**
     * 默认自己定义Menu的样式和实现(含Toolbar)
     */
    private void defaultMenuOptionsMenu(Menu menu, @Nullable MenuItem itemMore, @Nullable MenuItem itemSub) {

    }

    /**
     * 自定义Menu的各种东西(含Toolbar)
     */
    protected boolean setupOptionsMenu(Menu menu, @Nullable MenuItem itemMore, @Nullable MenuItem itemSub) {
        return false;
    }

    @Nullable
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Nullable
    public View getTitleView() {
        return mTitleView;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU
                && event.getAction() == KeyEvent.ACTION_DOWN
                && !isUseSystemUI()) {
            onMoreItemSelected(null);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onReload() {

    }
}
