package com.toocms.template.ui.coupon.coupons;

import com.toocms.template.modle.coupon.Coupon;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/23 12:44
 *
 * @version v2.0
 */

public interface CouponsInteractor {

    interface OnRequestFinishedListener {
        void onGetCouponListFinished(List<Coupon> list);
    }

    void getCouponList(String member_id, OnRequestFinishedListener listener);
}
