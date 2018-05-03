package com.toocms.template.ui.loading.advert;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.toocms.frame.image.ImageLoader;
import com.toocms.template.R;
import com.toocms.template.modle.advert.Advert;
import com.toocms.template.ui.BaseAty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 广告页
 *
 * @author Zero
 * @date 2016/8/26 13:49
 */
public class AdvertAty extends BaseAty<AdvertView, AdvertPresenterImpl> implements AdvertView {

    private final long MILLIS_IN_FUTURE = 3500; // 总倒计时秒数（这里如果设置整3秒的话会从2开始倒计时，所以设置成3秒5）
    private final long COUNT_DOWN_INTERVAL = 1000; // 倒计时间隔

    @BindView(R.id.advert_image)
    ImageView imageView;
    @BindView(R.id.advert_skip)
    TextView textView;

    private Timer timer;

    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        // 设置全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActionBar.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_advert;
    }

    @Override
    protected AdvertPresenterImpl getPresenter() {
        return new AdvertPresenterImpl();
    }

    @Override
    protected void initialized() {
        timer = new Timer();
    }

    @Override
    protected void requestData() {
        presenter.loadAdvert();
    }

    @OnClick({R.id.advert_skip, R.id.advert_image})
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }

    @Override
    public void showAdvertAndCountdown(Advert advert) {
        ImageLoader.loadUrl2Image(this, advert.getUrl(), imageView, R.drawable.layer_loading);
        // 开始倒计时
        timer.start();
    }

    @Override
    public void openNextPage(Class cls) {
        // 先停止计时器再启动页面，防止启动两个界面
        timer.cancel();
        startActivity(cls, null);
        finish();
    }

    @Override
    public void openHtml() {

    }

    private class Timer extends CountDownTimer {

        public Timer() {
            super(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText("跳过 " + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            presenter.onClick(R.id.advert_skip);
        }
    }
}
