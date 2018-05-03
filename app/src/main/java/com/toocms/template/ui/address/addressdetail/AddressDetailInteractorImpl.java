package com.toocms.template.ui.address.addressdetail;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.address.Address;

import cn.zero.android.common.util.ParamsUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/4/28 13:52
 *
 * @version v2.0
 */
public class AddressDetailInteractorImpl implements AddressDetailInteractor {

    @Override
    public void getAddressDetail(String member_id, String address_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("member_id", member_id);
        params.put("address_id", address_id);
        new ApiTool<TooCMSResponse<Address>>().getApi("Address/getDetail", params, new ApiListener<TooCMSResponse<Address>>() {
            @Override
            public void onComplete(TooCMSResponse<Address> data, Call call, Response response) {
                listener.onGetAddressDetailFinished(data);
            }
        });
    }

    @Override
    public void editAddress(String member_id, Address address, final OnRequestFinishedListener listener) {
        HttpParams params = ParamsUtils.createHttpParams(address);
        params.put("member_id", member_id);
        new ApiTool<TooCMSResponse<Void>>().postApi("Address/toEdit", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onSaveAddressFinished(data.getMessage());
            }
        });
    }

    @Override
    public void addNewAddress(String member_id, Address address, final OnRequestFinishedListener listener) {
        HttpParams params = ParamsUtils.createHttpParams(address);
        params.put("member_id", member_id);
        new ApiTool<TooCMSResponse<Void>>().postApi("Address/toCreate", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onSaveAddressFinished(data.getMessage());
            }
        });
    }
}
