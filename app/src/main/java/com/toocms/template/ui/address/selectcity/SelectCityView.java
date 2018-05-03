package com.toocms.template.ui.address.selectcity;

import android.content.Intent;
import android.os.Bundle;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.address.Region;

import java.util.List;

/**
 * Author：Zero
 * Date：2017/4/28 16:52
 *
 * @version v2.0
 */

public interface SelectCityView extends BaseView {

    void showCityList(List<Region> data);

    void startSelectRegionAty(Bundle bundle);

    void finish(Intent data);
}
