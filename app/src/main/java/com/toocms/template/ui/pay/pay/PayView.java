package com.toocms.template.ui.pay.pay;

import com.toocms.frame.ui.BaseView;

/**
 * Author：Zero
 * Date：2017/5/23 17:35
 *
 * @version v2.0
 */

public interface PayView extends BaseView {

    /**
     * 显示金额（余额、总价）
     */
    void showPrices(String balance, String payTotal);

    /**
     * 显示输入支付密码框
     */
    void showInputPassword(String payTotal, String bill_sn);

    /**
     * 支付成功，跳转到订单详情页
     */
    void openOrderDetail(String bill_sn);

    /**
     * 当余额支付失败时显示充值对话框
     */
    void showRecharge();
}
