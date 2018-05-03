package com.toocms.template.ui.order.confirmorder;

import android.content.Intent;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.modle.order.ConfirmOrder;
import com.toocms.template.modle.order.CreateOrder;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/22 9:20
 *
 * @version v2.0
 */

public abstract class ConfirmOrderPresenter<T> extends BasePresenter<T> {

    abstract void loadOrderInfo();

    abstract void onClick(int viewId, String note);

    abstract void selectDeliveryTimeItem(int position);

    abstract void result(int requestCode, int resultCode, Intent data);
}
