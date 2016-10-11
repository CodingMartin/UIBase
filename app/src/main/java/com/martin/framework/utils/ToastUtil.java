package com.martin.framework.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.martin.framework.R;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * Author:Martin
 * Date:2016/10/10
 */

public class ToastUtil {
    private Toast sToast;
    private WeakReference<View> mView;
    private WeakReference<Context> mContext;
    private static ToastUtil sInstance;

    private ToastUtil(Context context) {
        setContext(context);
        initToast();
    }

    private static ToastUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ToastUtil(context);
        }
        return sInstance;
    }


    public static void show(Context context, String msg, int gravity) {
        getInstance(context)._show(context, msg, gravity);
    }

    public static void show(Context context, String msg) {
        getInstance(context)._show(context, msg, -1);
    }

    public static void show(Context context, @StringRes int msg) {
        getInstance(context)._show(context, context.getString(msg), -1);
    }

    public static void show(Context context, @StringRes int msg, int gravity) {
        getInstance(context)._show(context, context.getString(msg), gravity);
    }


    public static void custom(Context context, @NonNull String msg, @DrawableRes int icon) {
        getInstance(context)._show(context, msg, icon, Gravity.CENTER);
    }

    public static void custom(Context context, @StringRes int msg, @DrawableRes int icon) {
        getInstance(context)._show(context, context.getString(msg), icon, Gravity.CENTER);
    }

    private void _show(Context context, String msg, int gravity) {
        setContext(context);
        initToast();
        if (sToast == null) return;
        if (gravity != -1)
            sToast.setGravity(gravity, 0, 0);
        sToast.setText(msg);
        sToast.show();
    }

    private void _show(Context context, @NonNull String msg, @DrawableRes int icon, int gravity) {
        setContext(context);
        initToast();
        if (sToast == null) return;
        if (gravity != -1)
            sToast.setGravity(gravity, 0, 0);
        initView();
        View view = mView.get();
        if (view == null) return;
        TextView textView = (TextView) view.findViewById(R.id.toast_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
        textView.setText(msg);
        imageView.setImageResource(icon);
        sToast.setView(view);
        sToast.show();
    }

    private void initToast() {
        Context context = getContext();
        if (context != null) {
            sToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
    }

    private void initView() {
        Context context = getContext();
        View view = getView();
        if (view == null && context != null) {
            view = View.inflate(context, R.layout.layout_toast, null);
            setView(view);
        }
    }

    private void setContext(Context context) {
        mContext = context != null ? new WeakReference<>(context) : null;
    }

    private void setView(View view) {
        mView = view != null ? new WeakReference<>(view) : null;
    }

    private View getView() {
        return mView != null ? mView.get() : null;
    }

    private Context getContext() {
        return mContext != null ? mContext.get() : null;
    }
}
