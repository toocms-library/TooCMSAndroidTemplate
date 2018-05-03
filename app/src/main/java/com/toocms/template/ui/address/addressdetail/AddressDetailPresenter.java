package com.toocms.template.ui.address.addressdetail;

import android.content.Intent;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.modle.address.Address;

/**
 * Author：Zero
 * Date：2017/4/28 14:22
 *
 * @version v2.0
 */
public abstract class AddressDetailPresenter<T> extends BasePresenter<T> {

    /**
     * 设置页面类型，是否为添加地址
     *
     * @param isAdd
     */
    abstract void setType(boolean isAdd);

    /**
     * 加载地址详情
     */
    abstract void loadAddress();

    /**
     * 保存地址
     *
     * @param name
     * @param tel
     * @param ress
     * @param isDefault
     */
    abstract void saveAddress(String name, String tel, String ress, boolean isDefault);

    /**
     * 跳转到选择城市页
     */
    abstract void openSelectCity();

    /**
     * onActivityResult回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    abstract void result(int requestCode, int resultCode, Intent data);
}
