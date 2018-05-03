package com.toocms.template.ui.address.selectcity;

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
import com.toocms.template.ui.address.selectregion.SelectRegionAty;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.view.swipetoloadlayout.view.SwipeToLoadRecyclerView;
import cn.zero.android.common.view.swipetoloadlayout.view.listener.OnItemClickListener;

/**
 * 选择城市
 * Author：Zero
 * Date：2017/4/28 16:51
 *
 * @version v2.0
 */

public class SelectCityAty extends BaseAty<SelectCityView, SelectCityPresenterImpl> implements SelectCityView {

    public static final int REQUEST_CITY = 0x2323;

    @BindView(R.id.city_list)
    SwipeToLoadRecyclerView swipeToLoadRecyclerView;
    CityAdapter adapter;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        //        "(?<=\\d{3})\\d(?=\\d{3})", "*"
        mActionBar.setTitle("选择城市");
        swipeToLoadRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        swipeToLoadRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                presenter.selectCityItem(i);
            }
        });
        adapter = new CityAdapter();
        swipeToLoadRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_city;
    }

    @Override
    protected SelectCityPresenterImpl getPresenter() {
        return new SelectCityPresenterImpl();
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
        presenter.loadCityList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.result(requestCode, resultCode, data);
    }

    @Override
    public void showCityList(List<Region> data) {
        adapter.replaceData(data);
    }

    @Override
    public void startSelectRegionAty(Bundle bundle) {
        startActivityForResult(SelectRegionAty.class, bundle, REQUEST_CITY);
    }

    @Override
    public void finish(Intent data) {
        setResult(RESULT_OK, data);
        finish();
    }

    class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

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
            View view = LayoutInflater.from(SelectCityAty.this).inflate(R.layout.listitem_city, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvCity.setText(list.get(position).getRegion_name());
        }

        @Override
        public int getItemCount() {
            return ListUtils.getSize(list);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.listitem_city)
            public TextView tvCity;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                AutoUtils.autoSize(itemView);
            }
        }
    }
}
