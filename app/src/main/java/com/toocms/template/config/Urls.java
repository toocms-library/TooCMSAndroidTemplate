package com.toocms.template.config;

import com.toocms.frame.config.IUrls;

/**
 * URL集合类
 * 如果需要其他的URL也像这种方式去写，之后之后通过Urls.getInstance().xxx()去获取该URL
 * <p>
 * Author：Zero
 * Date：2017/4/26 14:47
 */
public class Urls implements IUrls {

    private volatile static Urls instance;

    private Urls() {
    }

    public static Urls getInstance() {
        if (instance == null)
            synchronized (Urls.class) {
                if (instance == null)
                    instance = new Urls();
            }
        return instance;
    }

    @Override
    // 主URL
    public String getBaseUrl() {
        return "http://xsd-api.toocms.com/index.php/";
    }

    @Override
    // 更新URL
    public String getUpdateUrl() {
        return "http://twp.toocms.com/App/App/checkVersion";
    }
}
