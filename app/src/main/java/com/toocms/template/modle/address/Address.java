package com.toocms.template.modle.address;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author：Zero
 * Date：2017/4/26 17:46
 *
 * @version v2.0
 */

public class Address implements Serializable {

    /**
     * address_id : 14
     * region_name : 天津
     * area_name : 河东区
     * ress : 1710
     * tel : 18722687861
     * name : 金达兰花
     * region_id : 343
     * area_id : 2916
     * def_address : 0
     */

    private String address_id;
    private String region_name;
    private String area_name;
    private String ress;
    private String tel;
    private String name;
    private String region_id;
    private String area_id;
    @SerializedName("def_address")
    private String def;

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getRess() {
        return ress;
    }

    public void setRess(String ress) {
        this.ress = ress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getDef_address() {
        return def;
    }

    public void setDef_address(String def_address) {
        this.def = def_address;
    }
}
