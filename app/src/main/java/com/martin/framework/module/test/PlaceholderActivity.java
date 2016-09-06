package com.martin.framework.module.test;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.martin.framework.R;
import com.martin.framework.base.BaseCompatActivity;
import com.martin.framework.view.EmptyView;

public class PlaceholderActivity extends BaseCompatActivity {


    @Override
    protected void bindData() {
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void bindView() {
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_placeholder;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_placeholder_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_empty:
                showEmptyView(EmptyView.STATE_NODATA);
                break;
            case R.id.menu_error:
                showEmptyView(EmptyView.STATE_ERROR);
                break;
            case R.id.menu_loading:
                showEmptyView(EmptyView.STATE_LOADING);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showEmptyView(EmptyView.STATE_NODATA);
                    }
                }, 800);
                break;
            case R.id.menu_nonet:
                showEmptyView(EmptyView.STATE_NONET);
                break;
            case R.id.menu_normal:
                showEmptyView(EmptyView.STATE_NORMAL);
                break;
        }
        return true;
    }
}
