package com.toocms.template.ui.order.confirmorder;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.order.ConfirmOrder;
import com.toocms.template.modle.order.SN;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/19 16:28
 *
 * @version v2.0
 */

public interface ConfirmOrderView extends BaseView {

    void showAddress(ConfirmOrder.AddressBean address);

    void showEmptyAddress();

    void showDeliveryTimeList(List<String> delivery_time, List<Boolean> isSelected);

    void showCommodityList(List<ConfirmOrder.GoodsBean> list);

    void showFreightAndTotal(boolean isShow, String freight_price, String total);

    void openAddresses();

    void openCoupons(double total);

    void openPayment(double payTotal, SN sn);

    void showDeliveryTime(String deliveryTime);

    void setIsUseCoupon(boolean isUsed, String coupon_money);

    void showPayTotal(String payTotal);

    void setIsShowDeliveryTime(boolean isShow);
}
