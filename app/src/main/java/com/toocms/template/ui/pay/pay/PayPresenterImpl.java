package com.toocms.template.ui.pay.pay;

import com.toocms.pay.Pay;
import com.toocms.pay.listener.PayStatusCallback;
import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.config.User;
import com.toocms.template.ui.order.confirmorder.ConfirmOrderAty;

import java.text.DecimalFormat;

import cn.zero.android.common.util.NumberUtils;
import cn.zero.android.common.util.StringUtils;

/**
 * Author：Zero
 * Date：2017/5/24 11:56
 *
 * @version v2.0
 */

public class PayPresenterImpl extends PayPresenter<PayView> implements PayInteractor.OnRequestFinishListener {

    private PayInteractor interactor;

    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    private User user; // 用户信息
    private String bill_sn; // 订单ID（或者是订单编号根据实际接口变换）
    private String from;
    private double payTotal; // 支付金额
    private double balance; // 余额
    private int payType = R.id.pay_wxpay; // 支付方式（默认为微信支付，也就是第一个布局当中的第一个）

    public PayPresenterImpl(String from, String bill_sn, double PayTotal) {
        this.from = from;
        this.bill_sn = bill_sn;
        this.payTotal = PayTotal;
        user = DataSet.getInstance().getUser();
        balance = NumberUtils.toDouble(user.getBalance());
        interactor = new PayInteractorImpl();
    }

    @Override
    void showPrices() {
        view.showPrices(decimalFormat.format(balance), decimalFormat.format(payTotal));
    }

    @Override
    void onClick() {
        switch (payType) {
            case R.id.pay_balpay:
                view.showInputPassword(decimalFormat.format(payTotal), bill_sn);
                break;
            case R.id.pay_alipay:
            case R.id.pay_wxpay:
                view.showProgress();
                interactor.getPaySign(user.getMember_id(), bill_sn, payType == R.id.pay_alipay);
                break;
        }
    }

    @Override
    void onPayTypeChanged(int checkedId, boolean isChecked) {
        if (isChecked) payType = checkedId;
    }

    @Override
    void checkPayState() {
        Pay.payStatusCallback(new PayStatusCallback() {
            @Override
            public void callback() {
                view.showProgress();
                interactor.checkPayState(user.getMember_id(), bill_sn, PayPresenterImpl.this);
            }
        });
    }

    @Override
    void toBalancePay(String member_id, String bill_sn, String password) {
        view.showProgress();
        interactor.toBalancePay(member_id, bill_sn, password, this);
    }

    @Override
    public void onPaySuccess() {
        balance -= payTotal;
        user.setBalance(decimalFormat.format(balance));
        if (StringUtils.equals(from, ConfirmOrderAty.class.getSimpleName())) {
            view.openOrderDetail(bill_sn);
        }
    }

    @Override
    public void onNoBalance() {
        view.showRecharge();
    }
}
