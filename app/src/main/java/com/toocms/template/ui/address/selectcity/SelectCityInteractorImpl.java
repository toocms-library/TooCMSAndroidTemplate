package com.toocms.template.ui.address.selectcity;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.modle.address.Region;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Author：Zero
 * Date：2017/4/28 17:03
 *
 * @version v2.0
 */

public class SelectCityInteractorImpl implements SelectCityInteractor {

    @Override
    public void getRegionList(final OnRequestFinishedListener listener) {
        new ApiTool<TooCMSResponse<List<Region>>>().getApi("Address/getRegionList", null, new ApiListener<TooCMSResponse<List<Region>>>() {
            @Override
            public void onComplete(TooCMSResponse<List<Region>> data, Call call, Response response) {
                listener.onGetRegionListFinished(data.getData());
            }
        });
    }
}
