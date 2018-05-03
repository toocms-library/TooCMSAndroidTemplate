package com.toocms.template.ui.pay.pay;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.pay.Pay;
import com.toocms.pay.modle.PayRequest;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/5/24 11:07
 *
 * @version v2.0
 */

public class PayInteractorImpl implements PayInteractor {

    @Override
    public void getPaySign(String member_id, String bill_sn, boolean isAlipay) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("bill_sn", bill_sn);
        new ApiTool<TooCMSResponse<PayRequest>>().postApi("Pay/" + (isAlipay ? "aliPayment" : "wechatPayment"), params, new ApiListener<TooCMSResponse<PayRequest>>() {
            @Override
            public void onComplete(TooCMSResponse<PayRequest> data, Call call, Response response) {
                Pay.pay((PayAty) call.request().tag(), data.getData());
            }
        });
    }

    @Override
    public void checkPayState(String member_id, String bill_sn, final OnRequestFinishListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("bill_sn", bill_sn);
        new ApiTool<TooCMSResponse<Void>>().postApi("Pay/paymentCallback", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onPaySuccess();
            }
        });
    }

    @Override
    public void toBalancePay(String member_id, String bill_sn, String password, final OnRequestFinishListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("bill_sn", bill_sn);
        params.put("password", password);
        new ApiTool<TooCMSResponse<Void>>().postApi("Pay/balancePayment", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onPaySuccess();
            }

            @Override
            public void onError(String error, Call call, Response response) {
                // 如果message中包含提示余额不足代码的话则显示是否充值对话框
                if (error.contains("#1187107")) listener.onNoBalance();
                else super.onError(error, call, response);
            }
        });
    }
}
