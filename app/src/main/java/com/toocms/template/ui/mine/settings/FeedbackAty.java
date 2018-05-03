package com.toocms.template.ui.mine.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.ui.BaseAty;

/**
 * 意见反馈
 *
 * @author Zero
 * @date 2016/7/19 9:04
 */
public class FeedbackAty extends BaseAty {

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("意见反馈");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_feedback;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initialized() {

    }

    @Override
    protected void requestData() {

    }
}
