package com.toocms.template.ui.mine.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.toocms.frame.ui.BasePresenter;
import com.toocms.template.R;
import com.toocms.template.ui.BaseAty;

import butterknife.BindView;

/**
 * 关于我们
 *
 * @author Zero
 * @date 2016/7/19 9:51
 */
public class AboutAty extends BaseAty {

    @BindView(R.id.about_content)
    TextView textView;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        mActionBar.setTitle("关于我们");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_about;
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

//    @Override
//    public void onComplete(RequestParams params, String result) {
//        // =========================== 指定接口返回的关于我们内容的字段 =========================
//        textView.setText(Html.fromHtml(JSONUtils.parseDataToMap(result).get("")));
//        super.onComplete(params, result);
//    }
}
