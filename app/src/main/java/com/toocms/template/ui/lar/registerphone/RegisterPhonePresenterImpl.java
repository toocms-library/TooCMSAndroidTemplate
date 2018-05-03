package com.toocms.template.ui.lar.registerphone;

import cn.zero.android.common.util.StringUtils;

/**
 * Author：Zero
 * Date：2017/5/27 17:55
 *
 * @version v2.0
 */

public class RegisterPhonePresenterImpl extends RegisterPhonePresenter<RegisterPhoneView> implements RegisterPhoneInteractor.OnRequestFinishListener {

    private RegisterPhoneInteractor interactor;

    public RegisterPhonePresenterImpl() {
        interactor = new RegisterPhoneInteractorImpl();
    }

    @Override
    void sendCode(String account) {
        if (account.length() < 11) {
            view.showToast("请输入11位手机号");
            return;
        }
        view.showProgress();
        interactor.sendCode(account, this);
    }

    @Override
    void verifyCode(String account, String verify) {
        if (account.length() < 11) {
            view.showToast("请输入11位手机号");
            return;
        } else if (StringUtils.isEmpty(verify)) {
            view.showToast("请填写验证码");
            return;
        }
        view.showProgress();
        interactor.verifyCode(account, verify, this);
    }

    @Override
    public void onGetCode(String message) {
        view.showToast(message);
        view.startCountdown();
    }

    @Override
    public void onVerifySuccess(String message) {
        view.openRegisterPsd();
    }
}
