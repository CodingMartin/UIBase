package com.martin.framework.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/17
 */
public class PlaceholderPopWindow extends PopupWindow implements PopupWindow.OnDismissListener {

    private PlaceholderView mPlaceholderView;

    private PlaceholderPopWindow() {
    }

    private PlaceholderPopWindow(View contentView) {
        super(contentView);
    }

    private PlaceholderPopWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    private PlaceholderPopWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    private PlaceholderPopWindow(Context context) {
        super(context);
        mPlaceholderView = new PlaceholderView(context);
        setContentView(mPlaceholderView);
        setOnDismissListener(this);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
//        this.setFocusable(true);
//        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);

        setAnimationStyle(0);
    }

    public PlaceholderPopWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlaceholderPopWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlaceholderPopWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public PlaceholderPopWindow(int width, int height) {
        super(width, height);
    }

    public PlaceholderPopWindow show(View anchor, @PlaceholderView.ViewState int state) {
        mPlaceholderView.show(state);
        if (!isShowing()) {
            showAsDropDown(anchor);
        }
        return this;
    }

    public PlaceholderPopWindow showAtLocation(View parent, @PlaceholderView.ViewState int state) {
        mPlaceholderView.show(state);
        if (!isShowing()) {
            showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
        } else {
            update();
        }
        return this;
    }

    public void setOnReloadListener(PlaceholderView.OnReloadListener listener) {
        mPlaceholderView.setOnReloadListener(listener);
    }

    public void setEmptyTip(@StringRes int emptyTip) {
        mPlaceholderView.setEmptyTip(emptyTip);
    }

    public void setErrorTip(@StringRes int errorTip) {
        mPlaceholderView.setErrorTip(errorTip);
    }

    public void setLoadingTip(@StringRes int loadingTip) {
        mPlaceholderView.setLoadingTip(loadingTip);
    }

    public void anchor(View anchor) {
    }

    public void setNoDataIcon(@DrawableRes int noDataIcon) {
        mPlaceholderView.setNoDataIcon(noDataIcon);
    }

    public void setNoNetIcon(@DrawableRes int noNetIcon) {
        mPlaceholderView.setNoNetIcon(noNetIcon);
    }

    public void setNoNetTip(@StringRes int noNetTip) {
        mPlaceholderView.setNoNetTip(noNetTip);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mPlaceholderView != null) {
            mPlaceholderView.dismss();
        }
    }

    @Override
    public void onDismiss() {
        if (mPlaceholderView != null) {
            mPlaceholderView.dismss();
        }
    }

    @PlaceholderView.ViewState
    public int getViewState() {
        if (mPlaceholderView != null) return mPlaceholderView.getViewState();
        return PlaceholderView.STATE_NORMAL;
    }

    public static class Builder {
        public Context mContext;
        public int noDataIcon = -1;
        public int noNetIcon = -1;
        public int errorTip = -1;
        public int emptyTip = -1;
        public int loadingTip = -1;
        public int noNetTip = -1;
        //是否全屏显示
        public boolean fullScreen = false;
        //参数是否有改变
        private boolean hasChanged = false;
        /**
         * 重新加载监听
         */
        public PlaceholderView.OnReloadListener listener = null;

        private PlaceholderPopWindow mPlaceholderWindow = null;
        public View anchor = null;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setEmptyTip(@StringRes int emptyTip) {
            if (this.emptyTip != emptyTip)
                hasChanged = true;
            else return this;
            this.emptyTip = emptyTip;
            return this;
        }

        public Builder setErrorTip(@StringRes int errorTip) {
            if (this.errorTip != errorTip)
                hasChanged = true;
            else return this;
            this.errorTip = errorTip;
            return this;
        }

        public Builder setListener(PlaceholderView.OnReloadListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setLoadingTip(@StringRes int loadingTip) {
            if (this.loadingTip != loadingTip)
                hasChanged = true;
            else return this;
            this.loadingTip = loadingTip;
            return this;
        }

        public Builder anchor(View anchor) {
            if (this.anchor != anchor) hasChanged = true;
            else return this;
            if (anchor != null) fullScreen = false;
            else fullScreen = true;
            this.anchor = anchor;
            return this;
        }

        public Builder setNoDataIcon(@DrawableRes int noDataIcon) {
            if (this.noDataIcon != noDataIcon)
                hasChanged = true;
            else return this;
            this.noDataIcon = noDataIcon;
            return this;
        }

        public Builder setNoNetIcon(@DrawableRes int noNetIcon) {
            if (this.noNetIcon != noNetIcon)
                hasChanged = true;
            else return this;
            this.noNetIcon = noNetIcon;
            return this;
        }

        public Builder setNoNetTip(@StringRes int noNetTip) {
            if (this.noNetTip != noNetTip)
                hasChanged = true;
            else return this;
            this.noNetTip = noNetTip;
            return this;
        }

        public PlaceholderPopWindow create() {
            if (hasChanged) {
                mPlaceholderWindow = createPlaceholderWindow();
            } else {
                return mPlaceholderWindow == null ? createPlaceholderWindow() : mPlaceholderWindow;
            }
            hasChanged = false;
            mPlaceholderWindow.setOnReloadListener(listener);
            return mPlaceholderWindow;
        }

        @NonNull
        private PlaceholderPopWindow createPlaceholderWindow() {
            PlaceholderPopWindow window = new PlaceholderPopWindow(mContext);
            window.setEmptyTip(emptyTip);
            window.setErrorTip(errorTip);
            window.setLoadingTip(loadingTip);
            window.setNoNetTip(noNetTip);
            window.setNoDataIcon(noDataIcon);
            window.setNoNetIcon(noNetIcon);
            return window;
        }
    }

}
