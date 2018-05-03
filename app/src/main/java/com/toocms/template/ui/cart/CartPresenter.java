package com.toocms.template.ui.cart;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.modle.cart.Cart;

/**
 * Author：Zero
 * Date：2017/5/16 11:31
 *
 * @version v2.0
 */

public abstract class CartPresenter<T> extends BasePresenter<T> {

    /**
     * 正常/编辑状态改变
     */
    abstract void onStateChange(boolean isReset);

    /**
     * 加载购物车列表
     *
     * @param region_id
     * @param isShowProgress
     */
    abstract void loadCartList(String region_id, boolean isShowProgress);

    /**
     * 按钮点击事件
     *
     * @param viewId
     */
    abstract void onClick(int viewId);

    /**
     * 改变购物车项的选中状态
     *
     * @param cart
     * @param isSelected
     */
    abstract void changeSelected(Cart.ListBean cart, boolean isSelected);

    /**
     * 修改购物车商品数量
     *
     * @param cart
     * @param isPlus
     */
    abstract void editNumber(Cart.ListBean cart, boolean isPlus);
}
