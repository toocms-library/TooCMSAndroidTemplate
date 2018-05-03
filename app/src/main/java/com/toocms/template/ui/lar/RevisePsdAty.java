package com.toocms.template.ui.lar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.toocms.frame.tool.Commonly;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.ui.BaseAty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改登录密码
 *
 * @author Zero
 * @date 2016/7/15 10:43
 */
public class RevisePsdAty extends BaseAty {

    @BindView(R.id.revise_psd_old_psd)
    EditText etxtOldpsd;
    @BindView(R.id.revise_psd_newpsd)
    EditText etxtNewpsd;
    @BindView(R.id.revise_psd_renewpsd)
    EditText etxtRenewpsd;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("修改登录密码");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_revise_psd;
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

    @OnClick({R.id.revise_psd_sure})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.revise_psd_sure:
                if (TextUtils.isEmpty(Commonly.getViewText(etxtOldpsd))) {
                    showToast("请输入原密码");
                    return;
                } else if (TextUtils.isEmpty(Commonly.getViewText(etxtNewpsd))) {
                    showToast("请输入新密码");
                    return;
                } else if (TextUtils.equals(Commonly.getViewText(etxtNewpsd), Commonly.getViewText(etxtRenewpsd))) {
                    showToast("两次输入的密码不一样");
                    return;
                }
                // ================================== 调用修改密码接口 ===============================

                break;
        }
    }

//    @Override
//    public void onComplete(RequestParams params, String result) {
//        showToast(JSONUtils.parseKeyAndValueToMap(result).get(Constants.MESSAGE));
//        // ======================= 处理返回信息（无需处理可以略过） ==========================
//
//        // 此处为处理显示信息之后延时关闭页面
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                finish();
//            }
//        }, x.config().isUseSnackBar() ? 2000 : 0);
//        super.onComplete(params, result);
//    }
}
