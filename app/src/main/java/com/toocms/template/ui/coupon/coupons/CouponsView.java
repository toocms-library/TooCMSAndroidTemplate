package com.toocms.template.ui.coupon.coupons;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.coupon.Coupon;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/23 12:42
 *
 * @version v2.0
 */

public interface CouponsView extends BaseView {

    void setTitle(String title);

    void setIsShowBottomBar(boolean isShow);

    void showCouponList(List<Coupon> list);

    void onSelectCoupon(Coupon coupon);

    void onNotUsedCoupon();
}
