package com.toocms.template.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.toocms.frame.ui.BaseActivity;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.zero.android.common.util.StatusBarUtil;

/**
 * 所有页面的基类，主要用于实现一些适用于该项目的通用方法
 *
 * @author Zero
 * @date 2016/6/23 16:32
 */
public abstract class BaseAty<V, T extends BasePresenter<V>> extends BaseActivity<V, T> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 改变通知栏颜色
        changeStatusBar();
    }

    // 更改状态栏颜色、文字颜色
    private void changeStatusBar() {
        if (Build.MANUFACTURER.equals("Xiaomi") || Build.MANUFACTURER.equals("Meizu") || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            StatusBarUtil.setColor(this, getResources().getColor(R.color.action_bg), 0);
        else
            StatusBarUtil.setColor(this, getResources().getColor(R.color.black), 255);
        if (Build.MANUFACTURER.equals("Xiaomi"))
            setMiuiStatusBarDarkMode(true);
        if (Build.MANUFACTURER.equals("Meizu"))
            setMeizuStatusBarDarkIcon(true);
    }

    private boolean setMiuiStatusBarDarkMode(boolean darkmode) {
        Class<? extends Window> clazz = getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setMeizuStatusBarDarkIcon(boolean dark) {
        try {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) value |= bit;
            else value &= ~bit;
            meizuFlags.setInt(lp, value);
            getWindow().setAttributes(lp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
