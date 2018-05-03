package com.toocms.template.ui.loading.advert;

import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.advert.Advert;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/6/1 15:20
 *
 * @version v2.0
 */

public class AdvertInteractorImpl implements AdvertInteractor {

    @Override
    public void getAdvert(final OnRequestFinishedListener listener) {
        new ApiTool<TooCMSResponse<Advert>>().getApi("http://ttt.toocms.com/Index/ad", null, new ApiListener<TooCMSResponse<Advert>>() {
            @Override
            public void onComplete(TooCMSResponse<Advert> data, Call call, Response response) {
                listener.onGetAdvert(data.getData());
            }
        });
    }
}
