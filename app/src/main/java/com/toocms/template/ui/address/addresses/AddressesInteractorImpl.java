package com.toocms.template.ui.address.addresses;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.address.Address;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 地址列表Modle层接口实现类
 * <p>
 * Author：Zero
 * Date：2017/4/26 17:48
 *
 * @version v2.0
 */
public class AddressesInteractorImpl implements AddressesInteractor {

    @Override
    public void getAddressList(String member_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        new ApiTool<TooCMSResponse<List<Address>>>().getApi("Address/index", params, new ApiListener<TooCMSResponse<List<Address>>>() {
            @Override
            public void onComplete(TooCMSResponse<List<Address>> data, Call call, Response response) {
                listener.onGetAddressListFinished(data.getData());
            }
        });
    }

    @Override
    public void deleteAddress(String member_id, String address_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("address_id", address_id);
        new ApiTool<TooCMSResponse<Void>>().getApi("Address/toDelete", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onDeleteAddressListFinished(data.getMessage());
            }
        });
    }

    @Override
    public void setDefaultAddress(String member_id, String address_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("address_id", address_id);
        new ApiTool<TooCMSResponse<Void>>().getApi("Address/toDefault", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onSetDefaultAddressFinished(data.getMessage());
            }
        });
    }
}
