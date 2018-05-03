package com.toocms.template.ui.order.confirmorder;

import android.app.Activity;
import android.content.Intent;

import com.toocms.frame.config.WeApplication;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.config.ProjectCache;
import com.toocms.template.config.User;
import com.toocms.template.modle.coupon.Coupon;
import com.toocms.template.modle.order.ConfirmOrder;
import com.toocms.template.modle.order.CreateOrder;
import com.toocms.template.modle.order.SN;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.util.NumberUtils;
import cn.zero.android.common.util.StringUtils;

/**
 * Author：Zero
 * Date：2017/5/22 9:42
 *
 * @version v2.0
 */

public class ConfirmOrderPresenterImpl extends ConfirmOrderPresenter<ConfirmOrderView> implements ConfirmOrderInteractor.OnRequestFinishedListener {

    private ConfirmOrderInteractor interactor;

    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    private String member_id; // 用户ID
    private String cart_ids; // 所有商品购物车id
    private String address_id; // 选择的用户地址的ID
    private String coupon_id; // 选择的优惠券ID
    private String delivery_time; // 配送时间
    private double total; // 总价
    private double freight_price; // 实际运费
    private double coupon_money; // 优惠券金额
    private double payTotal; // 需要支付的金额
    private boolean isOpen; // 控制配送时间是否显示

    // 配送时间
    private List<String> listDeliveryTime; // 配送时间列表
    private List<Boolean> isSelected;
    private int lastPosition; // 上次点击的位置

    public ConfirmOrderPresenterImpl(String member_id, String cart_ids, double total) {
        this.member_id = member_id;
        this.cart_ids = cart_ids;
        this.payTotal = this.total = total;
        interactor = new ConfirmOrderInteractorImpl();
    }

    @Override
    void loadOrderInfo() {
        view.showProgress();
        interactor.getOrderInfo(member_id, ProjectCache.getCityId(), cart_ids, this);
    }

    @Override
    void onClick(int viewId, String note) {
        switch (viewId) {
            case R.id.confirm_order_address_click: // 配送地址
                view.openAddresses();
                break;
            case R.id.confirm_order_delivery_time_click: // 配送时间
                isOpen = !isOpen;
                view.setIsShowDeliveryTime(isOpen);
                break;
            case R.id.confirm_order_coupon_click: // 优惠券
                view.openCoupons(total);
                break;
            case R.id.confirm_order_submit: // 提交订单
                // 检测用户是否选择收获地址
                if (StringUtils.isEmpty(address_id)) {
                    view.showToast("请选择收货地址");
                    return;
                }
                // 构建提交订单参数类
                CreateOrder createOrder = new CreateOrder();
                createOrder.setCart_id(cart_ids);
                createOrder.setAddress_id(address_id);
                createOrder.setAmount(String.valueOf(total));
                createOrder.setCoupon_id(coupon_id);
                createOrder.setFreight_price(String.valueOf(freight_price));
                createOrder.setFreight_time(delivery_time);
                createOrder.setInformation(note);
                createOrder.setMember_id(member_id);
                createOrder.setRegion_id(ProjectCache.getCityId());
                // 调用创建订单接口
                view.showProgress();
                interactor.createOrder(createOrder, this);
                break;
        }
    }

    @Override
    void selectDeliveryTimeItem(int position) {
        // 本次和上次点击的item是同一个的话直接return
        if (lastPosition == position) return;
        // 设置选择的配送时间
        delivery_time = listDeliveryTime.get(position);
        view.showDeliveryTime(delivery_time);
        // 设置上一次点击的item为未选中状态，本次点击的为选中状态，之后通知数据改变刷新listview
        isSelected.set(lastPosition, false);
        isSelected.set(position, true);
        lastPosition = position;
        view.showDeliveryTimeList(listDeliveryTime, isSelected);
        // 隐藏配送时间列表
        isOpen = !isOpen;
        view.setIsShowDeliveryTime(isOpen);
    }

    @Override
    void result(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case ConfirmOrderAty.REQUEST_ADDRESS:
                if (null != data) {
                    ConfirmOrder.AddressBean address = (ConfirmOrder.AddressBean) data.getSerializableExtra("address");
                    address_id = address.getId();
                    view.showAddress(address);
                }
                break;
            case ConfirmOrderAty.REQUEST_COUPON:
                // 判断一下用户是否选择使用了优惠券
                if (null != data) {
                    // 获取选择优惠券的ID和金额
                    Coupon coupon = (Coupon) data.getSerializableExtra("coupon");
                    coupon_id = coupon.getCoupon_id();
                    coupon_money = NumberUtils.toDouble(coupon.getPrice_sub());
                    view.setIsUseCoupon(true, decimalFormat.format(coupon_money));
                } else {
                    view.setIsUseCoupon(false, "未使用");
                    coupon_money = 0;
                }
                // 最后刷新一下实付款的UI
                showPayTotal();
                break;
        }
    }

    @Override
    public void onGetOrderInfoFinished(ConfirmOrder order) {
        // 根据返回的地址是否为空判断显示
        if (!StringUtils.isEmpty(order.getAddress().getCity_id())) {
            address_id = order.getAddress().getId();
            view.showAddress(order.getAddress());
        } else view.showEmptyAddress();
        // 显示配送时间列表、商品列表、运费以及总价
        listDeliveryTime = order.getFreight_time();
        // 初始化选中标识
        isSelected = new ArrayList<>();
        for (int i = 0; i < ListUtils.getSize(listDeliveryTime); i++) {
            isSelected.add(i == 0);
        }
        // 设置配送时间列表
        view.showDeliveryTimeList(order.getFreight_time(), isSelected);
        // 设置配送时间列表隐藏
        view.setIsShowDeliveryTime(isOpen);
        // 显示商品列表
        view.showCommodityList(order.getGoods());
        // 计算出运费，并判断运费结算项是否显示
        freight_price = (total >= NumberUtils.toDouble(order.getNo_freight())) ? 0 : NumberUtils.toDouble(order.getDef_freight());
        // 更新余额
        User user = DataSet.getInstance().getUser();
        user.setBalance(order.getBalance());
        WeApplication.getInstance().setUserInfo(user);
        view.showFreightAndTotal(freight_price == 0,
                decimalFormat.format(freight_price),
                decimalFormat.format(total));
        // 显示实付款
        showPayTotal();
    }

    @Override
    public void onCreateOrderFinished(TooCMSResponse<SN> data) {
        // 创建订单成功，跳转到付款页面
        view.showToast(data.getMessage());
        view.openPayment(payTotal, data.getData());
    }


    /**
     * 计算并显示实付款
     */
    private void showPayTotal() {
        payTotal = total + freight_price - coupon_money;
        view.showPayTotal(decimalFormat.format(payTotal));
    }
}
