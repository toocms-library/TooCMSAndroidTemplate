package com.toocms.template.ui.mine.phone;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.toocms.frame.tool.AppManager;
import com.toocms.frame.tool.Commonly;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.config.AppCountdown;
import com.toocms.template.config.DataSet;
import com.toocms.template.config.User;
import com.toocms.template.ui.BaseAty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 更改手机号
 *
 * @author Zero
 * @date 2016/7/20 12:39
 */
public class ChangePhoneAty extends BaseAty {

    @BindView(R.id.change_phone)
    EditText etxtPhone;
    @BindView(R.id.change_phone_code)
    EditText etxtCode;
    @BindView(R.id.change_phone_getcode)
    TextView tvGetcode;

    private AppCountdown appCountdown;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("更改手机号");
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
        return R.layout.aty_change_phone;
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

    @OnClick({R.id.change_phone_getcode, R.id.change_phone_sure})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_phone_getcode: // 获取验证码
                if (Commonly.getViewText(etxtPhone).length() < 11) {
                    showToast("请填写11位手机号");
                    return;
                }
                // =========================== 调用获取验证码接口 =========================

                // =================== 将以下代码放到onComplete的获取验证码判断中 ===================
                tvGetcode.setTextColor(Color.parseColor("#656565"));
                tvGetcode.setEnabled(false);
                appCountdown.start();
                break;
            case R.id.change_phone_sure: // 确认
                if (Commonly.getViewText(etxtPhone).length() < 11) {
                    showToast("请填写11位手机号");
                    return;
                }
                if (TextUtils.isEmpty(Commonly.getViewText(etxtCode))) {
                    showToast("请填写验证码");
                    return;
                }
                // ============================== 模拟数据（需删除） ============================
                User user = DataSet.getInstance().getUser();
                user.setAccount(Commonly.getViewText(etxtPhone));
                application.setUserInfo(user);
                showToast("更改手机号成功");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getInstance().killActivity(ValidatePhoneAty.class);
                        finish();
                    }
                }, DataSet.getInstance().getAppConfig().isUseSnackBar() ? 2000 : 0);
                break;
        }
    }
}
