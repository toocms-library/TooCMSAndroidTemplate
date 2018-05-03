package com.toocms.template.config;

import com.toocms.frame.config.BaseDataSet;
import com.toocms.frame.config.WeApplication;

/**
 * Author：Zero
 * Date：2017/6/21 16:05
 *
 * @version v2.0
 */

public class DataSet extends BaseDataSet<AppConfig, Urls, User> {

    private volatile static DataSet instance;

    public static DataSet getInstance() {
        if (instance == null)
            synchronized (DataSet.class) {
                if (instance == null)
                    instance = new DataSet();
            }
        return instance;
    }

    @Override
    public AppConfig getAppConfig() {
        return AppConfig.getInstance();
    }

    @Override
    public Urls getUrls() {
        return Urls.getInstance();
    }

    @Override
    public User getUser() {
        return WeApplication.getInstance().getUserInfo();
    }
}
