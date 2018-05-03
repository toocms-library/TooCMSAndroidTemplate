package com.toocms.template.modle.cart;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/16 11:39
 *
 * @version v2.0
 */

public class Cart {

    /**
     * list : [{"cart_id":"405","number":"1","type":"3","goods_id":"100","name":"皇冠水晶梨 (3.6/斤) (3个装精品）","cover":"http://xsd-img.toocms.com//Uploads/goods/2017-01-04/586cb8fe0b466.jpg","price":"5.80","category_id":"4","specify_name":"","parent_id":"3"},{"cart_id":"44","number":"1","type":"3","goods_id":null,"name":null,"cover":"","price":null,"category_id":null,"specify_name":"","parent_id":null},{"cart_id":"42","number":"1","type":"3","goods_id":null,"name":null,"cover":"","price":null,"category_id":null,"specify_name":"","parent_id":null}]
     * no_freight : 0.00
     */

    private String no_freight;
    private List<ListBean> list;

    public String getNo_freight() {
        return no_freight;
    }

    public void setNo_freight(String no_freight) {
        this.no_freight = no_freight;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * cart_id : 405
         * number : 1
         * type : 3
         * goods_id : 100
         * name : 皇冠水晶梨 (3.6/斤) (3个装精品）
         * cover : http://xsd-img.toocms.com//Uploads/goods/2017-01-04/586cb8fe0b466.jpg
         * price : 5.80
         * category_id : 4
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
        private boolean is_selected;  // 此为自定义属性，用于标识是否为选中状态

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

        public boolean is_selected() {
            return is_selected;
        }

        public void setIs_selected(boolean is_selected) {
            this.is_selected = is_selected;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cart_id);
            dest.writeString(this.number);
            dest.writeString(this.type);
            dest.writeString(this.goods_id);
            dest.writeString(this.name);
            dest.writeString(this.cover);
            dest.writeString(this.price);
            dest.writeString(this.category_id);
            dest.writeString(this.specify_name);
            dest.writeString(this.parent_id);
            dest.writeByte(this.is_selected ? (byte) 1 : (byte) 0);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.cart_id = in.readString();
            this.number = in.readString();
            this.type = in.readString();
            this.goods_id = in.readString();
            this.name = in.readString();
            this.cover = in.readString();
            this.price = in.readString();
            this.category_id = in.readString();
            this.specify_name = in.readString();
            this.parent_id = in.readString();
            this.is_selected = in.readByte() != 0;
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };

        @Override
        public String toString() {
            return "ListBean{" +
                    "cart_id='" + cart_id + '\'' +
                    ", number='" + number + '\'' +
                    ", type='" + type + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", name='" + name + '\'' +
                    ", cover='" + cover + '\'' +
                    ", price='" + price + '\'' +
                    ", category_id='" + category_id + '\'' +
                    ", specify_name='" + specify_name + '\'' +
                    ", parent_id='" + parent_id + '\'' +
                    ", is_selected=" + is_selected +
                    '}';
        }
    }
}
