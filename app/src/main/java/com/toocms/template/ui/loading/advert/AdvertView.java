package com.toocms.template.ui.loading.advert;

import com.toocms.frame.ui.BaseView;
import com.toocms.template.modle.advert.Advert;

/**
 * Author：Zero
 * Date：2017/6/1 15:16
 *
 * @version v2.0
 */

public interface AdvertView extends BaseView {

    /**
     * 显示广告图并开始倒计时
     */
    void showAdvertAndCountdown(Advert advert);

    /**
     * 根据是否为第一次打开APP跳转到下一页（导航介绍/主页）
     */
    void openNextPage(Class cls);

    /**
     * 跳转到广告Html页
     */
    void openHtml();
}
