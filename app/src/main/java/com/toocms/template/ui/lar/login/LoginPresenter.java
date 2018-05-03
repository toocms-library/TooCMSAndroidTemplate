package com.toocms.template.ui.lar.login;

import com.toocms.frame.ui.BasePresenter;

/**
 * Author：Zero
 * Date：2017/5/27 11:24
 *
 * @version v2.0
 */

public abstract class LoginPresenter<T> extends BasePresenter<T> {

    abstract void login(String account, String password);

    abstract void onClickRegister();
}
