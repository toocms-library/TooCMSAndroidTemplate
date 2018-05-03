package com.toocms.template.ui.lar.registerphone;

import com.toocms.frame.ui.BaseView;

/**
 * Author：Zero
 * Date：2017/5/27 17:41
 *
 * @version v2.0
 */

public interface RegisterPhoneView extends BaseView {

    /**
     * 开始倒计时
     */
    void startCountdown();

    /**
     * 跳转到设置密码页
     */
    void openRegisterPsd();
}
