package com.martin.framework.module.splash.v;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.ImageView;

import com.martin.framework.R;
import com.martin.framework.base.mvp.BaseIntricateActivity;
import com.martin.framework.module.home.v.MainActivity;
import com.martin.framework.module.splash.c.LoginContract;
import com.martin.framework.module.splash.p.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseIntricateActivity<LoginPresenter> implements LoginContract.View {

    @BindView(android.R.id.icon) ImageView mIcon;
    @BindView(R.id.til_account) TextInputLayout mTilAccount;
    @BindView(R.id.til_password) TextInputLayout mTilPassword;
    @BindView(R.id.btn_login) Button mBtnLogin;

    @Override
    protected void bindEvent() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login) public void onClick() {
        getPresenter().onLogin();
    }

    @Override public String getAccount() {
        if (mTilAccount.getEditText() != null) {
            return mTilAccount.getEditText().getText().toString();
        }
        return null;
    }

    @Override public String getPassword() {
        if (mTilPassword.getEditText() != null) {
            return mTilPassword.getEditText().getText().toString();
        }
        return null;
    }

    @Override public void actionHomePage() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @NonNull @Override public LoginPresenter bindPresenter() {
        return new LoginPresenter(this);
    }
}
