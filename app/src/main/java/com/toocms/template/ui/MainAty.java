package com.toocms.template.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toocms.frame.config.LoginStatus;
import com.toocms.frame.tool.AppManager;
import com.toocms.frame.tool.Toolkit;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.frame.update.UpdateManager;
import com.toocms.template.R;
import com.toocms.template.config.AppConfig;
import com.toocms.template.config.ProjectCache;
import com.toocms.template.ui.cart.CartFgt;
import com.toocms.template.ui.classify.ClassifyFgt;
import com.toocms.template.ui.index.IndexFgt;
import com.toocms.template.ui.lar.login.LoginAty;
import com.toocms.template.ui.loading.PopAdvertAty;
import com.toocms.template.ui.mine.MineFgt;
import com.toocms.template.util.CartUtils;

import org.xutils.x;

/**
 * 底部菜单栏样式主框架
 * <p>
 * 用法：通过修改此类中的常量来控制跳转页面、底部标签字体颜色
 * 添加标签栏：如需添加底部标签栏个数需要以下几步
 * 1、在布局中复制一个标签控件，放在任意位置
 * 2、drawable-xhdpi中按规则添加对应的图片（正常、按下状态的2张）
 * 3、修改CLASSES常量，在对应的位置添加点击标签后所跳转的Fragment
 * <p>
 * 修改底部标签栏的图标：直接替换drawable-xhdpi文件夹中所对应的图片文件即可，注：main_tab_item系列文件名
 * CLR_NORMAL：对应底部标签栏正常状态下的文字颜色
 * CLR_CHECKED：对应底部标签栏选中状态下的文字颜色
 * CLASSES：对应底部标签点击之后所跳转的Fragment页面
 * 是否使用角标：通过修改AppConfig类中的IS_USE_BADGE常量来控制是否显示标签栏的角标控件
 *
 * @author Zero
 * @date 2016/6/23 16:32
 */
public class MainAty extends BaseAty {

    private static final Class[] CLASSES = new Class[]{IndexFgt.class, ClassifyFgt.class, CartFgt.class, MineFgt.class}; // 底部标签所对应的Fragment类
    private static final int CLR_NORMAL = Color.parseColor("#878787"); // 底部标签 正常 状态下的文字颜色
    private static final int CLR_CHECKED = x.app().getResources().getColor(R.color.clr_main); // 底部标签 选中 状态下的文字颜色

    public static TextView badgeView; // 底部的圆形角标

    public static TextView[] tabs;

    public int position; // 点击的底部tab的位置

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.hide();
        UpdateManager.checkUpdate(false);
        ProjectCache.saveCityId("343");
        // 初始化底部标签按钮
        initTabs();
        // 初始化购物车角标
        if (AppConfig.IS_USE_BADGE) {
            int cartNum = CartUtils.getCartNum();
            if (LoginStatus.isLogin())
                if (cartNum > 0) {
                    badgeView.setText(String.valueOf(cartNum));
                    badgeView.setVisibility(View.VISIBLE);
                }
        }
        // 选中初始标签以及Fragment
        selectedItem(this, position);
        addFragment(CLASSES[0], null);
        // 启动弹出广告页
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(PopAdvertAty.class, null);
                overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
            }
        }, 1500);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.main_content;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
    }

    private void initTabs() {
        // 初始化角标
        badgeView = (TextView) findViewById(R.id.tv_badge);
        // 获取menu容器中子控件的数量
        int count = ((LinearLayout) findViewById(R.id.main_menu)).getChildCount();
        // 根据子控件的数量初始化tabs数组
        tabs = new TextView[count];
        for (int i = 0; i < count; i++) {
            tabs[i] = (TextView) findViewById(Toolkit.getViewRes(this, "main_item" + (i + 1)));
            final int finalI = i;
            tabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 2)
                        if (!LoginStatus.isLogin()) {
                            startActivity(LoginAty.class, null);
                            return;
                        }
                    position = finalI;
                    selectedItem(MainAty.this, position);
                    addFragment(CLASSES[position], null);
                }
            });
        }
    }

    /**
     * 设置购物车角标数量
     *
     * @param num 角标所显示的数量
     */
    public static void setBadgeNum(int num) {
        if (AppConfig.IS_USE_BADGE)
            if (LoginStatus.isLogin()) {
                if (num > 0) {
                    badgeView.setText(String.valueOf(num));
                    badgeView.setVisibility(View.VISIBLE);
                } else {
                    badgeView.setVisibility(View.INVISIBLE);
                }
            } else {
                badgeView.setVisibility(View.INVISIBLE);
            }
    }

    /**
     * 选择底部标签
     *
     * @param context
     * @param position 标签标识
     */
    public static void selectedItem(Context context, int position) {
        for (int i = 0; i < tabs.length; i++) {
            if (i == position) {
                tabs[i].setTextColor(CLR_CHECKED);
                tabs[i].setCompoundDrawablesWithIntrinsicBounds(0, Toolkit.getBitmapRes(context, "main_tab_item_icon" + (i + 1) + "_checked"), 0, 0);
            } else {
                tabs[i].setTextColor(CLR_NORMAL);
                tabs[i].setCompoundDrawablesWithIntrinsicBounds(0, Toolkit.getBitmapRes(context, "main_tab_item_icon" + (i + 1) + "_normal"), 0, 0);
            }
        }
    }

    @Override
    public void onBackPressed() {
        // 判断如果底部选中标签不是第一个（首页）的话就跳到第一个，否则提示退出
        if (position != 0) {
            tabs[0].performClick();
        } else {
            showDialog("提示", "是否确认退出？", "确定", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hasAnimiation = false;
                    AppManager.getInstance().AppExit(MainAty.this);
                }
            }, null);
        }
    }
}
