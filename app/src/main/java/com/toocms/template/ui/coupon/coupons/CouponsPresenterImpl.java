package com.toocms.template.ui.coupon.coupons;

import com.toocms.template.config.DataSet;
import com.toocms.template.modle.coupon.Coupon;
import com.toocms.template.ui.order.confirmorder.ConfirmOrderAty;

import java.util.List;

import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.util.NumberUtils;
import cn.zero.android.common.util.StringUtils;

/**
 * Author：Zero
 * Date：2017/5/23 12:54
 *
 * @version v2.0
 */

public class CouponsPresenterImpl extends CouponsPresenter<CouponsView> implements CouponsInteractor.OnRequestFinishedListener {

    private CouponsInteractor interactor;

    private List<Coupon> list; // 优惠券列表
    private String member_id; // 用户ID
    private double total; // 商品总价，用于判断优惠券是否可用
    private boolean isNeedResult; // 根据跳转页面判断是否需要回传数据

    public CouponsPresenterImpl(double total) {
        this.member_id = DataSet.getInstance().getUser().getMember_id();
        this.total = total;
        interactor = new CouponsInteractorImpl();
    }

    @Override
    void setFrom(String from) {
        isNeedResult = StringUtils.equals(from, ConfirmOrderAty.class.getSimpleName());
        view.setTitle(isNeedResult ? "使用优惠券" : "我的优惠券");
        // 根据页面需要判断是否显示底部栏
        if (!isNeedResult) view.setIsShowBottomBar(false);
    }

    @Override
    void loadCouponList(boolean isShowProgress) {
        if (isShowProgress) view.showProgress();
        interactor.getCouponList(member_id, this);
    }

    @Override
    void onCouponListItemClick(int position) {
        if (!isNeedResult) return;
        if (!StringUtils.equals(list.get(position).getUsed(), "0")) return;
        if (total < NumberUtils.toDouble(list.get(position).getPrice())) {
            view.showToast("未满足优惠券使用条件");
            return;
        }
        view.onSelectCoupon(list.get(position));
    }

    @Override
    void onNotUsedCouponClick() {
        view.onNotUsedCoupon();
    }

    @Override
    public void onGetCouponListFinished(List<Coupon> list) {
        this.list = list;
        view.setIsShowBottomBar(!ListUtils.isEmpty(list));
        view.showCouponList(list);
    }
}
