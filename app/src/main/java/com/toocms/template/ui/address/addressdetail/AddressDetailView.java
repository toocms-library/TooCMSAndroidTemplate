package com.toocms.template.ui.address.addressdetail;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.address.Address;

/**
 * Author：Zero
 * Date：2017/4/28 14:19
 *
 * @version v2.0
 */

public interface AddressDetailView extends BaseView {

    /**
     * 设置标题
     *
     * @param title
     */
    void setTitle(String title);

    /**
     * 跳转到选择城市页
     */
    void openSelectCity();

    /**
     * 显示地址详情
     *
     * @param address
     */
    void showAddressDetail(Address address);

    /**
     * 保存地址完毕
     */
    void onSaveAddressFinished();

    /**
     * 显示输入框为空时的提示
     */
    void showEmptyError(EmptyError emptyError, String error);

    /**
     * 显示区域
     *
     * @param area
     */
    void showArea(String area);
}
