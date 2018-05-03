package com.toocms.template.modle.address;

import java.io.Serializable;

/**
 * Author：Zero
 * Date：2017/4/28 16:58
 *
 * @version v2.0
 */
public class Region implements Serializable {

    /**
     * region_id : 394
     * region_name : 重庆
     */

    private String region_id;
    private String region_name;

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
