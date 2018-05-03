package com.toocms.template.ui.lar.registerphone;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.toocms.frame.tool.Commonly;
import com.toocms.template.R;
import com.toocms.template.config.AppCountdown;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.lar.registerpsd.RegisterPsdAty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册 - 验证手机号
 *
 * @author Zero
 * @date 2016/7/8 17:32
 */
public class RegisterPhoneAty extends BaseAty<RegisterPhoneView, RegisterPhonePresenterImpl> implements RegisterPhoneView {

    @BindView(R.id.register_phone)
    EditText etxtPhone;
    @BindView(R.id.register_code)
    EditText etxtCode;
    @BindView(R.id.register_get_code)
    TextView tvGetcode;

    private AppCountdown appCountdown;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("注册");
        // 初始化倒计时
        appCountdown = new AppCountdown();
        appCountdown.play(tvGetcode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appCountdown.stop();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_register_phone;
    }

    @Override
    protected RegisterPhonePresenterImpl getPresenter() {
        return new RegisterPhonePresenterImpl();
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
    }

    @OnClick({R.id.register_get_code, R.id.register_next})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_get_code:
                presenter.sendCode(Commonly.getViewText(etxtPhone));
                break;
            case R.id.register_next:
                presenter.verifyCode(Commonly.getViewText(etxtPhone), Commonly.getViewText(etxtCode));
                break;
        }
    }

    @Override
    public void startCountdown() {
        tvGetcode.setTextColor(Color.parseColor("#656565"));
        tvGetcode.setEnabled(false);
        appCountdown.start();
    }

    @Override
    public void openRegisterPsd() {
        Bundle bundle = new Bundle();
        bundle.putString("account", Commonly.getViewText(etxtPhone));
        startActivity(RegisterPsdAty.class, bundle);
    }
}
