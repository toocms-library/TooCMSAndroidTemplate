package com.toocms.template.ui.address.selectregion;

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
 * Date：2017/5/4 13:16
 *
 * @version v2.0
 */

public class SelectRegionInteractorImpl implements SelectRegionInteractor {

    @Override
    public void getRegionList(String type, String region_id, final OnRequestFinishedListener listener) {
        HttpParams params = new HttpParams();
        params.put("type", type);
        params.put("region_id", region_id);
        new ApiTool<TooCMSResponse<List<Region>>>().getApi("Address/getRegionList", params, new ApiListener<TooCMSResponse<List<Region>>>() {
            @Override
            public void onComplete(TooCMSResponse<List<Region>> data, Call call, Response response) {
                listener.onGetRegionListFinished(data.getData());
            }
        });
    }
}
