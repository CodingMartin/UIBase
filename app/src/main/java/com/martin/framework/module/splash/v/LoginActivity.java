package com.martin.framework.module.splash.v;

import com.martin.framework.R;
import com.martin.framework.base.BaseCompatActivity;
import com.martin.framework.module.splash.c.LoginContract;
import com.martin.framework.module.splash.p.LoginPresenter;
import com.martin.framework.utils.CheckUtil;

public class LoginActivity extends BaseCompatActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    @Override
    protected void bindData() {
        new LoginPresenter(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        CheckUtil.checkNotNull(presenter);
        this.mPresenter = presenter;
    }
}
