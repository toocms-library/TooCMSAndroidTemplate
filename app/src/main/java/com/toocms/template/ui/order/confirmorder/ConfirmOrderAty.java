package com.toocms.template.ui.order.confirmorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toocms.frame.image.ImageLoader;
import com.toocms.frame.tool.Commonly;
import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.modle.order.ConfirmOrder;
import com.toocms.template.modle.order.SN;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.address.addresses.AddressesAty;
import com.toocms.template.ui.coupon.coupons.CouponsAty;
import com.toocms.template.ui.pay.pay.PayAty;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.view.linearlistview.LinearListView;

/**
 * 确认订单
 *
 * @author Zero
 * @date 2016/7/27 12:06
 */
public class ConfirmOrderAty extends BaseAty<ConfirmOrderView, ConfirmOrderPresenterImpl> implements ConfirmOrderView {

    public static final int REQUEST_ADDRESS = 0x987;
    public static final int REQUEST_COUPON = 0x654;

    // 选项
    @BindView(R.id.confirm_order_address_content)
    LinearLayout linlayAddress;
    @BindView(R.id.confirm_order_address_create)
    TextView tvCreateAddress;
    @BindView(R.id.confirm_order_name_and_phone)
    TextView tvNameAndPhone;
    @BindView(R.id.confirm_order_address_detail)
    TextView tvAddressDetail;
    @BindView(R.id.confirm_order_delivery_time)
    TextView tvDeliveryTime;
    @BindView(R.id.confirm_order_pay_type)
    TextView tvPayType;
    @BindView(R.id.confirm_order_statistics_coupon_item)
    LinearLayout linlayStatisticsCoupon;
    @BindView(R.id.confirm_order_freight_price_item)
    LinearLayout linlayFreightPrice;
    @BindView(R.id.confirm_order_note)
    EditText etxtNote;
    @BindView(R.id.confirm_order_coupon_price)
    TextView tvCoupon;
    @BindView(R.id.confirm_order_total)
    TextView tvTotal;
    @BindView(R.id.confirm_order_freight_price)
    TextView tvFreight;

    // 列表
    @BindView(R.id.confirm_order_delivery_time_list)
    LinearListView listViewDeliveryTime;
    DeliveryTimeAdapter deliveryTimeAdapter;
    @BindView(R.id.confirm_order_list)
    LinearListView listViewCommidity;
    CommodityAdapter commodityAdapter;

    // 统计
    @BindView(R.id.confirm_order_statistics_total)
    TextView tvStatisticsTotal;
    @BindView(R.id.confirm_order_statistics_coupon)
    TextView tvStatisticsCoupon;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        //        str.replaceAll("\\[([^\\]]*)\\]", "$1").replaceAll("\"([^\"]*)\"", "$1").split(",");
        mActionBar.setTitle("确认订单");
        listViewDeliveryTime.setOnItemClickListener(new LinearListView.OnItemClickListener() {
            @Override
            public void onItemClick(LinearListView linearListView, View view, int i, long l) {
                presenter.selectDeliveryTimeItem(i);
            }
        });
        deliveryTimeAdapter = new DeliveryTimeAdapter(null, null);
        listViewDeliveryTime.setAdapter(deliveryTimeAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_confirm_order;
    }

    @Override
    protected ConfirmOrderPresenterImpl getPresenter() {
        return new ConfirmOrderPresenterImpl(DataSet.getInstance().getUser().getMember_id(),
                getIntent().getExtras().getString("list"),
                getIntent().getExtras().getDouble("total"));
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
        presenter.loadOrderInfo();
    }

    @OnClick({R.id.confirm_order_address_click,
            R.id.confirm_order_delivery_time_click,
            R.id.confirm_order_coupon_click,
            R.id.confirm_order_submit})
    public void onClick(View v) {
        presenter.onClick(v.getId(), Commonly.getViewText(etxtNote));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.result(requestCode, resultCode, data);
    }

    @Override
    public void showAddress(ConfirmOrder.AddressBean address) {
        tvCreateAddress.setVisibility(View.GONE);
        linlayAddress.setVisibility(View.VISIBLE);
        tvNameAndPhone.setText("收货人：" + address.getName() + "\t\t\t\t\t\t" + address.getTel());
        tvAddressDetail.setText("收货地址：" + address.getAddress());
    }

    @Override
    public void showEmptyAddress() {
        tvCreateAddress.setVisibility(View.VISIBLE);
        linlayAddress.setVisibility(View.GONE);
    }

    @Override
    public void showDeliveryTimeList(List<String> delivery_time, List<Boolean> isSelected) {
        deliveryTimeAdapter.replaceData(delivery_time, isSelected);
    }

    @Override
    public void showCommodityList(List<ConfirmOrder.GoodsBean> list) {
        commodityAdapter = new CommodityAdapter(list);
        listViewCommidity.setAdapter(commodityAdapter);
    }

