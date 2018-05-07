package com.toocms.template.ui.mine.user;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.toocms.frame.image.ImageLoader;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.config.Constants;
import com.toocms.template.config.DataSet;
import com.toocms.template.config.User;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.lar.RevisePsdAty;
import com.toocms.template.ui.mine.phone.ValidatePhoneAty;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zero.android.common.permission.PermissionSuccess;
import cn.zero.android.common.util.ListUtils;

/**
 * 个人信息
 * <p>
 * 用法：在指定地方调用接口
 *
 * @author Zero
 * @date 2016/7/19 11:27
 */
public class UserInfoAty extends BaseAty {

    @BindView(R.id.userinfo_head)
    ImageView imageView;
    @BindView(R.id.userinfo_nickname)
    TextView tvName;
    @BindView(R.id.userinfo_phone)
    TextView tvPhone;

    private ImageLoader imageLoader;
    private User user;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("个人信息");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ================= 设置头像和昵称，真实项目中换成调用接口获取 ====================
        String head = user.getCover();
        ImageLoader.loadUrl2CircleImage(glide, head, imageView, R.drawable.ic_default_head);
        tvName.setText(user.getNickname());
        tvPhone.setText(user.getAccount());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_userinfo;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initialized() {
        user = DataSet.getInstance().getUser();
    }

    @Override
    protected void requestData() {
    }

    @OnClick({R.id.userinfo_head, R.id.userinfo_nickname_click, R.id.userinfo_editpsd_click, R.id.userinfo_phone_click})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userinfo_head: // 头像
                requestPermissions(Constants.PERMISSIONS_WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.userinfo_nickname_click: // 昵称
                startActivity(ReviseNameAty.class, null);
                break;
            case R.id.userinfo_editpsd_click: // 密码
                startActivity(RevisePsdAty.class, null);
                break;
            case R.id.userinfo_phone_click: // 手机号
                startActivity(ValidatePhoneAty.class, null);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case com.toocms.frame.config.Constants.SELECT_IMAGE:
                ArrayList<String> list = getSelectImagePath(data);
                if (!ListUtils.isEmpty(list)) {
                    // ========================== 此处调用修改头像接口（以下代码放到onComplete中） =========================
                    // 记录所选择的头像地址，实际项目中需要改成接口返回的头像地址
                    user.setCover(list.get(0));
                    application.setUserInfo(user);
                }
                break;
        }
    }

//    @Override
//    public void onComplete(RequestParams params, String result) {
//        // 修改头像
//        if (params.getUri().contains("editHead")) {
//            Map<String, String> map = JSONUtils.parseKeyAndValueToMap(result);
//            showToast(map.get(Constants.MESSAGE));
//            String head = map.get("data");
//            application.setUserInfoItem("head", head);
//            imageLoader.disPlay(imageView, head);
//        }
//        super.onComplete(params, result);
//    }

    @PermissionSuccess(requestCode = Constants.PERMISSIONS_WRITE_EXTERNAL_STORAGE)
    public void requestSuccess() {
        startSelectSignImageAty();
    }
}
