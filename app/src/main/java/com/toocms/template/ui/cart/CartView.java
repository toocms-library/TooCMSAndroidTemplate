package com.toocms.template.ui.cart;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.cart.Cart;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/16 11:29
 *
 * @version v2.0
 */

public interface CartView extends BaseView {

    /**
     * 显示购物车列表
     *
     * @param data
     */
    void showCartList(List<Cart.ListBean> data);

    /**
     * 显示顶部包邮提示
     *
     * @param no_freight
     */
    void showNotice(String no_freight);

    /**
     * 显示状态
     *
     * @param status
     */
    void showStatus(String status);

    /**
     * 设置去结算按钮的文字
     *
     * @param settle
     */
    void showSettle(String settle);

    /**
     * 显示运费
     *
     * @param isShowFreight
     * @param freight
     */
    void showFreight(boolean isShowFreight, String freight);

    /**
     * 显示总价
     *
     * @param isShowSettle
     * @param total
     */
    void showTotal(boolean isShowSettle, String total);

    /**
     * 根据全选状态显示复选框按钮的状态
     *
     * @param resId
     */
    void showIsAllSelected(int resId);

    /**
     * 设置是否显示空视图
     *
     * @param isShowEmptyView
     */
    void setIsShowEmptyView(boolean isShowEmptyView);

    /**
     * 跳转到确认订单页面
     */
    void openConfirmOrder(String list, double total);
}
