package com.toocms.template.ui.lar.registerpsd;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.tool.AppManager;
import com.toocms.frame.tool.Commonly;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.lar.registerphone.RegisterPhoneAty;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 注册 - 设置密码
 *
 * @author Zero
 * @date 2016/7/11 17:44
 */
public class RegisterPsdAty extends BaseAty {

    @BindView(R.id.register_psd)
    EditText etxtPsd;
    @BindView(R.id.register_repsd)
    EditText etxtRepsd;

    private String account;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("注册");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_register_psd;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initialized() {
        account = getIntent().getExtras().getString("account");
    }

    @Override
    protected void requestData() {
    }

    @OnClick({R.id.register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if (Commonly.getViewText(etxtPsd).length() < 6) {
                    showToast("请填写6位以上密码");
                    return;
                } else if (!TextUtils.equals(Commonly.getViewText(etxtPsd), Commonly.getViewText(etxtRepsd))) {
                    showToast("两次填写的密码不一致");
                    return;
                }
                showProgress();
                HttpParams params = new HttpParams();
                params.put("account", account);
                params.put("password", Commonly.getViewText(etxtPsd));
                params.put("check", Commonly.getViewText(etxtRepsd));
                new ApiTool<TooCMSResponse<Void>>().postApi("Secure/register", params, new ApiListener<TooCMSResponse<Void>>() {
                    @Override
                    public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                        showToast(data.getMessage());
//                         ============= 如果调用的注册接口返回值为用户信息则取消下列代码的注释 =============
//                        Config.setLoginState(true);
//                        application.setUserInfo(JSONUtils.parseDataToMap(result));
//                        AppManager.getInstance().killActivity(LoginAty.class);
                        AppManager.getInstance().killActivity(RegisterPhoneAty.class);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, DataSet.getInstance().getAppConfig().isUseSnackBar() ? 2000 : 0);
                    }
                });
                break;
        }
    }
}
