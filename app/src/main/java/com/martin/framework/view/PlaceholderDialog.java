package com.martin.framework.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/17
 */
public class PlaceholderDialog extends DialogFragment {

    private Context mContext;
    private PlaceholderView mPlaceholderView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPlaceholderView = new PlaceholderView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPlaceholderView.setLayoutParams(layoutParams);
        return mPlaceholderView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Dialog dialog = getDialog();
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.verticalMargin = 300;
////        lp.y
//        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public PlaceholderDialog show(View anchor, @PlaceholderView.ViewState int state) {
        mPlaceholderView.show(state);
        return this;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPlaceholderView.show(PlaceholderView.STATE_LOADING);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public void setOnReloadListener(PlaceholderView.OnReloadListener listener) {
        mPlaceholderView.setOnReloadListener(listener);
    }


}
