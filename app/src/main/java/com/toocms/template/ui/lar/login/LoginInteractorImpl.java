package com.toocms.template.ui.lar.login;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.config.User;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/5/27 11:21
 *
 * @version v2.0
 */

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(String account, String password, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("account", account);
        params.put("password", password);
        new ApiTool<TooCMSResponse<User>>().postApi("Secure/login", params, new ApiListener<TooCMSResponse<User>>() {
            @Override
            public void onComplete(TooCMSResponse<User> data, Call call, Response response) {
                listener.onLoginSuccess(data);
            }
        });
    }
}
