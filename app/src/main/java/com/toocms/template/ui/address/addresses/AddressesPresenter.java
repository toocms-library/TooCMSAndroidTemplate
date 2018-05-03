package com.toocms.template.ui.address.addresses;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.modle.address.Address;

/**
 * 地址列表Presenter层接口
 * <p>
 * 该类的作用是融合M和V，是真正会与Activity交互的类
 * <p>
 * Author：Zero
 * Date：2017/4/26 18:14
 *
 * @version v2.0
 */
public abstract class AddressesPresenter<V> extends BasePresenter<V> {

    /**
     * 加载地址列表
     *
     * @param isShowLoading
     */
    abstract void loadAddressList(boolean isShowLoading);

    /**
     * 设置默认地址
     *
     * @param address
     */
    abstract void setDefaultAddress(Address address);

    /**
     * 删除地址
     *
     * @param address
     */
    abstract void deleteAddress(Address address);

    /**
     * 跳转到添加新地址页
     */
    abstract void openAddNewAddress();

    /**
     * 跳转到编辑地址页
     */
    abstract void openEditAddress(String address_id);

    /**
     * 选择一个地址项
     */
    abstract void onSelectItem(int position);
}
