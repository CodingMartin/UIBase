package com.martin.framework.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.martin.framework.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/8
 */
public class ToolBarX implements View.OnClickListener {
    private Toolbar mToolbar;
    private AppCompatActivity mActivity;
    private ActionBar mActionBar;

    public static final int TITLE_OPTS_NORMAL = 0;
    public static final int TITLE_OPTS_CENTER = 1;
    public static final int TITLE_OPTS_NONE = 2;
    private TextView mTitleView;
    private int titleOptions;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TITLE_OPTS_CENTER, TITLE_OPTS_NONE, TITLE_OPTS_NORMAL})
    public @interface TitleOption {
    }

    public ToolBarX(@Nullable Toolbar toolbar, @NonNull AppCompatActivity activity) {
        if (toolbar == null) return;
        this.mToolbar = toolbar;
        this.mActivity = activity;
        mActivity.setSupportActionBar(mToolbar);
        mActionBar = mActivity.getSupportActionBar();
        setDisplayHomeAsUpEnabled(true); // 返回按钮
        setTitleOptions(TITLE_OPTS_CENTER);
        mToolbar.setNavigationOnClickListener(this);
    }


    @Override public void onClick(View v) {
        mActivity.finish();
    }


    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public ToolBarX setTitle(CharSequence title) {
        if (mToolbar == null) return this;
        findTitleView();
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
        mActionBar.setTitle(title);
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public ToolBarX setTitle(@StringRes int title) {
        return setTitle(mActivity.getString(title));
    }

    private void findTitleView() {
        if (mTitleView == null && titleOptions == TITLE_OPTS_CENTER) {
            mTitleView = (TextView) mToolbar.findViewById(R.id.toolbar_title_view);
            if (mTitleView == null)
                throw new IllegalStateException("请使用R.toolbar_simple_layout,或者标题设置为R.id.toolbar_title_view");
        }
    }

    /**
     * 控制Toolbar显示
     *
     * @return
     */
    public ToolBarX show() {
        if (mToolbar == null) return this;
        mActionBar.show();
        return this;
    }

    /**
     * 控制Toolbar隐藏
     *
     * @return
     */
    public ToolBarX hide() {
        if (mToolbar == null) return this;
        mActionBar.hide();
        return this;
    }

    /**
     * 隐藏返回按钮
     *
     * @return
     */
    public ToolBarX setDisplayHomeAsUpEnabled(boolean enable) {
        if (mToolbar == null) return this;
        mActionBar.setDisplayHomeAsUpEnabled(enable);
        return this;
    }

    /**
     * 设置标题的显示方式</br>
     * <li>{@link #TITLE_OPTS_CENTER} 居中显示并且Toolbar必须包含一个TextView且id为{@link com.martin.framework.R.id#toolbar_title_view} 可以选择使用{@link com.martin.framework.R.layout#toolbar_simple_layout}</li>
     * <li>{@link #TITLE_OPTS_NORMAL} 使用Toolbar的标题,显示在左边</li>
     * <li>{@link #TITLE_OPTS_NONE} 没有标题</li>
     *
     * @param opts
     * @return
     */
    public ToolBarX setTitleOptions(@TitleOption int opts) {
        if (mToolbar == null) return this;
        this.titleOptions = opts;
        //获取TextView
        switch (opts) {
            case TITLE_OPTS_NORMAL:
                if (mTitleView != null) mTitleView.setVisibility(View.GONE);
                mActionBar.setDisplayShowTitleEnabled(true);
                break;
            case TITLE_OPTS_NONE:
                if (mTitleView != null) mTitleView.setVisibility(View.GONE);
                mActionBar.setDisplayShowTitleEnabled(false);
                break;
            case TITLE_OPTS_CENTER:
                mActionBar.setDisplayShowTitleEnabled(false);
                break;
        }
        return this;
    }

    /**
     * 设置返回按钮的图标
     *
     * @param resId
     * @return
     */
    public ToolBarX setNavigationIcon(@DrawableRes int resId) {
        if (mToolbar == null) return this;
        mToolbar.setNavigationIcon(resId);
        return this;
    }

    /**
     * 设置返回按钮的点击事件,默认为关闭本活动
     *
     * @param listener
     * @return
     */
    public ToolBarX setNavigationOnClickListener(View.OnClickListener listener) {
        if (mToolbar == null) return this;
        mToolbar.setNavigationOnClickListener(listener);
        return this;
    }

    /**
     * 设置右边小三点的图标
     *
     * @param resId
     * @return
     */
    public ToolBarX setOverflowIcon(@DrawableRes int resId) {
        if (mToolbar == null) return this;
        Drawable drawable = ContextCompat.getDrawable(mActivity, resId);
        mToolbar.setOverflowIcon(drawable);
        return this;
    }

    /**
     * 设置自定义视图
     *
     * @param view
     * @param lp
     * @return
     */
    public ToolBarX setCustomView(View view, ActionBar.LayoutParams lp) {
        if (mToolbar == null) return this;
        if (lp == null) {
            mActionBar.setCustomView(view);
        } else {
            mActionBar.setCustomView(view, lp);
        }
        return this;
    }

    /**
     * 返回一个自定义视图
     *
     * @return
     */
    public View getCustomView() {
        if (mToolbar == null) return null;
        return mActionBar.getCustomView();
    }

}
