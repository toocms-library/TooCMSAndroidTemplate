package com.toocms.template.ui.lar.registerphone;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/5/27 17:48
 *
 * @version v2.0
 */

public class RegisterPhoneInteractorImpl implements RegisterPhoneInteractor {

    @Override
    public void sendCode(String account, final OnRequestFinishListener listener) {
        HttpParams params = new HttpParams();
        params.put("account", account);
        params.put("unique_code", "register");
        new ApiTool<TooCMSResponse<Void>>().postApi("Sms/send", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onGetCode(data.getMessage());
            }
        });
    }

    @Override
    public void verifyCode(String account, String verify, final OnRequestFinishListener listener) {
        HttpParams params = new HttpParams();
        params.put("account", account);
        params.put("verify", verify);
        params.put("unique_code", "register");
        new ApiTool<TooCMSResponse<Void>>().postApi("Sms/check", params, new ApiListener<TooCMSResponse<Void>>() {
            @Override
            public void onComplete(TooCMSResponse<Void> data, Call call, Response response) {
                listener.onVerifySuccess(data.getMessage());
            }
        });
    }
}
