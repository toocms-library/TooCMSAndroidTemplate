package com.toocms.template.config;

import org.xutils.x;

import cn.zero.android.common.util.PreferencesUtils;

/**
 * 项目缓存
 * <p>
 * 该类主要作用为缓存一些项目中用到的信息
 * Author：Zero
 * Date：2017/5/16 15:12
 *
 * @version v2.0
 */

public class ProjectCache {

    /**
     * 存储城市id
     *
     * @param cityId
     */
    public static final void saveCityId(String cityId) {
        PreferencesUtils.putString(x.app(), "cityId", cityId);
    }

    /**
     * 获取城市id
     *
     * @return
     */
    public static final String getCityId() {
        return PreferencesUtils.getString(x.app(), "cityId");
    }
}
