package com.toocms.template.ui.address.addressdetail;

import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.address.Address;

/**
 * Author：Zero
 * Date：2017/4/28 12:27
 *
 * @version v2.0
 */

public interface AddressDetailInteractor {

    interface OnRequestFinishedListener {
        /**
         * 单条地址信息
         *
         * @param data
         */
        void onGetAddressDetailFinished(TooCMSResponse<Address> data);

        /**
         * 保存地址
         */
        void onSaveAddressFinished(String message);
    }

    /**
     * 单条地址信息
     *
     * @param member_id
     * @param address_id
     */
    void getAddressDetail(String member_id, String address_id, OnRequestFinishedListener listener);

    /**
     * 修改地址
     *
     * @param address
     */
    void editAddress(String member_id, Address address, OnRequestFinishedListener listener);

    /**
     * 添加地址
     *
     * @param member_id
     * @param address
     * @param listener
     */
    void addNewAddress(String member_id, Address address, OnRequestFinishedListener listener);
}
