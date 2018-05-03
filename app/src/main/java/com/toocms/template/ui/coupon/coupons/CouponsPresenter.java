package com.toocms.template.ui.coupon.coupons;

import com.toocms.frame.ui.BasePresenter;

/**
 * Author：Zero
 * Date：2017/5/23 12:50
 *
 * @version v2.0
 */

public abstract class CouponsPresenter<T> extends BasePresenter<T> {

    abstract void setFrom(String from);

    abstract void loadCouponList(boolean isShowProgress);

    abstract void onCouponListItemClick(int position);

    abstract void onNotUsedCouponClick();
}
