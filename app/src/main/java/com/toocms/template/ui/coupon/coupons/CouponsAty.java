package com.toocms.template.ui.coupon.coupons;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toocms.template.R;
import com.toocms.template.modle.coupon.Coupon;
import com.toocms.template.ui.BaseAty;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.view.CouponFrameView;
import cn.zero.android.common.view.swipetoloadlayout.OnRefreshListener;
import cn.zero.android.common.view.swipetoloadlayout.view.SwipeToLoadRecyclerView;
import cn.zero.android.common.view.swipetoloadlayout.view.listener.OnItemClickListener;

/**
 * 优惠券列表
 * <p>
 * 使用优惠券和我的优惠券的汇总页面，根据isNeedResult属性区分
 *
 * @author Zero
 * @date 2016/8/3 15:53
 */
public class CouponsAty extends BaseAty<CouponsView, CouponsPresenterImpl> implements OnRefreshListener, CouponsView {

    @BindView(R.id.order_coupon_bottom)
    LinearLayout linearLayout;
    @BindView(R.id.order_coupon_unused)
    CheckBox checkBox;
    @BindView(R.id.order_coupon_list)
    SwipeToLoadRecyclerView swipeToLoadRecyclerView;
    private CouponAdapter adapter;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        presenter.setFrom(getFrom());
        swipeToLoadRecyclerView.setEmptyView(findViewById(R.id.order_coupon_empty));
        swipeToLoadRecyclerView.setOnRefreshListener(this);
        swipeToLoadRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        swipeToLoadRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                presenter.onCouponListItemClick(i);
            }
        });
        adapter = new CouponAdapter();
        swipeToLoadRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_order_coupon;
    }

    @Override
    protected CouponsPresenterImpl getPresenter() {
        return new CouponsPresenterImpl(getIntent().getExtras().getDouble("total"));
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
        presenter.loadCouponList(true);
    }

    @OnClick(R.id.order_coupon_sure)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_coupon_sure:
                presenter.onNotUsedCouponClick();
                break;
        }
    }

    @Override
    public void onRefresh() {
        presenter.loadCouponList(false);
    }

    @Override
    public void setTitle(String title) {
        mActionBar.setTitle(title);
    }

    @Override
    public void setIsShowBottomBar(boolean isShow) {
        linearLayout.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showCouponList(List<Coupon> list) {
        adapter.replaceData(list);
        swipeToLoadRecyclerView.stopRefreshing();
    }

    @Override
    public void onSelectCoupon(Coupon coupon) {
        // 使用优惠券的对象回传
        Intent intent = getIntent();
        intent.putExtra("coupon", coupon);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onNotUsedCoupon() {
        if (checkBox.isChecked()) {
            setResult(RESULT_OK);
        }
        finish();
    }

    class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

        private List<Coupon> list;

        public void replaceData(List<Coupon> list) {
            setList(list);
            notifyDataSetChanged();
        }

        private void setList(List<Coupon> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CouponsAty.this).inflate(R.layout.listitem_coupon, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // 控制底部间距
            if (position == ListUtils.getSize(list) - 1) {
                holder.vOccupy.setVisibility(View.VISIBLE);
            } else {
                holder.vOccupy.setVisibility(View.GONE);
            }
            Coupon coupon = list.get(position);
            holder.tvMoney.setText(coupon.getPrice_sub());
            holder.tvLimit.setText("满" + coupon.getPrice() + "元可用");
            holder.tvName.setText(coupon.getGoods_type());
            holder.tvPay.setText(coupon.getPay_type());
            holder.tvEnd.setText("有效期至：" + coupon.getEnd_time());
            // 显示各种状态
            switch (coupon.getUsed()) {
                case "0": // 未使用
                    holder.couponFrameView.setBackgroundColor(Color.parseColor("#FF8237"));
                    holder.imgvFlag.setVisibility(View.GONE);
                    break;
                case "1": // 已使用
                    holder.couponFrameView.setBackgroundColor(Color.parseColor("#CCCCCC"));
                    holder.imgvFlag.setVisibility(View.VISIBLE);
                    holder.imgvFlag.setImageResource(R.drawable.ic_coupon_used);
                    break;
                case "2": // 已过期
                    holder.couponFrameView.setBackgroundColor(Color.parseColor("#CCCCCC"));
                    holder.imgvFlag.setVisibility(View.VISIBLE);
                    holder.imgvFlag.setImageResource(R.drawable.ic_coupon_expired);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return ListUtils.getSize(list);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.listitem_coupon)
            CouponFrameView couponFrameView;
            @BindView(R.id.listitem_coupon_flag)
            ImageView imgvFlag;
            @BindView(R.id.listitem_coupon_money)
            TextView tvMoney;
            @BindView(R.id.listitem_coupon_limit)
            TextView tvLimit;
            @BindView(R.id.listitem_coupon_name)
            TextView tvName;
            @BindView(R.id.listitem_coupon_pay)
            TextView tvPay;
            @BindView(R.id.listitem_coupon_end)
            TextView tvEnd;
            @BindView(R.id.listitem_coupon_occupy)
            View vOccupy;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                AutoUtils.auto(itemView);
            }
        }
    }
}
