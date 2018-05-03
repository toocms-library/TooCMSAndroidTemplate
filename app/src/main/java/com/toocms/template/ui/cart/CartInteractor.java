package com.toocms.template.ui.cart;

import com.toocms.template.modle.cart.Cart;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/16 11:30
 *
 * @version v2.0
 */

public interface CartInteractor {

    interface OnRequestFinishedListener {
        void onGetCartListFinished(Cart data);

        void onGetCartListError();

        void onEditCartNumFinished(String message);

        void onDeleteCartFinished(String message);
    }

    /**
     * 获取购物车列表
     *
     * @param member_id
     * @param region_id
     */
    void getCartList(String member_id, String region_id, OnRequestFinishedListener listener);

    /**
     * 修改购物车数量
     *
     * @param member_id
     * @param cart_id
     * @param number
     */
    void editCartNum(String member_id, String cart_id, int number, OnRequestFinishedListener listener);

    /**
     * 删除购物车商品
     *
     * @param member_id
     * @param cart_id
     */
    void deleteCart(String member_id, String cart_id, OnRequestFinishedListener listener);
}
