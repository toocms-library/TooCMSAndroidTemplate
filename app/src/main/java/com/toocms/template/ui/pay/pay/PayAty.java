package com.toocms.template.ui.pay.pay;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.ui.BaseAty;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cn.zero.android.common.view.payui.PayPwdView;
import cn.zero.android.common.view.payui.PayUI;

/**
 * 支付方式选择页
 *
 * @author Zero
 * @date 2016/8/4 14:40
 */
public class PayAty extends BaseAty<PayView, PayPresenterImpl> implements PayView {

    @BindView(R.id.pay_balance)
    TextView tvBalance;
    @BindView(R.id.pay_total)
    TextView tvTotal;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("付款");
        presenter.showPrices();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.checkPayState();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_pay;
    }

    @Override
    protected PayPresenterImpl getPresenter() {
        return new PayPresenterImpl(getFrom(),
                getIntent().getExtras().getString("bill_sn"),
                getIntent().getExtras().getDouble("payTotal"));
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
    }

    @OnClick(R.id.pay)
    public void onClick(View v) {
        presenter.onClick();
    }

    @OnCheckedChanged({R.id.pay_wxpay, R.id.pay_alipay, R.id.pay_balpay})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        presenter.onPayTypeChanged(buttonView.getId(), isChecked);
    }

    @Override
    public void showPrices(String balance, String payTotal) {
        tvBalance.setText(balance);
        tvTotal.setText("￥" + payTotal);
    }

    @Override
    public void showInputPassword(String payTotal, final String bill_sn) {
        PayUI.showPayUI("支付：￥" + payTotal, new PayPwdView.InputCallBack() {
            @Override
            public void onInputFinish(String s) {
                presenter.toBalancePay(DataSet.getInstance().getUser().getMember_id(), bill_sn, s);
            }
        });
    }

    @Override
    public void openOrderDetail(String bill_sn) {
        Bundle bundle = new Bundle();
        bundle.putString("bill_sn", bill_sn);
//            startActivity(OrderDetailAty.class, bundle);
        finish();
    }

    @Override
    public void showRecharge() {
        showDialog("提示", "您的余额不足是否去充值", "确定", "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                startActivity(RechargeAty.class, null);
            }
        }, null);
    }
}
