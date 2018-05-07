package com.toocms.template.ui.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.toocms.frame.image.ImageLoader;
import com.toocms.frame.ui.BaseFragment;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.zero.android.common.recyclerview.RecycleViewDivider;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.view.banner.ConvenientBanner;
import cn.zero.android.common.view.banner.holder.CBViewHolderCreator;
import cn.zero.android.common.view.banner.holder.Holder;
import cn.zero.android.common.view.banner.listener.OnItemClickListener;
import cn.zero.android.common.view.swipetoloadlayout.OnRefreshListener;
import cn.zero.android.common.view.swipetoloadlayout.view.SwipeToLoadRecyclerView;

/**
 * 首页
 *
 * @author Zero
 * @date 2016/6/30 12:42
 */
public class IndexFgt extends BaseFragment implements OnRefreshListener {

    public static final String ABS_URL = "abs_url"; // 优惠券ID
    public static final String GOODS_NAME = "goods_name"; // 优惠券金额
    public static final String GOODS_BRIEF = "goods_brief"; // 优惠券使用条件（满XX元可用）
    public static final String GOODS_PRICE = "goods_price"; // 优惠券名字（也可以是优惠券码之类的）
    public static final String UNIT = "unit"; // 优惠券有效期

    @BindView(R.id.index_list)
    SwipeToLoadRecyclerView swipeToLoadRecyclerView;
    // 轮播图
    private View headerBanner;
    private ConvenientBanner banner;

    private List<Map<String, String>> listBanner = new ArrayList();
    private List<Map<String, String>> listCommodity = new ArrayList();

    // ===============================  模拟数据源开始点（需要删除）  ==============================
    {
        for (int i = 0; i < 3; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(ABS_URL, "http://scdj-bms.supeimingyang.com/Uploads/Advert/2016-06-29/57736465c8c9e.jpg");
            listBanner.add(map);
        }
        for (int i = 0; i < 6; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(ABS_URL, "http://scdj-bms.toocms.com//Uploads//GoodsImages//2016-06-08//5757cb2ceed81.jpg");
            map.put(GOODS_NAME, "辣椒");
            map.put(GOODS_BRIEF, "超辣朝天椒");
            map.put(GOODS_PRICE, "35.00");
            map.put(UNIT, "100g");
            listCommodity.add(map);
        }
    }
    // ===============================  模拟数据源结束点（需要删除）  ===============================

    @Override
    protected void onCreateFragment(@Nullable Bundle bundle) {
        // 设置下拉刷新监听
        swipeToLoadRecyclerView.setOnRefreshListener(this);
        // 设置显示类型
        swipeToLoadRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        // 添加分割线
        swipeToLoadRecyclerView.getRecyclerView().addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, AutoUtils.getPercentHeightSize(15), getResources().getColor(R.color.transparent)));
        // ========================== 以下方法放到onComplete方法中 ========================
        updateUI();
        // ========================== 以上方法放到onComplete方法中 ========================
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fgt_index;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getTitlebarResId() {
        return R.id.index_titlebar;
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
    }

    @Override
    public void onRefresh() {
        // ================= 次数刷新方法为模拟刷新，实际项目中换成接口调用 ================
        swipeToLoadRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        }, 2000);
    }

    // 初始化顶部banner
    private void initBanner(final List<Map<String, String>> list) {
        if (headerBanner == null) {
            headerBanner = View.inflate(getActivity(), R.layout.header_index_banner, null);
            banner = headerBanner.findViewById(R.id.banner);
        }
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View view) {
                return new LocalImageHolderView(view);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner;
            }
        }, list).setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focused}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                // ============================== 根据类型判断跳转页面 ===============================

            }
        });
        banner.startTurning(5000);
        // 将banner添加到首页列表头部
        swipeToLoadRecyclerView.addHeaderView(headerBanner);
    }

    /**
     * 此方法需在onComplete中调用，模板中因为不涉及到联网请求所以在onActivityCreated中调用
     * 在onComplete中调用此方法之前记得先给数据源赋值
     */
    private void updateUI() {
        if (!ListUtils.isEmpty(listBanner))
            initBanner(listBanner);
        swipeToLoadRecyclerView.setAdapter(new CommodityAdapter(glide, listCommodity));
        swipeToLoadRecyclerView.stopRefreshing();
    }

    // 轮播图
    private class LocalImageHolderView extends Holder<Map<String, String>> {

        private ImageView imageView;

        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View view) {
            imageView = view.findViewById(R.id.item_banner_imageview);
        }

        @Override
        public void updateUI(Map<String, String> map) {
            ImageLoader.loadUrl2Image(glide, map.get("abs_url"), imageView, R.drawable.ic_default_750_374);
        }
    }
}
