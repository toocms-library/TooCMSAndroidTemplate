package com.toocms.template.ui.pay.pay;

import com.toocms.frame.ui.BasePresenter;

/**
 * Author：Zero
 * Date：2017/5/24 11:24
 *
 * @version v2.0
 */

public abstract class PayPresenter<T> extends BasePresenter<T> {

    /**
     * 显示金额（余额、总价）
     */
    abstract void showPrices();

    abstract void onClick();

    /**
     * 改变支付方式
     *
     * @param checkedId
     */
    abstract void onPayTypeChanged(int checkedId, boolean isChecked);

    /**
     * 检测订单支付状态
     */
    abstract void checkPayState();

    /**
     * 余额支付
     *
     * @param member_id
     * @param bill_sn
     * @param password
     */
    abstract void toBalancePay(String member_id, String bill_sn, String password);
}
