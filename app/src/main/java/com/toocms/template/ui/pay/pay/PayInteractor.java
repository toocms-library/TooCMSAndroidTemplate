package com.toocms.template.ui.pay.pay;

/**
 * Author：Zero
 * Date：2017/5/23 17:36
 *
 * @version v2.0
 */

public interface PayInteractor {

    interface OnRequestFinishListener {

        void onPaySuccess();

        void onNoBalance();
    }

    /**
     * 获取支付宝/微信的签名
     *
     * @param member_id
     * @param bill_sn
     * @param isAlipay
     */
    void getPaySign(String member_id, String bill_sn, boolean isAlipay);

    /**
     * 检测订单支付状态
     *
     * @param member_id
     * @param bill_sn
     * @param listener
     */
    void checkPayState(String member_id, String bill_sn, OnRequestFinishListener listener);

    /**
     * 余额支付
     *
     * @param member_id
     * @param bill_sn
     * @param password
     * @param listener
     */
    void toBalancePay(String member_id, String bill_sn, String password, OnRequestFinishListener listener);
}
