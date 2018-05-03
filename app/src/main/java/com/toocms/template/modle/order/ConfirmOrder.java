package com.toocms.template.modle.order;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/19 16:36
 *
 * @version v2.0
 */

public class ConfirmOrder {

    /**
     * address : {"id":"18","name":"刘冰","tel":"15822829206","city_id":"343","city_name":"天津","address":"天津河北区君临天下1710"}
     * freight_time : ["尽快送达","8:00-11:00","9:00-12:00","13:00-22:00"]
     * goods : [{"cart_id":"835","number":"9","type":"3","goods_id":"89","name":"进口美国红提 (11.8/斤) (2斤装）","cover":"http://xsd-img.toocms.com//Uploads/goods/2017-01-04/586ca434cc179.jpg","price":"27.00","category_id":"6","specify_name":"","parent_id":"3"},{"cart_id":"834","number":"26","type":"3","goods_id":"70","name":"砂糖贡桔（8.99/斤) (1斤装精品）","cover":"http://xsd-img.toocms.com//Uploads/goods/2017-01-04/586c6b7229c7b.jpg","price":"8.99","category_id":"4","specify_name":"","parent_id":"3"},{"cart_id":"819","number":"4","type":"3","goods_id":"132","name":"苹果胡萝卜汁 (2个红富士+1根胡萝卜组合）","cover":"http://xsd-img.toocms.com//Uploads/goods/2017-01-10/5874f497c50ab.jpg","price":"6.80","category_id":"13","specify_name":"","parent_id":"11"}]
     * def_freight : 0.00
     * no_freight : 0.00
     * balance : 3511.20
     */

    private AddressBean address;
    private String def_freight;
    private String no_freight;
    private String balance;
    private List<String> freight_time;
    private List<GoodsBean> goods;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getDef_freight() {
        return def_freight;
    }

    public void setDef_freight(String def_freight) {
        this.def_freight = def_freight;
    }

    public String getNo_freight() {
        return no_freight;
    }

    public void setNo_freight(String no_freight) {
        this.no_freight = no_freight;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<String> getFreight_time() {
        return freight_time;
    }

    public void setFreight_time(List<String> freight_time) {
        this.freight_time = freight_time;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class AddressBean implements Serializable {
        /**
         * id : 18
         * name : 刘冰
         * tel : 15822829206
         * city_id : 343
         * city_name : 天津
         * address : 天津河北区君临天下1710
         */

        private String id;
        private String name;
        private String tel;
        private String city_id;
        private String city_name;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class GoodsBean {
        /**
         * cart_id : 835
         * number : 9
         * type : 3
         * goods_id : 89
         * name : 进口美国红提 (11.8/斤) (2斤装）
         * cover : http://xsd-img.toocms.com//Uploads/goods/2017-01-04/586ca434cc179.jpg
         * price : 27.00
         * category_id : 6
         * specify_name :
         * parent_id : 3
         */

        private String cart_id;
        private String number;
        private String type;
        private String goods_id;
        private String name;
        private String cover;
        private String price;
        private String category_id;
        private String specify_name;
        private String parent_id;

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getSpecify_name() {
            return specify_name;
        }

        public void setSpecify_name(String specify_name) {
            this.specify_name = specify_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }
}
