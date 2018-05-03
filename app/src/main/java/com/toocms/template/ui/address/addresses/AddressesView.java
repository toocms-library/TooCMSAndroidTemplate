package com.toocms.template.ui.address.addresses;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.address.Address;
import com.toocms.template.modle.order.ConfirmOrder;

import java.util.List;

/**
 * 地址列表View层接口
 * <p>
 * 此接口需被Activity类实现，作用是处理页面逻辑，所以这里的所有方法都为页面处理方法
 * <p>
 * Author：Zero
 * Date：2017/4/26 17:57
 *
 * @version v2.0
 */

interface AddressesView extends BaseView {

    /**
     * 展示地址列表
     *
     * @param data
     */
    void showAddressList(List<Address> data);

    /**
     * 跳转到添加新地址页
     */
    void openAddNewAddress();

    /**
     * 跳转到编辑地址页
     */
    void openEditAddress(String address_id);

    /**
     * 选择一个地址项
     */
    void onSelectItem(ConfirmOrder.AddressBean address);
}
