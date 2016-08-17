package com.martin.framework.module.home.v;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.ListPopupWindow;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.martin.framework.R;
import com.martin.framework.base.BaseCompatActivity;
import com.martin.framework.module.home.c.MainContract;
import com.martin.framework.module.home.p.MainPresenter;
import com.martin.framework.module.splash.v.LoginActivity;
import com.martin.framework.module.test.PlaceholderActivity;
import com.martin.framework.utils.CheckUtil;
import com.martin.framework.view.PlaceholderPopWindow;

public class MainActivity extends BaseCompatActivity implements MainContract.View {

    private ListPopupWindow mListPopupWindow;
    private MainContract.Presenter mPresenter;
    private PlaceholderPopWindow window;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {

    }

    /**
     * 事件绑定
     */
    @Override
    protected void bindEvent() {

    }

    /**
     * 数据绑定
     */
    @Override
    protected void bindData() {
        new MainPresenter(this);
    }

    @Override
    protected boolean isUseSystemUI() {
        return true;
    }

    @Override
    protected void onMoreItemSelected(MenuItem item) {
        if (mListPopupWindow == null) {
            mListPopupWindow = createDefaultMenuPopup(getToolbar(), new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher}, new int[]{R.string.app_name, R.string.app_name, R.string.app_name});
        }
        if (mListPopupWindow.isShowing()) {
            mListPopupWindow.dismiss();
        } else {
            mListPopupWindow.show();
        }

    }

    @Override
    protected boolean setupOptionsMenu(Menu menu, @Nullable MenuItem itemMore, @Nullable MenuItem itemSub) {
        return super.setupOptionsMenu(menu, itemMore, itemSub);
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        CheckUtil.checkNotNull(presenter);
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void showToast(View view) {
        showToast("Hello world");
    }

    public void showProgress(View view) {
        showProgress();
    }

    public void showMessageDialog(View view) {
        showMessageDialog("Hello World");
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void showPop(View view) {
       startActivity(new Intent(this, PlaceholderActivity.class));
    }


}
