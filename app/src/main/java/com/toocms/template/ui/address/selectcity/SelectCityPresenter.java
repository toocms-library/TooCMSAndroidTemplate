package com.toocms.template.ui.address.selectcity;

import android.content.Intent;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.modle.address.Region;

/**
 * Author：Zero
 * Date：2017/4/28 17:05
 *
 * @version v2.0
 */

public abstract class SelectCityPresenter<T> extends BasePresenter<T> {

    /**
     * 加载地址列表
     */
    abstract void loadCityList();

    /**
     * 选择一个城市项
     */
    abstract void selectCityItem(int position);

    /**
     * onActivityResult回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    abstract void result(int requestCode, int resultCode, Intent data);
}
