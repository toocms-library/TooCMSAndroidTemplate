package com.toocms.template.ui.mine.user;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.toocms.frame.tool.Commonly;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.config.User;
import com.toocms.template.ui.BaseAty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改昵称
 *
 * @author Zero
 * @date 2016/7/19 15:42
 */
public class ReviseNameAty extends BaseAty {

    @BindView(R.id.revise_name)
    EditText editText;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("修改昵称");
        editText.setText(DataSet.getInstance().getUser().getNickname());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_revise_name;
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

    @OnClick(R.id.revise_name_save)
    public void onClick(View v) {
        String nickname = Commonly.getViewText(editText);
        if (TextUtils.isEmpty(nickname)) {
            showToast("请输入昵称");
            return;
        }
        // =============================== 调用修改昵称接口 ===========================
        // 模拟数据（需删除）
        User user = new User();
        user.setNickname(nickname);
        application.setUserInfo(user);
        showToast("修改成功");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, DataSet.getInstance().getAppConfig().isUseSnackBar() ? 2000 : 0);
    }

//    @Override
//    public void onComplete(RequestParams params, String result) {
//        showToast(JSONUtils.parseKeyAndValueToMap(result).get(Constants.MESSAGE));
//        // 记录用户信息（注意字段）
//        application.setUserInfoItem("nickname", Commonly.getViewText(editText));
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                finish();
//            }
//        }, Config.isUseSnackBar() ? 2000 : 0);
//        super.onComplete(params, result);
//    }
}
