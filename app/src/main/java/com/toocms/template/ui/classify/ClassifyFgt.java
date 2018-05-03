package com.toocms.template.ui.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.toocms.frame.ui.BaseFragment;
import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;

import org.xutils.common.util.LogUtil;

/**
 * 分类
 *
 * @author Zero
 * @date 2016/7/1 16:33
 */
public class ClassifyFgt extends BaseFragment {

    @Override
    protected void onCreateFragment(@Nullable Bundle bundle) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fgt_classify;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getTitlebarResId() {
        return R.id.classify_titlebar;
    }

    @Override
    protected void initialized() {

    }

    @Override
    protected void requestData() {

    }
}
