package com.toocms.template.ui.address.selectregion;

import com.toocms.template.modle.address.Region;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/4 13:13
 *
 * @version v2.0
 */

public interface SelectRegionInteractor {

    interface OnRequestFinishedListener {
        void onGetRegionListFinished(List<Region> data);
    }

    /**
     * 获取地区列表
     *
     * @param type
     * @param region_id
     * @param listener
     */
    void getRegionList(String type, String region_id, OnRequestFinishedListener listener);
}
