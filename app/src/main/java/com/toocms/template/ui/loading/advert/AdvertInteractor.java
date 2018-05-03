package com.toocms.template.ui.loading.advert;

import com.toocms.template.modle.advert.Advert;

/**
 * Author：Zero
 * Date：2017/6/1 15:16
 *
 * @version v2.0
 */

public interface AdvertInteractor {

    interface OnRequestFinishedListener {
        void onGetAdvert(Advert advert);
    }

    void getAdvert(OnRequestFinishedListener listener);
}
