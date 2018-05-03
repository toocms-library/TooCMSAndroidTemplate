package com.toocms.template.ui.address.selectregion;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.modle.address.Region;

/**
 * Author：Zero
 * Date：2017/5/4 13:20
 *
 * @version v2.0
 */

public abstract class SelectRegionPresenter<T> extends BasePresenter<T> {

    /**
     * 加载地区列表
     */
    abstract void loadRegionList();

    /**
     * 选择一项地区
     *
     * @param position
     */
    abstract void selectRegionItem(int position);
}
