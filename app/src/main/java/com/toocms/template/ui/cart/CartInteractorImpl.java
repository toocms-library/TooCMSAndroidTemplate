package com.toocms.template.ui.cart;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.cart.Cart;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/5/16 11:30
 *
 * @version v2.0
 */

public class CartInteractorImpl implements CartInteractor {

    @Override
    public void getCartList(String member_id, String region_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("region_id", region_id);
        new ApiTool<TooCMSResponse<Cart>>().getApi("Cart/index", params, new ApiListener<TooCMSResponse<Cart>>() {
            @Override
            public void onComplete(TooCMSResponse<Cart> data, Call call, Response response) {
                listener.onGetCartListFinished(data.getData());
            }

            @Override
            public void onError(String error, Call call, Response response) {
                listener.onGetCartListError();
            }
        });
    }

    @Override
    public void editCartNum(String member_id, String cart_id, int number, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("cart_id", cart_id);
        params.put("number", number);
        new ApiTool<TooCMSResponse<Void>>().getApi("Cart/toEdit", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onEditCartNumFinished(data.getMessage());
            }
        });
    }

    @Override
    public void deleteCart(String member_id, String cart_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("cart_id", cart_id);
        new ApiTool<TooCMSResponse<Void>>().postApi("Cart/toDelete", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onDeleteCartFinished(data.getMessage());
            }
        });
    }
}
