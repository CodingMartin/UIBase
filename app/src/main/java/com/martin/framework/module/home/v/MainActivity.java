package com.martin.framework.module.home.v;

import android.content.Intent;
import android.view.Menu;
import android.view.View;

import com.martin.framework.R;
import com.martin.framework.base.BaseCompatActivity;
import com.martin.framework.module.home.c.MainContract;
import com.martin.framework.module.home.p.MainPresenter;
import com.martin.framework.module.splash.v.ImageActivity;
import com.martin.framework.module.splash.v.LoginActivity;
import com.martin.framework.module.test.PlaceholderActivity;

import butterknife.OnClick;

public class MainActivity extends BaseCompatActivity implements MainContract.View {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化视图
     */
    @Override
    protected void bindView() {
        getToolBarX().setTitle("麦圈").setDisplayHomeAsUpEnabled(false);
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

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more_toolbar, menu);
        return true;
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

    @OnClick(R.id.btn_picture)
    public void goPicture() {
        startActivity(new Intent(this, ImageActivity.class));
    }
}
