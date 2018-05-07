package com.toocms.template.ui.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toocms.frame.image.ImageLoader;
import com.toocms.frame.ui.BaseFragment;
import com.toocms.template.R;
import com.toocms.template.config.ProjectCache;
import com.toocms.template.modle.cart.Cart;
import com.toocms.template.ui.order.confirmorder.ConfirmOrderAty;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.util.StringUtils;
import cn.zero.android.common.view.FButton;
import cn.zero.android.common.view.swipetoloadlayout.OnRefreshListener;
import cn.zero.android.common.view.swipetoloadlayout.view.SwipeToLoadRecyclerView;
import cn.zero.android.common.view.swipetoloadlayout.view.listener.OnItemClickListener;

/**
 * 购物车
 *
 * @author Zero
 * @date 2016/7/6 12:21
 */
public class CartFgt extends BaseFragment<CartView, CartPresenterImpl> implements CartView, OnRefreshListener {

    @BindView(R.id.cart_state)
    TextView tvState;
    @BindView(R.id.cart_notice)
    TextView tvNotice;
    @BindView(R.id.cart_all)
    TextView tvAll;
    @BindView(R.id.linlay_total)
    LinearLayout linlayTotal;
    @BindView(R.id.cart_total)
    TextView tvTotal;
    @BindView(R.id.cart_freight)
    TextView tvFreight;
    @BindView(R.id.cart_settle)
    FButton fbtnSettle;
    @BindView(R.id.cart_empty)
    TextView empty;
    @BindView(R.id.cart_list)
    SwipeToLoadRecyclerView swipeToLoadRecyclerView;
    private CartAdapter adapter;

    @Override
    protected void onCreateFragment(@Nullable Bundle bundle) {
        // 指定显示方向
        swipeToLoadRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        // 设置刷新监听
        swipeToLoadRecyclerView.setOnRefreshListener(this);
        // 设置item点击监听
        swipeToLoadRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }
        });
        adapter = new CartAdapter(null, listener);
        swipeToLoadRecyclerView.setAdapter(adapter);
        // 设置标题栏的切换状态按钮监听
        tvState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onStateChange(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // 重置为未编辑状态
        presenter.onStateChange(true);
        // 调用购物车列表接口
        presenter.loadCartList(ProjectCache.getCityId(), true);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fgt_cart;
    }

    @Override
    protected int getTitlebarResId() {
        return R.id.cart_titlebar;
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected CartPresenterImpl getPresenter() {
        return new CartPresenterImpl();
    }

    @Override
    protected void requestData() {
    }

    @OnClick({R.id.cart_settle, R.id.cart_all})
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }

    @Override
    public void onRefresh() {
        presenter.loadCartList(ProjectCache.getCityId(), false);
    }

    @Override
    public void showCartList(List<Cart.ListBean> data) {
        adapter.replaceData(data);
        swipeToLoadRecyclerView.stopRefreshing();
    }

    @Override
    public void showNotice(String no_freight) {
        tvNotice.setText("全场满" + StringUtils.substringBefore(no_freight, ".") + "包邮");
    }

    @Override
    public void showStatus(String status) {
        tvState.setText(status);
    }

    @Override
    public void showSettle(String settle) {
        fbtnSettle.setText(settle);
    }

    @Override
    public void showFreight(boolean isShowFreight, String freight) {
        tvFreight.setVisibility(isShowFreight ? View.VISIBLE : View.GONE);
        tvFreight.setText(freight);
    }

    @Override
    public void showTotal(boolean isShowSettle, String total) {
        linlayTotal.setVisibility(isShowSettle ? View.VISIBLE : View.INVISIBLE);
        tvTotal.setText(total);
    }

    @Override
    public void showIsAllSelected(int resId) {
        tvAll.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    @Override
    public void setIsShowEmptyView(boolean isShowEmptyView) {
        tvState.setVisibility(isShowEmptyView ? View.INVISIBLE : View.VISIBLE);
        empty.setVisibility(isShowEmptyView ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void openConfirmOrder(String list, double total) {
        Bundle bundle = new Bundle();
        bundle.putString("list", list);
        bundle.putDouble("total", total);
        startActivity(ConfirmOrderAty.class, bundle);
    }

    private CartItemListener listener = new CartItemListener() {

        @Override
        public void onSelectedChange(Cart.ListBean cart, boolean isSelected) {
            presenter.changeSelected(cart, isSelected);
        }

        @Override
        public void onEditNumber(Cart.ListBean cart, boolean isPlus) {
            presenter.editNumber(cart, isPlus);
        }
    };

    interface CartItemListener {
        void onSelectedChange(Cart.ListBean cart, boolean isSelected);

        void onEditNumber(Cart.ListBean cart, boolean isPlus);
    }

    class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

        private List<Cart.ListBean> list;
        private CartItemListener listener;

        public CartAdapter(List<Cart.ListBean> list, CartItemListener listener) {
            setList(list);
            this.listener = listener;
        }

        public void replaceData(List<Cart.ListBean> list) {
            setList(list);
            notifyDataSetChanged();
        }

        public void setList(List<Cart.ListBean> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.listitem_cart, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // 控制底部间距
            holder.vOccupy.setVisibility((position == ListUtils.getSize(list) - 1) ? View.VISIBLE : View.GONE);
            // 赋值
            final Cart.ListBean cart = list.get(position);
            // 获取该项是不是被选中
            final boolean isSelected = cart.is_selected();
            // 显示相应的状态图标
            holder.imgvSelect.setImageResource(isSelected ? R.drawable.flag_radio_checked : R.drawable.flag_radio_normal);
            // 设置各控件的值
            ImageLoader.loadUrl2Image(glide, cart.getCover(), holder.imgvImage, R.drawable.ic_default_172_172);
            holder.tvName.setText(cart.getName());
            holder.tvSpec.setVisibility(StringUtils.isEmpty(cart.getSpecify_name()) ? View.GONE : View.VISIBLE);
            holder.tvSpec.setText("规格：" + cart.getSpecify_name());

            holder.tvPrice.setText("￥" + cart.getPrice());
            holder.tvNum.setText(cart.getNumber());

            // 选择按钮的点击事件
            holder.imgvSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectedChange(cart, !isSelected);
                }
            });
            // 减
            holder.imgbtnReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditNumber(cart, false);
                }
            });
            // 加
            holder.imgbtnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditNumber(cart, true);
                }
            });
        }

        @Override
        public int getItemCount() {
            return ListUtils.getSize(list);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.listitem_cart_select)
            public AppCompatImageView imgvSelect;
            @BindView(R.id.listitem_cart_image)
            public ImageView imgvImage;
            @BindView(R.id.listitem_cart_name)
            public TextView tvName;
            @BindView(R.id.listitem_cart_spec)
            public TextView tvSpec;
            @BindView(R.id.listitem_cart_price)
            public TextView tvPrice;
            @BindView(R.id.listitem_cart_num)
            public TextView tvNum;
            @BindView(R.id.listitem_cart_reduce)
            public ImageButton imgbtnReduce;
            @BindView(R.id.listitem_cart_plus)
            public ImageButton imgbtnPlus;
            @BindView(R.id.listitem_cart_occupy)
            public View vOccupy;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                AutoUtils.autoSize(itemView);
            }
        }
    }
}