package com.martin.framework.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.martin.framework.R;
import com.martin.framework.base.BaseRevealView;
import com.martin.framework.view.PlaceholderPopWindow;
import com.martin.framework.view.PlaceholderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc: 各种显示理论上都在这里面进行
 * Author:Martin
 * Date:2016/8/15
 */
public class RevealController implements BaseRevealView {


    private final Context mContext;
    private ProgressDialog mProgressDialog;
    private AlertDialog mMessageDialog;
    private PlaceholderPopWindow.Builder builder;
    private PlaceholderPopWindow mPlaceholderPopWindow;

    public RevealController(Context context) {
        this.mContext = context;
    }

    @Override
    public void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void showProgress() {
        showProgress("正在加载...");
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTipToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelToast() {

    }

    @Override
    public void showMessageDialog(String message) {
        showMessageDialog(message, "确认");
    }

    @Override
    public void showMessageDialog(String message, String okTip) {
        showMessageDialog(message, okTip, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) dialog.dismiss();
            }
        });
    }

    @Override
    public void showMessageDialog(String message, String ok, DialogInterface.OnClickListener okClickListener) {
        mMessageDialog = new AlertDialog.Builder(mContext)
                .setMessage(message).setPositiveButton("确认", okClickListener).show();
    }

    @Override
    public void showPlaceholder(PlaceholderPopWindow.Builder builder, @PlaceholderView.ViewState int state) {
        builder = builder == null ? this.builder : builder;
        mPlaceholderPopWindow = builder.create().show(builder.anchor, state);
    }

    @Override
    public void showPlaceholder(@PlaceholderView.ViewState int state) {
        mPlaceholderPopWindow = this.builder.create().show(this.builder.anchor, state);
    }

    @CallSuper
    public void setPlaceHolderBuilder(PlaceholderPopWindow.Builder builder) {
        this.builder = builder;
    }

    public void handleOnBackPressed() {
        if (mPlaceholderPopWindow != null && mPlaceholderPopWindow.isShowing())
            mPlaceholderPopWindow.dismiss();
//        if (mMessageDialog != null && mMessageDialog.isShowing()) mMessageDialog.dismiss();
//        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.dismiss();
    }


    public ListPopupWindow createDefaultMenuPopup(@NonNull View anchorView, int[] icons, @NonNull @StringRes int[] titles) {
        ListPopupWindow mMenuPopupWindow = new ListPopupWindow(mContext);
        mMenuPopupWindow.createDragToOpenListener(anchorView);
        mMenuPopupWindow.setAnchorView(anchorView);
        mMenuPopupWindow.setHorizontalOffset(-50);
        mMenuPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mMenuPopupWindow.setContentWidth(manager.getDefaultDisplay().getWidth() / 2);
//        mMenuPopupWindow.setAnimationStyle(R.style.DropDownMenuAnimationStyle);
        mMenuPopupWindow.setDropDownGravity(Gravity.BOTTOM | Gravity.END);
        BaseAdapter adapter = null;
        if (icons != null && icons.length == titles.length) {
            List<Map<String, Object>> data = new ArrayList<>();
            for (int i = 0; i < titles.length; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("image", icons[i]);
                map.put("title", mContext.getString(titles[i]));
                data.add(map);
            }
            adapter = new SimpleAdapter(mContext, data, R.layout.item_simple_menu_item, new String[]{"image", "title"}, new int[]{android.R.id.icon, android.R.id.text1});
        } else {
            List<String> data = new ArrayList<>(titles.length);
            for (int i = 0; i < titles.length; i++) {
                String title = mContext.getString(titles[i]);
                data.add(title);
            }
            adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, data);
        }
        mMenuPopupWindow.setAdapter(adapter);
        return mMenuPopupWindow;
    }

}
