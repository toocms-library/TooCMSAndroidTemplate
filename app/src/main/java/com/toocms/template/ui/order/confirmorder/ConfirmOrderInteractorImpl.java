package com.toocms.template.ui.order.confirmorder;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.order.ConfirmOrder;
import com.toocms.template.modle.order.CreateOrder;
import com.toocms.template.modle.order.SN;

import org.xutils.common.util.LogUtil;

import java.lang.reflect.Field;

import cn.zero.android.common.util.ParamsUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/5/19 16:43
 *
 * @version v2.0
 */

public class ConfirmOrderInteractorImpl implements ConfirmOrderInteractor {

    @Override
    public void getOrderInfo(String member_id, String region_id, String cart_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("region_id", region_id);
        params.put("cart_id", cart_id);
        new ApiTool<TooCMSResponse<ConfirmOrder>>().postApi("Bill/billInfo", params, new ApiListener<TooCMSResponse<ConfirmOrder>>() {
            @Override
            public void onComplete(TooCMSResponse<ConfirmOrder> data, Call call, Response response) {
                listener.onGetOrderInfoFinished(data.getData());
            }
        });
    }

    @Override
    public void createOrder(CreateOrder createOrder, final OnRequestFinishedListener listener) {
        new ApiTool<TooCMSResponse<SN>>().postApi("Bill/toCreate", ParamsUtils.createHttpParams(createOrder), new ApiListener<TooCMSResponse<SN>>() {
            @Override
            public void onComplete(TooCMSResponse<SN> data, Call call, Response response) {
                listener.onCreateOrderFinished(data);
            }
        });
    }
}
