package com.toocms.template.ui.cart;

import com.toocms.template.R;
import com.toocms.template.config.AppConfig;
import com.toocms.template.config.DataSet;
import com.toocms.template.modle.cart.Cart;
import com.toocms.template.ui.MainAty;
import com.toocms.template.util.CartUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.zero.android.common.util.ListUtils;
import cn.zero.android.common.util.NumberUtils;

/**
 * Author：Zero
 * Date：2017/5/16 11:32
 *
 * @version v2.0
 */

public class CartPresenterImpl extends CartPresenter<CartView> implements CartInteractor.OnRequestFinishedListener {

    private CartInteractor interactor;

    private List<Cart.ListBean> list = new ArrayList(); // 购物车中的商品列表
    private List<Cart.ListBean> removes = new ArrayList<>(); // 将要移除的购物车项
    private List<String> cancelled = new ArrayList<>(); // 购物车项被取消的id
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private String member_id; // 用户ID
    private boolean isEditState; // 是否为编辑状态
    private boolean isAllSelected = true; // 是否为全选状态
    private boolean isPlus; // 购物车是否为添加商品数量
    private double total; // 总价
    private double no_freight; // 包邮价格
    private int position; // 点击的购物车位置

    public CartPresenterImpl() {
        this.member_id = DataSet.getInstance().getUser().getMember_id();
        interactor = new CartInteractorImpl();
    }

    @Override
    void onStateChange(boolean isReset) {
        if (isReset) isEditState = true;
        if (isEditState) {
            view.showStatus("编辑");
            view.showTotal(true, "￥" + decimalFormat.format(total));
            view.showSettle("去结算");
        } else {
            view.showStatus("完成");
            view.showTotal(false, "￥" + decimalFormat.format(total));
            view.showSettle("删除" + (AppConfig.CART_IS_HAVE_DELETE_NUM ? "(" + (ListUtils.getSize(list) - ListUtils.getSize(cancelled)) + ")" : ""));
        }
        isEditState = !isEditState;
        /** 如果下架该商品则不被选中，如需使用取消注释 */
//        if (!isEditState)
//            for (int i = 0; i < ListUtils.getSize(list); i++) {
//                if (AppConfig.CART_IS_HAVE_STATUS)
//                    if (TextUtils.equals(list.get(i).getType(), "0"))
//                        list.get(i).setIs_selected(false);
//            }
        view.showCartList(list);
    }

    @Override
    void loadCartList(String region_id, boolean isShowProgress) {
        if (isShowProgress) view.showProgress();
        interactor.getCartList(member_id, region_id, this);
    }

    @Override
    void onClick(int viewId) {
        switch (viewId) {
            case R.id.cart_settle:
                if (isEditState) { // 删除
                    List<String> selected = getSelectedId();
                    if (ListUtils.isEmpty(selected)) {
                        view.showToast("请选择要删除的商品");
                        return;
                    }
                    // 获取逗号连起来的购物车id，调用删除接口时可能会用到
                    String select = ListUtils.join(selected);
                    // 调用删除购物车商品接口
                    view.showProgress();
                    interactor.deleteCart(member_id, select, this);
                } else { // 结算
                    onClickSettle();
                }
                break;
            case R.id.cart_all: // 全选
                onClickAllSelect();
                break;
        }
    }

    @Override
    void changeSelected(Cart.ListBean cart, boolean isSelected) {
        cart.setIs_selected(isSelected);
        if (!isSelected) cancelled.add(cart.getCart_id());
        else cancelled.remove(cart.getCart_id());
        view.showCartList(list);
        showTotalAndUpdateUI();
    }

    @Override
    void editNumber(Cart.ListBean cart, boolean isPlus) {
        int cart_number = Integer.parseInt(cart.getNumber());
        if (!isPlus) {
            if (cart_number <= 1) return;
        }
        this.isPlus = isPlus;
        this.position = list.indexOf(cart);
        // 调用购物车减数量接口
        view.showProgress();
        interactor.editCartNum(member_id, cart.getCart_id(), isPlus ? ++cart_number : --cart_number, this);
    }

