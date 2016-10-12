package com.martin.framework.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Desc:
 * Author:Martin
 * Date:2016/10/12
 */

public class StatusBarUtil {
    public static final int LIGHT = 0;
    public static final int DARK = 1;

    public static final String MIUI = "xiaomi";
    public static final String FLYME = "meizu";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {LIGHT, DARK})
    public @interface Mode {
    }

    public static boolean lightModeForMiuiV6(Activity activity) {
        boolean flag = false;
        Window window = activity.getWindow();
        Class clazz = window.getClass();
        try {
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class); //只需要状态栏透明
            extraFlagField.invoke(window, 0, darkModeFlag);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static void darkModeForMiuiV6(Activity activity) {
        Window window = activity.getWindow();
        Class clazz = window.getClass();
        try {
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class); //只需要状态栏透明
            extraFlagField.invoke(window, darkModeFlag, darkModeFlag); //状态栏透明且黑色字体
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean lightModeForFlymeV4(Activity activity) {
        boolean flag = false;
        Window window = activity.getWindow();
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                value &= ~bit;
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean darkModeForFlymeV4(Activity activity) {
        boolean flag = false;
        Window window = activity.getWindow();
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                value |= bit;
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void lightModeForAndroidSDK23(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int option = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }
        decorView.setSystemUiVisibility(option);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void darkModeForAndroidSDK23(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int option = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_VISIBLE;
        } else {
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }
        decorView.setSystemUiVisibility(option);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

    }

    public static void immersionModelForTarget19(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            activity.getWindow().setAttributes(localLayoutParams);
        }
    }

    @TargetApi(19)
    public static void statusMode(Activity activity, @Mode int mode) {
        try {
            String brand = Build.BRAND.toLowerCase();
            switch (mode) {
                case LIGHT:
                    switch (brand) {
                        case MIUI:
                            lightModeForMiuiV6(activity);
                        case FLYME:
                            lightModeForFlymeV4(activity);
                            immersionModelForTarget19(activity);
                            break;
                        default: {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                lightModeForAndroidSDK23(activity);
                            } else {
                                immersionModelForTarget19(activity);
                            }
                        }
                    }
                    break;
                case DARK:
                    switch (brand) {
                        case MIUI:
                            darkModeForMiuiV6(activity);
                        case FLYME:
                            darkModeForFlymeV4(activity);
                            immersionModelForTarget19(activity);
                            break;
                        default: {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                darkModeForAndroidSDK23(activity);
                            } else {
                                immersionModelForTarget19(activity);
                            }
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
