package com.toocms.template.ui.address.addressdetail;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.toocms.frame.tool.Commonly;
import com.toocms.template.R;
import com.toocms.template.config.DataSet;
import com.toocms.template.modle.address.Address;
import com.toocms.template.ui.BaseAty;
import com.toocms.template.ui.address.selectcity.SelectCityAty;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zero.android.common.util.StringUtils;

/**
 * 地址详情
 * <p>
 * 用法：直接在指定位置调用接口即可
 *
 * @author Zero
 * @date 2016/7/12 17:59
 */
public class AddressDetailAty extends BaseAty<AddressDetailView, AddressDetailPresenterImpl> implements AddressDetailView {

    public static final int REQUEST_LOCATION = 0x21;

    @BindView(R.id.address_detail_name)
    EditText etxtName;
    @BindView(R.id.address_detail_phone)
    EditText etxtPhone;
    @BindView(R.id.address_detail_area)
    TextView tvArea;
    @BindView(R.id.address_detail)
    EditText etxtDetail;
    @BindView(R.id.address_detail_default)
    CheckBox checkBox;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        presenter.setType(StringUtils.equals(getIntent().getExtras().getString("flag"), "add"));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_address_detail;
    }

    @Override
    protected AddressDetailPresenterImpl getPresenter() {
        return new AddressDetailPresenterImpl(getIntent().getExtras().getString("address_id"));
    }

    @Override
    protected void initialized() {
    }

    @Override
    protected void requestData() {
        presenter.loadAddress();
    }

    @OnClick({R.id.address_area, R.id.address_detail_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_area:
                presenter.openSelectCity();
                break;
            case R.id.address_detail_save:
                presenter.saveAddress(Commonly.getViewText(etxtName),
                        Commonly.getViewText(etxtPhone),
                        Commonly.getViewText(etxtDetail),
                        checkBox.isChecked());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.result(requestCode, resultCode, data);
    }

    @Override
    public void setTitle(String title) {
        mActionBar.setTitle(title);
    }

    @Override
    public void openSelectCity() {
        startActivityForResult(SelectCityAty.class, null, REQUEST_LOCATION);
    }

    @Override
    public void showAddressDetail(Address address) {
        etxtName.setText(address.getName());
        etxtPhone.setText(address.getTel());
        tvArea.setText(address.getRegion_name() + address.getArea_name());
        etxtDetail.setText(address.getRess());
        checkBox.setChecked(StringUtils.equals(address.getDef_address(), "1"));
    }

    @Override
    public void onSaveAddressFinished() {
        // 此处为处理显示信息之后延时关闭页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, DataSet.getInstance().getAppConfig().isUseSnackBar() ? 2000 : 0);
    }

    @Override
    public void showEmptyError(EmptyError emptyError, String error) {
        switch (emptyError) {
            case NAME:
                etxtName.setError(error);
                break;
            case PHONE:
                etxtPhone.setError(error);
                break;
            case AREA:
                tvArea.setError(error);
                tvArea.setFocusable(true);
                tvArea.setFocusableInTouchMode(true);
                tvArea.requestFocus();
                break;
            case DETAIL:
                etxtDetail.setError(error);
                break;
        }
    }

    @Override
    public void showArea(String area) {
        tvArea.setText(area);
        tvArea.setError(null);
    }
}