    @Override
    public void onGetCartListFinished(Cart data) {
        list = data.getList();
        // 显示顶部提示以及列表数据
        this.no_freight = NumberUtils.toDouble(data.getNo_freight());
        view.showNotice(data.getNo_freight());
        // 这里直接把空视图隐藏
        view.setIsShowEmptyView(ListUtils.isEmpty(list));
        // 计算购物车数量
        int cartNum = 0;
        for (int i = 0; i < ListUtils.getSize(list); i++) {
            // 获取用户是否之前已经取消过该项的选中状态
            boolean isCancelld = cancelled.contains(list.get(i).getCart_id());
            // 如果开启角标功能的话就把个数加起来
            if (AppConfig.IS_USE_BADGE)
                cartNum += Integer.parseInt(list.get(i).getNumber());
            // 区分一下有上下架和没有上下架，如需要此功能取消注释
//            if (AppConfig.CART_IS_HAVE_STATUS) {
//                if (TextUtils.equals(list.get(i).getType(), "1")) {
//                    // 判断如果当用户取消过该项选中状态那么就把他变成未选中状态
//                    list.get(i).setIs_selected(!isCancelld);
//                } else {
//                    list.get(i).setIs_selected(false);
//                }
//            } else {
//                list.get(i).setIs_selected(!isCancelld);
//            }
            list.get(i).setIs_selected(!isCancelld);
        }
        // 设置底部角标数字（已判断是否启用，不用处理）
        CartUtils.setCartNum(cartNum);
        MainAty.setBadgeNum(cartNum);
        view.showCartList(list);
        showTotalAndUpdateUI();
    }

    @Override
    public void onGetCartListError() {
        view.setIsShowEmptyView(true);
    }

    @Override
    public void onEditCartNumFinished(String message) {
        view.showToast(message);
        int num = Integer.parseInt(list.get(position).getNumber());
        if (!isPlus) {
            list.get(position).setNumber(String.valueOf(--num));
            CartUtils.reduceCartFixedNum(1);
        } else {
            list.get(position).setNumber(String.valueOf(++num));
            CartUtils.plusCartFixedNum(1);
        }
        view.showCartList(list);
        MainAty.setBadgeNum(CartUtils.getCartNum());
        showTotalAndUpdateUI();
    }

    @Override
    public void onDeleteCartFinished(String message) {
        view.showToast(message);
        list.removeAll(removes);
        // 如果设置使用购物车角标则算出被删除的商品个数重新显示
        if (AppConfig.IS_USE_BADGE) {
            int num = 0;
            for (int i = 0; i < ListUtils.getSize(removes); i++) {
                num += Integer.parseInt(removes.get(i).getNumber());
            }
            CartUtils.reduceCartFixedNum(num);
            MainAty.setBadgeNum(CartUtils.getCartNum());
        }
        // 删除商品之后
        // 如果购物车列表不为空则更新购物车列表同时更新总价
        // 如果购物车列表为空则显示空视图
        if (!ListUtils.isEmpty(list)) {
            view.showCartList(list);
            showTotalAndUpdateUI();
        } else {
            view.setIsShowEmptyView(true);
        }
        removes.clear();
    }

