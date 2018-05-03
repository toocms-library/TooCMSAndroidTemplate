package com.toocms.template.ui.address.selectregion;

import com.toocms.template.modle.address.AddressTemp;
import com.toocms.template.modle.address.Region;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/5/4 13:24
 *
 * @version v2.0
 */

public class SelectRegionPresenterImpl extends SelectRegionPresenter<SelectRegionView> implements SelectRegionInteractor.OnRequestFinishedListener {

    private SelectRegionInteractor interactor;

    private List<Region> list; // 地区列表数据源
    private AddressTemp temp; // 城市信息

    public SelectRegionPresenterImpl(AddressTemp temp) {
        this.temp = temp;
        interactor = new SelectRegionInteractorImpl();
    }

    @Override
    void loadRegionList() {
        view.showProgress();
        interactor.getRegionList("3", temp.area_id, this);
    }

    @Override
    void selectRegionItem(int position) {
        Region region = list.get(position);
        temp.region_id = region.getRegion_id();
        temp.address += region.getRegion_name();
        view.selectRegionItem(temp);
    }

    @Override
    public void onGetRegionListFinished(List<Region> data) {
        list = data;
        view.showRegionList(data);
    }
}
