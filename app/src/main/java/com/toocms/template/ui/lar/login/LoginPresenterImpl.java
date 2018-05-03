package com.toocms.template.ui.lar.login;

import com.toocms.frame.config.LoginStatus;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.config.User;

/**
 * Author：Zero
 * Date：2017/5/27 11:26
 *
 * @version v2.0
 */

public class LoginPresenterImpl extends LoginPresenter<LoginView> implements LoginInteractor.OnRequestFinishedListener {

    public LoginInteractor interactor;

    public LoginPresenterImpl() {
        interactor = new LoginInteractorImpl();
    }

    @Override
    void login(String account, String password) {
        if (account.length() < 11) {
            view.showToast("请输入11位手机号");
            return;
        } else if (password.length() < 6) {
            view.showToast("请输入最少6位以上的密码");
            return;
        }
        view.showProgress();
        interactor.login(account, password, this);
    }

    @Override
    void onClickRegister() {
        view.openRegister();
    }

    @Override
    public void onLoginSuccess(TooCMSResponse<User> tooCMSResponse) {
        view.showToast(tooCMSResponse.getMessage());
        LoginStatus.setLogin(true);
        view.onLoginSuccess(tooCMSResponse.getData());
    }
}
