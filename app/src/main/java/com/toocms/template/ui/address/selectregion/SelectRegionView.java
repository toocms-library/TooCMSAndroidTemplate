package com.toocms.template.ui.address.selectregion;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.address.AddressTemp;
import com.toocms.template.modle.address.Region;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/4 11:59
 *
 * @version v2.0
 */
public interface SelectRegionView extends BaseView {

    /**
     * 显示地区列表
     *
     * @param data
     */
    void showRegionList(List<Region> data);

    /**
     * 选择一项地区
     *
     * @param temp
     */
    void selectRegionItem(AddressTemp temp);
}
