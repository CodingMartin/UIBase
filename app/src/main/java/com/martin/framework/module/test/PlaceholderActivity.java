package com.martin.framework.module.test;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.martin.framework.R;
import com.martin.framework.base.BaseCompatActivity;
import com.martin.framework.view.PlaceholderPopWindow;
import com.martin.framework.view.PlaceholderView;

public class PlaceholderActivity extends BaseCompatActivity {

    private PlaceholderPopWindow window;

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
                showPlaceholder(PlaceholderView.STATE_NODATA);
                break;
            case R.id.menu_error:
                showPlaceholder(PlaceholderView.STATE_ERROR);
                break;
            case R.id.menu_loading:
                showPlaceholder(PlaceholderView.STATE_LOADING);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPlaceholder(PlaceholderView.STATE_NODATA);
                    }
                }, 800);
                break;
            case R.id.menu_nonet:
                showPlaceholder(PlaceholderView.STATE_NONET);
                break;
            case R.id.menu_normal:
                showPlaceholder(PlaceholderView.STATE_NORMAL);
                break;
        }
        return true;
    }
}
