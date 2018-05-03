package com.toocms.template.config;

import com.toocms.frame.config.IAppConfig;

/**
 * App配置
 *
 * @author Zero
 * @date 2016/6/23 17:05
 */
public class AppConfig implements IAppConfig {

    private volatile static AppConfig instance;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        if (instance == null)
            synchronized (AppConfig.class) {
                if (instance == null)
                    instance = new AppConfig();
            }
        return instance;
    }

    /**
     * 是否使用角标
     */
    public static final boolean IS_USE_BADGE = true;

    /**
     * 购物车是否有上下架功能
     */
    public static final boolean CART_IS_HAVE_STATUS = true;

    /**
     * 购物车的删除按钮是否带有数字
     */
    public static final boolean CART_IS_HAVE_DELETE_NUM = true;

    /**
     * 是否第一次启动App，当更新新版本时如需显示新更换的引导页，将此字段修改一下
     */
    public static final String IS_FIRST = "isFirst5";

    @Override
    public boolean isShowTitleCenter() {
        return false;
    }

    @Override
    public String getProgectFolder() {
        return "Android原型库";
    }

    @Override
    public boolean isUseSnackBar() {
        return true;
    }

    @Override
    public int getNavigationLoadingImage() {
        return 0;
    }
}
