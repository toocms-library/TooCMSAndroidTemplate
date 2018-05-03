package com.toocms.template.ui.loading.advert;

import com.toocms.template.R;
import com.toocms.template.config.AppConfig;
import com.toocms.template.modle.advert.Advert;
import com.toocms.template.ui.MainAty;
import com.toocms.template.ui.loading.GuideAty;

import org.xutils.x;

import cn.zero.android.common.util.PreferencesUtils;

/**
 * Author：Zero
 * Date：2017/6/1 15:26
 *
 * @version v2.0
 */

public class AdvertPresenterImpl extends AdvertPresenter<AdvertView> implements AdvertInteractor.OnRequestFinishedListener {

    private AdvertInteractor interactor;

    public AdvertPresenterImpl() {
        interactor = new AdvertInteractorImpl();
    }

    @Override
    void loadAdvert() {
        interactor.getAdvert(this);
    }

    @Override
    void onClick(int viewId) {
        switch (viewId) {
            case R.id.advert_skip:
                if (PreferencesUtils.getBoolean(x.app(), AppConfig.IS_FIRST, true)) {
                    view.openNextPage(GuideAty.class);
                } else {
                    view.openNextPage(MainAty.class);
                }
                break;
            case R.id.advert_image:
                view.openHtml();
                break;
        }
    }

    @Override
    public void onGetAdvert(Advert advert) {
        view.showAdvertAndCountdown(advert);
    }
}
