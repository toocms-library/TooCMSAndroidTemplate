package com.toocms.template.config;

/**
 * 全局常量字段
 *
 * @author Zero
 * @date 2016/1/22 9:46
 */
public class Constants {

    /**
     * 接口返回的message字段，定义成常量以免以后接口出现改动
     */
    public static final String MESSAGE = "message";

    /**
     * App是否在后台运行
     */
    public static boolean isRunInBackground;

    // 权限请求标识码
    public static final int PERMISSIONS_CALL_PHONE = 0x110; // 打电话权限请求代码
    public static final int PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0x120; // SD卡读写权限请求代码

    /**
     * 将带[]的字符串转换成String[]
     *
     * @param str
     * @return
     */
    public static String[] parseArray(String str) {
        return str.replaceAll("\\[([^\\]]*)\\]", "$1").replaceAll("\"([^\"]*)\"", "$1").split(",");
    }
}
