package com.toocms.template.ui.address.addresses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toocms.template.R;
import com.toocms.template.modle.address.Address;
import com.toocms.template.modle.order.ConfirmOrder;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.address.addressdetail.AddressDetailAty;
import com.toocms.template.ui.order.confirmorder.ConfirmOrderAty;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.view.swipetoloadlayout.OnRefreshListener;
import cn.zero.android.common.view.swipetoloadlayout.view.SwipeToLoadRecyclerView;
import cn.zero.android.common.view.swipetoloadlayout.view.listener.OnItemClickListener;

/**
 * 地址列表
 *
 * @author Zero
 * @date 2016/7/12 14:10
 */
public class AddressesAty extends BaseAty<AddressesView, AddressesPresenterImpl> implements AddressesView, OnRefreshListener {

    @BindView(R.id.list)
    SwipeToLoadRecyclerView swipeToLoadRecyclerView;
    private AddressAdapter adapter;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("收货地址");
        swipeToLoadRecyclerView.setEmptyView(findViewById(R.id.empty));
        swipeToLoadRecyclerView.setOnRefreshListener(this);
        swipeToLoadRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        swipeToLoadRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(android.view.View view, int i) {
                // 如果是从确认订单页跳转进来的
                if (TextUtils.equals(getFrom(), ConfirmOrderAty.class.getSimpleName()))
                    presenter.onSelectItem(i);
            }
        });
        adapter = new AddressAdapter(null, listener);
        swipeToLoadRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAddressList(true);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_address_list;
    }

    @Override
    protected AddressesPresenterImpl getPresenter() {
        return new AddressesPresenterImpl();
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                presenter.openAddNewAddress();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        presenter.loadAddressList(false);
    }

    @Override
    public void showAddressList(List<Address> data) {
        adapter.replaceData(data);
        swipeToLoadRecyclerView.stopRefreshing();
    }

    @Override
    public void openAddNewAddress() {
        Bundle bundle = new Bundle();
        bundle.putString("flag", "add");
        startActivity(AddressDetailAty.class, bundle);
    }

    @Override
    public void openEditAddress(String address_id) {
        Bundle bundle = new Bundle();
        bundle.putString("address_id", address_id);
        startActivity(AddressDetailAty.class, bundle);
    }

    @Override
    public void onSelectItem(ConfirmOrder.AddressBean address) {
        Intent intent = getIntent();
        intent.putExtra("address", address);
        setResult(RESULT_OK, intent);
        finish();
    }

    AddressItemListener listener = new AddressItemListener() {

        @Override
        public void onDefaultClick(Address address) {
            presenter.setDefaultAddress(address);
        }

        @Override
        public void onEditClick(String address_id) {
            presenter.openEditAddress(address_id);
        }

        @Override
        public void onDeleteClick(final Address address) {
            showDialog("提示", "是否要删除该收货地址？", "确定", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.deleteAddress(address);
                }
            }, null);
        }
    };

    interface AddressItemListener {
        void onDefaultClick(Address address);

        void onEditClick(String address_id);

        void onDeleteClick(Address address);
    }

    class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

        private List<Address> list;
        private AddressItemListener listener;

        public AddressAdapter(List<Address> list, AddressItemListener listener) {
            setList(list);
            this.listener = listener;
        }

        public void replaceData(List<Address> list) {
            setList(list);
            notifyDataSetChanged();
        }

        private void setList(List<Address> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            android.view.View view = LayoutInflater.from(AddressesAty.this).inflate(R.layout.listitem_address, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // 控制底部间距
            if (position == ListUtils.getSize(list) - 1) {
                holder.vOccupy.setVisibility(android.view.View.VISIBLE);
            } else {
                holder.vOccupy.setVisibility(android.view.View.GONE);
            }
            // ================ 把Address类中的字段改成和接口一样的字段即可，也可只改类中的变量名而无需再修改方法名 ================
            final Address address = list.get(position);
            holder.tvName.setText(address.getName());
            holder.tvPhone.setText(address.getTel());
            holder.tvAddress.setText(address.getRegion_name() + address.getArea_name() + address.getRess());
            // 判断此项地址是否为默认地址
            if (TextUtils.equals(address.getDef_address(), "1")) {
                holder.tvDefault.setCompoundDrawablesWithIntrinsicBounds(R.drawable.flag_cbox_checked, 0, 0, 0);
                holder.tvDefault.setText("默认地址");
            } else {
                holder.tvDefault.setCompoundDrawablesWithIntrinsicBounds(R.drawable.flag_cbox_normal, 0, 0, 0);
                holder.tvDefault.setText("设为默认");
            }
            // 默认按钮点击事件
            holder.tvDefault.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    listener.onDefaultClick(address);
                }
            });
            // 编辑按钮点击事件
            holder.tvEdit.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    listener.onEditClick(address.getAddress_id());
                }
            });
            // 删除按钮点击事件
            holder.tvDelete.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    listener.onDeleteClick(address);
                }
            });
        }

        @Override
        public int getItemCount() {
            return ListUtils.getSize(list);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.listitem_address_name)
            public TextView tvName;
            @BindView(R.id.listitem_address_phone)
            public TextView tvPhone;
            @BindView(R.id.listitem_address)
            public TextView tvAddress;
            @BindView(R.id.listitem_address_edit)
            public TextView tvEdit;
            @BindView(R.id.listitem_address_delete)
            public TextView tvDelete;
            @BindView(R.id.listitem_address_default)
            public TextView tvDefault;
            @BindView(R.id.listitem_address_occupy)
            public android.view.View vOccupy;

            public ViewHolder(android.view.View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                AutoUtils.auto(itemView);
            }
        }
    }
}
