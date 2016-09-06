package com.martin.framework.module.splash.v;

import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.martin.framework.R;
import com.martin.framework.base.BaseCompatActivity;

import butterknife.BindView;

public class ImageActivity extends BaseCompatActivity {

    @BindView(R.id.image)
    ImageView mImageView;

    @Override protected void bindData() {

    }

    @Override protected void setSystemStatus() {
        StatusBarUtil.setTransparentForImageView(this,null);
    }

    @Override protected void bindEvent() {

    }

    @Override public int getContentViewId() {
        return R.layout.activity_image;
    }
}
