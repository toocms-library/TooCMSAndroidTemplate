package com.toocms.template.ui.lar.login;

import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.config.User;

/**
 * Author：Zero
 * Date：2017/5/27 11:17
 *
 * @version v2.0
 */

public interface LoginInteractor {

    interface OnRequestFinishedListener {
        void onLoginSuccess(TooCMSResponse<User> tooCMSResponse);
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @param listener
     */
    void login(String account, String password, OnRequestFinishedListener listener);
}
