package com.toocms.template.ui.coupon.coupons;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.coupon.Coupon;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/5/23 12:46
 *
 * @version v2.0
 */

public class CouponsInteractorImpl implements CouponsInteractor {

    @Override
    public void getCouponList(String member_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        new ApiTool<TooCMSResponse<List<Coupon>>>().getApi("Coupon/index", params, new ApiListener<TooCMSResponse<List<Coupon>>>() {
            @Override
            public void onComplete(TooCMSResponse<List<Coupon>> data, Call call, Response response) {
                listener.onGetCouponListFinished(data.getData());
            }
        });
    }
}
