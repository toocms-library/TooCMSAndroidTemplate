package com.toocms.template.ui.address.selectregion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toocms.template.R;
import com.toocms.template.modle.address.AddressTemp;
import com.toocms.template.modle.address.Region;
import com.toocms.template.ui.BaseAty;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.view.swipetoloadlayout.view.SwipeToLoadRecyclerView;
import cn.zero.android.common.view.swipetoloadlayout.view.listener.OnItemClickListener;

/**
 * Author：Zero
 * Date：2017/5/4 11:58
 *
 * @version v2.0
 */

public class SelectRegionAty extends BaseAty<SelectRegionView, SelectRegionPresenterImpl> implements SelectRegionView {

    @BindView(R.id.city_list)
    SwipeToLoadRecyclerView swipeToLoadRecyclerView;
    RegionAdapter adapter;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("选择地区");
        swipeToLoadRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        swipeToLoadRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                presenter.selectRegionItem(i);
            }
        });
        adapter = new RegionAdapter();
        swipeToLoadRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_city;
    }

    @Override
    protected SelectRegionPresenterImpl getPresenter() {
        return new SelectRegionPresenterImpl((AddressTemp) getIntent().getExtras().getSerializable("address"));
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
        presenter.loadRegionList();
    }

    @Override
    public void showRegionList(List<Region> data) {
        adapter.replaceData(data);
    }

    @Override
    public void selectRegionItem(AddressTemp temp) {
        Intent intent = getIntent();
        intent.putExtra("address", temp);
        setResult(RESULT_OK, intent);
        finish();
    }

    class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.ViewHolder> {

        private List<Region> list;

        public void replaceData(List<Region> list) {
            setList(list);
            notifyDataSetChanged();
        }

        private void setList(List<Region> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SelectRegionAty.this).inflate(R.layout.listitem_city, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvRegion.setText(list.get(position).getRegion_name());
        }

        @Override
        public int getItemCount() {
            return ListUtils.getSize(list);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.listitem_city)
            public TextView tvRegion;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                AutoUtils.autoSize(itemView);
            }
        }
    }
}
