package com.toocms.template.ui.address.addresses;

import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.address.Address;

import java.util.List;

/**
 * 地址列表Modle层接口
 * <p>
 * 该接口的作用是处理一些网络请求
 * <p>
 * Author：Zero
 * Date：2017/4/26 16:42
 */
public interface AddressesInteractor {

    /**
     * 请求完毕监听
     */
    interface OnRequestFinishedListener {
        /**
         * 我的地址列表
         *
         * @param data
         */
        void onGetAddressListFinished(List<Address> data);

        /**
         * 删除地址
         *
         * @param message
         */
        void onDeleteAddressListFinished(String message);

        /**
         * 设为默认地址
         *
         * @param message
         */
        void onSetDefaultAddressFinished(String message);
    }

    /**
     * 我的地址列表
     *
     * @param member_id
     * @param listener
     */
    void getAddressList(String member_id, OnRequestFinishedListener listener);

    /**
     * 删除地址
     *
     * @param member_id
     * @param address_id
     * @param listener
     */
    void deleteAddress(String member_id, String address_id, OnRequestFinishedListener listener);

    /**
     * 设为默认地址
     *
     * @param member_id
     * @param address_id
     * @param listener
     */
    void setDefaultAddress(String member_id, String address_id, OnRequestFinishedListener listener);
}