    @Override
    public void showFreightAndTotal(boolean isShow, String freight_price, String total) {
        // 显示运费
        tvFreight.setText("￥" + freight_price);
        linlayFreightPrice.setVisibility(isShow ? View.GONE : View.VISIBLE);
        // 显示总价
        tvStatisticsTotal.setText("￥" + total);
    }

    @Override
    public void openAddresses() {
        Bundle bundle = new Bundle();
        startActivityForResult(AddressesAty.class, bundle, REQUEST_ADDRESS);
    }

    @Override
    public void openCoupons(double total) {
        Bundle bundle = new Bundle();
        bundle.putDouble("total", total);
        startActivityForResult(CouponsAty.class, bundle, REQUEST_COUPON);
    }

    @Override
    public void openPayment(double payTotal, SN sn) {
        Bundle bundle = new Bundle();
        bundle.putDouble("payTotal", payTotal);
        bundle.putString("bill_sn", String.valueOf(sn.getBill_sn()));
        startActivity(PayAty.class, bundle);
        finish();
    }

    @Override
    public void showDeliveryTime(String deliveryTime) {
        tvDeliveryTime.setText(deliveryTime);
    }

    @Override
    public void setIsUseCoupon(boolean isUsed, String coupon_money) {
        if (isUsed) {
            tvCoupon.setText("-￥" + coupon_money);
            linlayStatisticsCoupon.setVisibility(View.VISIBLE);
            tvStatisticsCoupon.setText("-￥" + coupon_money);
        } else {
            // 隐藏优惠券统计项
            linlayStatisticsCoupon.setVisibility(View.GONE);
            // 重置选择的优惠券
            tvCoupon.setText(coupon_money);
        }
    }

    @Override
    public void showPayTotal(String payTotal) {
        tvTotal.setText("￥" + payTotal);
    }

    @Override
    public void setIsShowDeliveryTime(boolean isShow) {
        listViewDeliveryTime.setVisibility(isShow ? View.VISIBLE : View.GONE);
        tvDeliveryTime.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                isShow ? R.drawable.flag_arrow_down : R.drawable.flag_arrow_right, 0);
    }

    // 配送时间列表
    class DeliveryTimeAdapter extends BaseAdapter {

        private ViewHolder viewHolder;
        private List<String> list;
        private List<Boolean> isSelected;

        public void replaceData(List<String> list, List<Boolean> isSelected) {
            setList(list);
            setIsSelected(isSelected);
            notifyDataSetChanged();
        }

        private void setList(List<String> list) {
            this.list = list;
        }

        private void setIsSelected(List<Boolean> isSelected) {
            this.isSelected = isSelected;
        }

        public DeliveryTimeAdapter(List<String> list, List<Boolean> isSelected) {
            this.list = list;
            this.isSelected = isSelected;
        }

        @Override
        public int getCount() {
            return ListUtils.getSize(list);
        }

        @Override
        public String getItem(int position) {
//            return delivery_time[position].replaceAll("\"([^\"]*)\"", "$1");
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(ConfirmOrderAty.this).inflate(R.layout.listitem_delivery_time, parent, false);
                ButterKnife.bind(viewHolder, convertView);
                AutoUtils.auto(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // 根据item项的选择状态更新UI
            if (isSelected.get(position))
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.flag_delivery_time_checked, 0, 0, 0);
            else
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.flag_delivery_time_normal, 0, 0, 0);
            viewHolder.textView.setText(getItem(position));
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.listitem_delivery_time_item)
            TextView textView;
        }
    }

    /**
     * 商品清单
     */
    class CommodityAdapter extends BaseAdapter {

        private ViewHolder viewHolder;
        private List<ConfirmOrder.GoodsBean> list;

        public CommodityAdapter(List<ConfirmOrder.GoodsBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return ListUtils.getSize(list);
        }

        @Override
        public ConfirmOrder.GoodsBean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ConfirmOrder.GoodsBean good = getItem(position);
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(ConfirmOrderAty.this).inflate(R.layout.listitem_confirm_order, parent, false);
                ButterKnife.bind(viewHolder, convertView);
                AutoUtils.autoSize(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ImageLoader.loadUrl2Image(ConfirmOrderAty.this, good.getCover(), viewHolder.imgvImage, R.drawable.ic_default_172_172);
            viewHolder.tvName.setText(good.getName());
            viewHolder.tvPrice.setText("￥" + good.getPrice());
            viewHolder.tvSpec.setText("规格：" + good.getSpecify_name());
            viewHolder.tvNum.setText("×" + good.getNumber());
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.listitem_confirm_order_image)
            ImageView imgvImage;
            @BindView(R.id.listitem_confirm_order_name)
            TextView tvName;
            @BindView(R.id.listitem_confirm_order_price)
            TextView tvPrice;
            @BindView(R.id.listitem_confirm_order_spec)
            TextView tvSpec;
            @BindView(R.id.listitem_confirm_order_num)
            TextView tvNum;
        }
    }
}
