package com.toocms.template.ui.loading.advert;

import com.toocms.frame.ui.BasePresenter;

/**
 * Author：Zero
 * Date：2017/6/1 15:22
 *
 * @version v2.0
 */

public abstract class AdvertPresenter<T> extends BasePresenter<T> {

    abstract void loadAdvert();

    abstract void onClick(int viewId);
}
