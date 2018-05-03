package com.toocms.template.ui.lar.registerphone;

/**
 * Author：Zero
 * Date：2017/5/27 17:42
 *
 * @version v2.0
 */

public interface RegisterPhoneInteractor {

    interface OnRequestFinishListener {
        void onGetCode(String message);

        void onVerifySuccess(String message);
    }

    /**
     * 发送验证码
     *
     * @param account
     * @param listener
     */
    void sendCode(String account, OnRequestFinishListener listener);

    /**
     * 验证验证码
     *
     * @param account
     * @param code
     * @param listener
     */
    void verifyCode(String account, String code, OnRequestFinishListener listener);
}
