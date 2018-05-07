package com.toocms.template.ui.loading;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.toocms.frame.tool.Toolkit;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.config.AppConfig;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.MainAty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.util.PreferencesUtils;
import cn.zero.android.common.view.banner.ConvenientBanner;
import cn.zero.android.common.view.banner.holder.CBViewHolderCreator;
import cn.zero.android.common.view.banner.holder.Holder;
import cn.zero.android.common.view.banner.listener.OnPageChangeListener;

/**
 * 引导页
 * <p>
 * 用法：1、首先确定是几张图片，将IMAGE_COUNT字段修改成需要显示图片数量
 * 2、将图片命名好ic_guide1、ic_guide2以此类推，并替换到drawable-xhdpi中
 * 3、更新新版本时如需显示新更换的引导页，需将AppConfig中的IS_FIRST字段修改一下
 *
 * @author Zero
 * @date 2016/8/26 15:44
 */
public class GuideAty extends BaseAty {

    private final int IMAGE_COUNT = 4; // 引导页的数量

    @BindView(R.id.guide)
    ConvenientBanner convenientBanner;
    @BindView(R.id.guide_experience)
    TextView textView;

    private List<String> list;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        // 设置全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActionBar.hide();
        PreferencesUtils.putBoolean(this, AppConfig.IS_FIRST, false);
        // 先将立即体验按钮隐藏
        textView.setVisibility(View.INVISIBLE);
        // 初始化引导页的viewpager控件
        // ================ 此处没有显示小圆点，一般情况下都是图片自带
        // 如果需要自己做的话就按照之前的方法添加就可以了 ================
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View view) {
                return new LocalImageHolderView(view);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner;
            }
        }, list).setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                // 判断一下如果是最后一页延迟一下显示立即体验按钮
                if (position == ListUtils.getSize(list) - 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.VISIBLE);
                        }
                    }, 300);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }
        }).setCanLoop(false);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_guide;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initialized() {
        list = new ArrayList<>();
        for (int i = 0; i < IMAGE_COUNT; i++) {
            list.add("ic_guide" + (i + 1));
        }
    }

    @Override
    protected void requestData() {
    }

    @OnClick(R.id.guide_experience)
    public void onClick(View v) {
        startActivity(MainAty.class, null);
        finish();
    }

    private class LocalImageHolderView extends Holder<String> {

        private ImageView imageView;

        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View view) {
            imageView = (ImageView) view.findViewById(R.id.item_banner_imageview);
        }

        @Override
        public void updateUI(String s) {
            imageView.setImageResource(Toolkit.getBitmapRes(GuideAty.this, s));
        }
    }
}
