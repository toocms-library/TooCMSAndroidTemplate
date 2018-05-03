package com.toocms.template.ui.lar.registerphone;

import com.toocms.frame.ui.BasePresenter;

/**
 * Author：Zero
 * Date：2017/5/27 17:52
 *
 * @version v2.0
 */

public abstract class RegisterPhonePresenter<T> extends BasePresenter<T> {

    /**
     * 发送验证码
     *
     * @param account
     */
    abstract void sendCode(String account);

    /**
     * 验证验证码
     *
     * @param account
     * @param verify
     */
    abstract void verifyCode(String account, String verify);
}
