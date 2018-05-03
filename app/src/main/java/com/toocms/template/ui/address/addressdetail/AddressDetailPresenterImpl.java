package com.toocms.template.ui.address.addressdetail;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.config.DataSet;
import com.toocms.template.modle.address.Address;
import com.toocms.template.modle.address.AddressTemp;

/**
 * Author：Zero
 * Date：2017/4/28 14:47
 *
 * @version v2.0
 */
public class AddressDetailPresenterImpl extends AddressDetailPresenter<AddressDetailView> implements AddressDetailInteractor.OnRequestFinishedListener {

    private AddressDetailInteractor interactor;

    private Address address; // 地址对象
    private AddressTemp temp; // 城市地区信息
    private String member_id; // 用户ID
    private String address_id; // 地址ID
    private boolean isAdd; // 是否为添加地址

    public AddressDetailPresenterImpl(String address_id) {
        this.member_id = DataSet.getInstance().getUser().getMember_id();
        this.address_id = address_id;
        interactor = new AddressDetailInteractorImpl();
    }

    @Override
    void setType(boolean isAdd) {
        this.isAdd = isAdd;
        view.setTitle(isAdd ? "添加地址" : "编辑地址");
    }

    @Override
    void loadAddress() {
        if (isAdd) return;
        view.showProgress();
        interactor.getAddressDetail(member_id, address_id, this);
    }

    @Override
    void saveAddress(String name, String tel, String ress, boolean isDefault) {
        if (TextUtils.isEmpty(name)) {
            view.showEmptyError(EmptyError.NAME, "请输入收货人姓名");
            return;
        } else if (tel.length() < 11) {
            view.showEmptyError(EmptyError.PHONE, "请输入正确的手机号");
            return;
        } else if (TextUtils.isEmpty(temp.address)) {
            view.showEmptyError(EmptyError.AREA, "请选择区域");
            return;
        } else if (TextUtils.isEmpty(ress)) {
            view.showEmptyError(EmptyError.DETAIL, "请输入详细地址");
            return;
        }
        if (isAdd) address = new Address();
        address.setName(name);
        address.setTel(tel);
        address.setArea_id(temp.region_id);
        address.setRegion_id(temp.area_id);
        address.setArea_name(temp.address);
        address.setRess(ress);
        address.setDef_address(isDefault ? "1" : "0");
        view.showProgress();
        if (isAdd)
            interactor.addNewAddress(member_id, address, this);
        else
            interactor.editAddress(member_id, address, this);
    }

    @Override
    void openSelectCity() {
        view.openSelectCity();
    }

    @Override
    void result(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case AddressDetailAty.REQUEST_LOCATION:
                if (null != data) {
                    temp = (AddressTemp) data.getSerializableExtra("address");
                    view.showArea(temp.address);
                }
                break;
        }
    }

    @Override
    public void onGetAddressDetailFinished(TooCMSResponse<Address> data) {
        address = data.getData();
        temp = new AddressTemp();
        temp.area_id = data.getData().getArea_id();
        temp.region_id = data.getData().getRegion_id();
        temp.address = data.getData().getRegion_name() + data.getData().getArea_name();
        view.showAddressDetail(address);
    }

    @Override
    public void onSaveAddressFinished(String message) {
        view.showToast(message);
        view.onSaveAddressFinished();
    }
}