    /**
     * 点击全选按钮
     */
    private void onClickAllSelect() {
        isAllSelected = !isAllSelected;
        // 处于编辑状态时
        if (isEditState) {
            // 现状态为全选时，清除所有选择项（换句话意思就是如果现在为全选状态点击改为全不选状态）
            if (isAllSelected) cancelled.clear();
            else
                // 现状态不为全选时，把购物车列表中的商品加入到列表
                for (int i = 0; i < ListUtils.getSize(list); i++) {
                    cancelled.add(list.get(i).getCart_id());
                }
            // 根据全选状态，改变购物车列表每项的选中状态
            for (int i = 0; i < ListUtils.getSize(list); i++) {
                list.get(i).setIs_selected(isAllSelected);
            }
        } else {
            // 处于正常状态时
            // 现状态为全选时，同上
            if (isAllSelected) cancelled.clear();
            else
                // 现状态不为全选时，同上
                for (int i = 0; i < ListUtils.getSize(list); i++) {
                    // 如果有上下架功能，判断一下如果是上架状态才能被选中，如需此功能取消注释
//                    if (AppConfig.CART_IS_HAVE_STATUS) {
//                        if (TextUtils.equals(list.get(i).getType(), "1"))
//                            cancelled.add(list.get(i).getCart_id());
//                    } else {
//                    cancelled.add(list.get(i).getCart_id());
//                    }
                    cancelled.add(list.get(i).getCart_id());
                }
            // 如果点击完全选按钮之后为全选状态，则把购物车列表中的项改为选中状态，反之则改为未选中状态
            for (int i = 0; i < ListUtils.getSize(list); i++) {
                // 上下架功能，如需此功能取消注释
//                if (isAllSelected)
//                    if (AppConfig.CART_IS_HAVE_STATUS) {
//                        if (TextUtils.equals(list.get(i).getType(), "1")) {
//                            list.get(i).setIs_selected(true);
//                        } else {
//                            list.get(i).setIs_selected(false);
//                        }
//                    } else {
//                        list.get(i).setIs_selected(true);
//                    }
                list.get(i).setIs_selected(isAllSelected);
            }
        }
        showTotalAndUpdateUI();
        view.showCartList(list);
    }

    /**
     * 点击结算按钮
     */
    private void onClickSettle() {
        // 将用户勾选的商品添加到列表
        List<String> buy = new ArrayList<>();
        for (Cart.ListBean cart : list) {
            if (cart.is_selected()) {
                buy.add(cart.getCart_id());
            }
        }
        // 判断如果没有勾选任何商品提示用户勾选
        if (ListUtils.isEmpty(buy)) {
            view.showToast("您还没有选择要购买的商品");
            return;
        }
        view.openConfirmOrder(ListUtils.join(buy), total);
    }

    private List<String> getSelectedId() {
        List<String> selected = new ArrayList<>();
        // 遍历出已选择的商品项
        for (int i = 0; i < ListUtils.getSize(list); i++) {
            if (list.get(i).is_selected()) {
                selected.add(list.get(i).getCart_id());
                removes.add(list.get(i));
            }
        }
        return selected;
    }

    /**
     * 判断是否是全选状态
     *
     * @return
     */
    private boolean isAllSelected() {
        if (ListUtils.isEmpty(cancelled)) {
            isAllSelected = true;
        } else {
            isAllSelected = false;
        }
        return isAllSelected;
    }

    /**
     * 显示总价以及运费并更新UI
     */
    private void showTotalAndUpdateUI() {
        // 初始化总价
        total = 0;
        // 把购物车商品列表中每项的价格*数量相加得出总价
        for (int i = 0; i < ListUtils.getSize(list); i++) {
            // 判断只有被选中的商品参加总价运算
            if (list.get(i).is_selected()) {
                // 如果有上下架功能的话，判断一下只有上架的商品参加运算，如需此功能取消注释
//                if (AppConfig.CART_IS_HAVE_STATUS) {
//                    if (list.get(i).getType().equals("1"))
//                        total += Double.parseDouble(list.get(i).getPrice()) * Integer.parseInt(list.get(i).getNumber());
//                } else {
//                    total += Double.parseDouble(list.get(i).getPrice()) * Integer.parseInt(list.get(i).getNumber());
//                }
                total += Double.parseDouble(list.get(i).getPrice()) * Integer.parseInt(list.get(i).getNumber());
            }
        }
        // 根据状态改变右下角文字（去结算/删除）
        view.showSettle(isEditState ? "删除" + (AppConfig.CART_IS_HAVE_DELETE_NUM ? "(" + (ListUtils.getSize(list) - ListUtils.getSize(cancelled)) + ")" : "") : "去结算");
        // 格式化总价并显示
        view.showTotal(!isEditState, "￥" + decimalFormat.format(total));
        // 根据总价判断是否有运费
        view.showFreight(total < no_freight, "还差" + decimalFormat.format(no_freight - total) + "元可享包邮");
        view.showIsAllSelected(isAllSelected() ? R.drawable.flag_radio_checked : R.drawable.flag_radio_normal);
    }
}