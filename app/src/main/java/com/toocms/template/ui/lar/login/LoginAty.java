package com.toocms.template.ui.lar.login;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.toocms.frame.tool.Commonly;
import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.config.User;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.lar.registerphone.RegisterPhoneAty;

import org.xutils.x;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zero.android.common.view.KeyboardLayout;

/**
 * 登录
 * <p>
 * 用法：在注有调用登录方法的地方调用登录接口
 *
 * @author Zero
 * @date 2016/7/8 14:07
 */
public class LoginAty extends BaseAty<LoginView, LoginPresenterImpl> implements KeyboardLayout.OnSoftKeyboardListener, LoginView {

    @BindView(R.id.login_keyboard)
    KeyboardLayout keyboardLayout;
    @BindView(R.id.login_logo)
    ImageView imageView;
    @BindView(R.id.login_account)
    EditText etxtAccount;
    @BindView(R.id.login_psd)
    EditText etxtPassword;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("登录");
        keyboardLayout.setOnSoftKeyboardListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_login;
    }

    @Override
    protected LoginPresenterImpl getPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
    }

    @OnClick({R.id.login, R.id.login_forget})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login: // 登录
                presenter.login(Commonly.getViewText(etxtAccount), Commonly.getViewText(etxtPassword));
                break;
            case R.id.login_forget: // 忘记密码
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_register:
                presenter.onClickRegister();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShown() {
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void onHidden() {
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginSuccess(User user) {
        application.setUserInfo(user);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, DataSet.getInstance().getAppConfig().isUseSnackBar() ? 2000 : 0);
    }

    @Override
    public void openRegister() {
        startActivity(RegisterPhoneAty.class, null);
    }
}
