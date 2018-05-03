package com.toocms.template.util;

import com.toocms.frame.tool.AppManager;

import cn.zero.android.common.util.PreferencesUtils;

/**
 * Author：Administrator
 * Date：2017/4/26 16:27
 */

public class CartUtils {

    /**
     * 购物车数量
     */
    public static final String CART_NUM = "CART_NUM";

    /**
     * 购物车角标数量增加
     *
     * @param num
     */
    public static final void plusCartFixedNum(int num) {
        int cart_num = PreferencesUtils.getInt(AppManager.getInstance().getTopActivity(), CART_NUM, 0);
        PreferencesUtils.putInt(AppManager.getInstance().getTopActivity(), CART_NUM, cart_num + num);
    }

    /**
     * 购物车角标数量减少
     *
     * @param num
     */
    public static final void reduceCartFixedNum(int num) {
        int cart_num = PreferencesUtils.getInt(AppManager.getInstance().getTopActivity(), CART_NUM, 0);
        if (cart_num == 0) return;
        PreferencesUtils.putInt(AppManager.getInstance().getTopActivity(), CART_NUM, cart_num - num);
    }

    /**
     * 获取购物车角标数量
     */
    public static final int getCartNum() {
        return PreferencesUtils.getInt(AppManager.getInstance().getTopActivity(), CART_NUM, 0);
    }

    /**
     * 设置购物车角标数量
     *
     * @param cartNum
     */
    public static final void setCartNum(int cartNum) {
        PreferencesUtils.putInt(AppManager.getInstance().getTopActivity(), CART_NUM, cartNum);
    }
}
