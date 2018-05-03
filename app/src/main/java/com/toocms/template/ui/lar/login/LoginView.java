package com.toocms.template.ui.lar.login;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.config.User;

/**
 * Author：Zero
 * Date：2017/5/27 11:27
 *
 * @version v2.0
 */

public interface LoginView extends BaseView {

    void onLoginSuccess(User user);

    void openRegister();
}
