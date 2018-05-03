package com.toocms.template.ui.mine.settings;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.toocms.frame.config.LoginStatus;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.frame.update.UpdateManager;
import com.toocms.template.R;
import com.toocms.template.config.Constants;
import com.toocms.template.ui.BaseAty;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zero.android.common.permission.PermissionFail;
import cn.zero.android.common.permission.PermissionSuccess;
import cn.zero.android.common.util.FileManager;

/**
 * 设置
 * <p>
 * 用法：1、如需拨打电话功能把tvPhone控件中的文字设置成要拨打的电话即可
 * 2、
 *
 * @author Zero
 * @date 2016/7/15 11:41
 */
public class SettingsAty extends BaseAty {

    @BindView(R.id.settings_phone)
    TextView tvPhone;
    @BindView(R.id.settings_version)
    TextView tvVersion;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("设置");
        try {
            tvVersion.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_settings;
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

    @OnClick({R.id.settings_feedback, R.id.settings_about, R.id.settings_clear, R.id.settings_service, R.id.settings_update, R.id.settings_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_feedback: // 意见反馈
                startActivity(FeedbackAty.class, null);
                break;
            case R.id.settings_about: // 关于我们
                startActivity(AboutAty.class, null);
                break;
            case R.id.settings_clear: // 清除缓存
                showProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FileManager.clearCacheFiles();
                        removeProgress();
                        showToast("清理完毕");
                    }
                }, 1000);
                break;
            case R.id.settings_service: // 客服电话
                showDialog("提示", "是否要拨打客服电话？", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(Constants.PERMISSIONS_CALL_PHONE, Manifest.permission.CALL_PHONE);
                    }
                }, null);
                break;
            case R.id.settings_update: // 版本信息
                // ======================= 修改AppConfig.UPDATE_URL的值即可 ========================
                UpdateManager.checkUpdate(true);
                break;
            case R.id.settings_exit: // 退出登录
                LoginStatus.setLogin(false);
                finish();
                break;
        }
    }

    @PermissionSuccess(requestCode = Constants.PERMISSIONS_CALL_PHONE)
    public void requestSuccess() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvPhone.getText().toString()));
        startActivity(intent);
    }

    @PermissionFail(requestCode = Constants.PERMISSIONS_CALL_PHONE)
    public void requestFailure() {
        showToast("请求权限失败，暂时无法拨打电话");
    }
}
