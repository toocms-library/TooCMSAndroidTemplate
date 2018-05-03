package com.toocms.template.ui.address.selectcity;

import com.toocms.template.modle.address.Region;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/4/28 16:52
 *
 * @version v2.0
 */

public interface SelectCityInteractor {

    interface OnRequestFinishedListener {
        void onGetRegionListFinished(List<Region> data);
    }

    /**
     * 选择市区列表
     *
     * @param listener
     */
    void getRegionList(OnRequestFinishedListener listener);
}
