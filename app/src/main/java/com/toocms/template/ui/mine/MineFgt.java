package com.toocms.template.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.toocms.frame.config.LoginStatus;
import com.toocms.frame.image.ImageLoader;
import com.toocms.frame.ui.BaseFragment;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.config.User;
import com.toocms.template.ui.address.addresses.AddressesAty;
import com.toocms.template.ui.lar.login.LoginAty;
import com.toocms.template.ui.mine.settings.SettingsAty;
import com.toocms.template.ui.mine.user.UserInfoAty;

import org.xutils.common.util.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 *
 * @author Zero
 * @date 2016/7/6 11:52
 */
public class MineFgt extends BaseFragment {

    @BindView(R.id.mine_head)
    ImageView imgvHead;
    @BindView(R.id.mine_name)
    TextView tvName;

    @Override
    protected void onCreateFragment(@Nullable Bundle bundle) {

    }

    @Override
    public void onResume() {
        super.onResume();
        // 判断是否登录从而展示对应的界面
        if (LoginStatus.isLogin()) {
            User user = application.getUserInfo();
            // 昵称
            tvName.setText(user.getNickname());
            tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.flag_arrow_right, 0);
            // 头像
            String head = user.getCover();
            ImageLoader.loadUrl2CircleImage(getActivity(), head, imgvHead, R.drawable.ic_default_head);
        } else {
            tvName.setText("登录 | 注册");
            tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            imgvHead.setImageResource(R.drawable.ic_default_head);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fgt_mine;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getTitlebarResId() {
        return R.id.mine_titlebar;
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
    }

    // ================================== 订单点击事件 ==================================
    @OnClick({R.id.mine_order, R.id.mine_order_pay, R.id.mine_order_delivery,
            R.id.mine_order_take, R.id.mine_order_evaluate, R.id.mine_order_return,})
    public void onOrderClick(View v) {
        if (!LoginStatus.isLogin()) {
            startActivity(LoginAty.class, null);
            return;
        }
        switch (v.getId()) {
            case R.id.mine_order: // 我的订单
                break;
            case R.id.mine_order_pay: // 待支付
                break;
            case R.id.mine_order_delivery: // 待发货
                break;
            case R.id.mine_order_take: // 待收货
                break;
            case R.id.mine_order_evaluate: // 待评价
                break;
            case R.id.mine_order_return: // 退换
                break;
        }
    }

    // ============================== 意见反馈点、设置击事件 ==============================
    @OnClick({R.id.mine_feedback, R.id.mine_settings})
    public void onFeedBackClick(View v) {
        switch (v.getId()) {
            case R.id.mine_feedback: // 意见反馈
                break;
            case R.id.mine_settings: // 设置
                startActivity(SettingsAty.class, null);
                break;
        }
    }

    // ================================= 其他按钮点击事件 ==================================
    @OnClick({R.id.mine_user, R.id.mine_browse, R.id.mine_wallet, R.id.mine_address, R.id.mine_collect, R.id.mine_coupon})
    public void onClick(View v) {
        if (!LoginStatus.isLogin()) {
            startActivity(LoginAty.class, null);
            return;
        }
        switch (v.getId()) {
            case R.id.mine_user: // 登录|用户信息
                startActivity(UserInfoAty.class, null);
                break;
            case R.id.mine_browse: // 浏览记录
                break;
            case R.id.mine_wallet: // 我的钱包
                break;
            case R.id.mine_address: // 收货地址
                startActivity(AddressesAty.class, null);
                break;
            case R.id.mine_collect: // 我的收藏
                break;
            case R.id.mine_coupon: // 优惠券
                break;
        }
    }
}
