package com.toocms.template.ui.address.selectcity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.toocms.template.modle.address.AddressTemp;
import com.toocms.template.modle.address.Region;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/4/28 17:09
 *
 * @version v2.0
 */

public class SelectCityPresenterImpl extends SelectCityPresenter<SelectCityView> implements SelectCityInteractor.OnRequestFinishedListener {

    private SelectCityInteractor interactor;

    private List<Region> list;

    public SelectCityPresenterImpl() {
        interactor = new SelectCityInteractorImpl();
    }

    @Override
    void loadCityList() {
        view.showProgress();
        interactor.getRegionList(this);
    }

    @Override
    void selectCityItem(int position) {
        Bundle bundle = new Bundle();
        AddressTemp temp = new AddressTemp();
        temp.area_id = list.get(position).getRegion_id();
        temp.address = list.get(position).getRegion_name();
        bundle.putSerializable("address", temp);
        view.startSelectRegionAty(bundle);
    }

    @Override
    void result(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case SelectCityAty.REQUEST_CITY:
                if (null != data) view.finish(data);
                break;
        }
    }

    @Override
    public void onGetRegionListFinished(List<Region> data) {
        list = data;
        view.showCityList(data);
    }
}
