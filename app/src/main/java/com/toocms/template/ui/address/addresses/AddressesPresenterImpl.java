package com.toocms.template.ui.address.addresses;

import com.toocms.template.config.DataSet;
import com.toocms.template.modle.address.Address;
import com.toocms.template.modle.order.ConfirmOrder;

import java.util.List;

import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.util.StringUtils;

/**
 * 地址列表Presenter层抽象类的实现类
 * <p>
 * Author：Zero
 * Date：2017/4/27 11:43
 *
 * @version v2.0
 */
class AddressesPresenterImpl extends AddressesPresenter<AddressesView> implements AddressesInteractor.OnRequestFinishedListener {

    private AddressesInteractor interactor;

    private List<Address> list; // 地址信息集合
    private String member_id; // 用户ID
    private int position; // 删除的地址的位置

    public AddressesPresenterImpl() {
        this.member_id = DataSet.getInstance().getUser().getMember_id();
        interactor = new AddressesInteractorImpl();
    }

    @Override
    void loadAddressList(boolean isShowLoading) {
        if (isShowLoading) view.showProgress();
        interactor.getAddressList(member_id, this);
    }

    @Override
    void openAddNewAddress() {
        view.openAddNewAddress();
    }

    @Override
    void setDefaultAddress(Address address) {
        position = list.indexOf(address); // 点击时获取该项的位置（设置默认收货地址接口调用成功之后会用到）
        view.showProgress();
        interactor.setDefaultAddress(member_id, address.getAddress_id(), this);
    }

    @Override
    void openEditAddress(String address_id) {
        view.openEditAddress(address_id);
    }

    @Override
    void onSelectItem(int position) {
        Address address = list.get(position);
        ConfirmOrder.AddressBean bean = new ConfirmOrder.AddressBean();
        bean.setId(address.getAddress_id());
        bean.setAddress(address.getRegion_name() + address.getArea_name() + address.getRess());
        bean.setName(address.getName());
        bean.setTel(address.getTel());
        view.onSelectItem(bean);
    }

    @Override
    void deleteAddress(Address address) {
        position = list.indexOf(address); // 点击时获取该项的位置（删除接口调用成功之后会用到）
        view.showProgress();
        interactor.deleteAddress(member_id, address.getAddress_id(), this);
    }

    @Override
    public void onGetAddressListFinished(List<Address> data) {
        list = data;
        view.showAddressList(data);
    }

    @Override
    public void onDeleteAddressListFinished(String message) {
        view.showToast(message);
        list.remove(position);
        view.showAddressList(list);
    }

    @Override
    public void onSetDefaultAddressFinished(String message) {
        view.showToast(message);
        for (int i = 0; i < ListUtils.getSize(list); i++) {
            if (position == i) {
                if (StringUtils.equals(list.get(i).getDef_address(), "1")) {
                    list.get(i).setDef_address("0");
                } else {
                    list.get(i).setDef_address("1");
                }
            } else {
                list.get(i).setDef_address("0");
            }
        }
        view.showAddressList(list);
    }
}
