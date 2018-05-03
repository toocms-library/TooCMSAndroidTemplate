package com.toocms.template.ui.index;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toocms.frame.image.ImageLoader;
import com.toocms.template.R;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.x;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zero.android.common.util.ListUtils;

/**
 * 首页推荐商品列表适配器
 *
 * @author Zero
 * @date 2016/7/5 12:23
 */
public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder> {

    private List<Map<String, String>> list;

    public CommodityAdapter(List<Map<String, String>> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_commodity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // ================ 把map的key值改成接口对应的key值即可 ================
        Map<String, String> map = list.get(position);
        ImageLoader.loadUrl2Image(x.app(), map.get(IndexFgt.ABS_URL), holder.imgvImage, R.drawable.ic_default_172_172);
        holder.tvName.setText(map.get(IndexFgt.GOODS_NAME));
        holder.tvDesc.setText(map.get(IndexFgt.GOODS_BRIEF));
        holder.tvPrice.setText(map.get(IndexFgt.GOODS_PRICE));
        holder.tvUnit.setText("/" + map.get(IndexFgt.UNIT));
    }

    @Override
    public int getItemCount() {
        return ListUtils.getSize(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.listitem_commodity_flag)
        ImageView imgvFlag;
        @BindView(R.id.listitem_commodity_image)
        ImageView imgvImage;
        @BindView(R.id.listitem_commodity_name)
        TextView tvName;
        @BindView(R.id.listitem_commodity_desc)
        TextView tvDesc;
        @BindView(R.id.listitem_commodity_price)
        TextView tvPrice;
        @BindView(R.id.listitem_commodity_unit)
        TextView tvUnit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
        }
    }
}
