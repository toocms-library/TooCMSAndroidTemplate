package com.toocms.template.modle.coupon;

import java.io.Serializable;

/**
 * Author：Zero
 * Date：2017/5/23 12:41
 *
 * @version v2.0
 */

public class Coupon implements Serializable {

    /**
     * coupon_id : 91
     * pay_type : 在线支付专享
     * price : 100.00
     * price_sub : 15.00
     * create_time : 1495514343
     * end_type : 0
     * end_value : 0
     * used : 0
     * goods_type : 所有商品可用
     * end_time : 永久有效
     */

    private String coupon_id;
    private String pay_type;
    private String price;
    private String price_sub;
    private String create_time;
    private String end_type;
    private String end_value;
    private String used;
    private String goods_type;
    private String end_time;

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_sub() {
        return price_sub;
    }

    public void setPrice_sub(String price_sub) {
        this.price_sub = price_sub;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEnd_type() {
        return end_type;
    }

    public void setEnd_type(String end_type) {
        this.end_type = end_type;
    }

    public String getEnd_value() {
        return end_value;
    }

    public void setEnd_value(String end_value) {
        this.end_value = end_value;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
