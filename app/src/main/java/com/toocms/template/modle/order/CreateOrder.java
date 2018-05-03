package com.toocms.template.modle.order;

/**
 * 提交订单需要的参数
 * Author：Zero
 * Date：2017/5/19 16:50
 *
 * @version v2.0
 */
public class CreateOrder {

    private String member_id;
    private String cart_id;
    private String address_id;
    private String freight_time;
    private String information;
    private String region_id;
    private String amount;
    private String freight_price;
    private String coupon_id;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getFreight_time() {
        return freight_time;
    }

    public void setFreight_time(String freight_time) {
        this.freight_time = freight_time;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(String freight_price) {
        this.freight_price = freight_price;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    @Override
    public String toString() {
        return "CreateOrder{" +
                "member_id='" + member_id + '\'' +
                ", cart_id='" + cart_id + '\'' +
                ", address_id='" + address_id + '\'' +
                ", freight_time='" + freight_time + '\'' +
                ", information='" + information + '\'' +
                ", region_id='" + region_id + '\'' +
                ", amount='" + amount + '\'' +
                ", freight_price='" + freight_price + '\'' +
                ", coupon_id='" + coupon_id + '\'' +
                '}';
    }
}
