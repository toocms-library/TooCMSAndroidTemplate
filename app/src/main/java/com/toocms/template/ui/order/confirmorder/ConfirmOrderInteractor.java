package com.toocms.template.ui.order.confirmorder;

import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.order.ConfirmOrder;
import com.toocms.template.modle.order.CreateOrder;
import com.toocms.template.modle.order.SN;

/**
 * Author：Zero
 * Date：2017/5/19 16:28
 *
 * @version v2.0
 */

public interface ConfirmOrderInteractor {

    interface OnRequestFinishedListener {
        void onGetOrderInfoFinished(ConfirmOrder order);

        void onCreateOrderFinished(TooCMSResponse<SN> data);
    }

    void getOrderInfo(String member_id, String region_id, String cart_id, OnRequestFinishedListener listener);

    void createOrder(CreateOrder createOrder, OnRequestFinishedListener listener);
}
