package com.toocms.template.config;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.toocms.template.R;

import org.xutils.x;

import cn.zero.android.common.util.PreferencesUtils;

/**
 * APP倒计时处理
 *
 * @author Zero
 * @date 2016/1/22 10:28
 */
public class AppCountdown {

    /**
     * 间隔时间
     */
    private static final int INTERVAL_TIME = 60;

    private TextView textView;
    private Timer timer;
    private long surplusTime; // 剩余时间

    public AppCountdown() {
        surplusTime = PreferencesUtils.getLong(x.app(), "STOP_TIME") - System.currentTimeMillis();
        timer = new Timer(surplusTime > 0 ? surplusTime : INTERVAL_TIME * 1000);
    }

    private void saveStopTime() {
        PreferencesUtils.putLong(x.app(), "STOP_TIME", System.currentTimeMillis() + surplusTime);
    }

    public void play(TextView textView) {
        this.textView = textView;
        if (surplusTime > 0) {
            textView.setEnabled(false);
            textView.setTextColor(Color.parseColor("#656565"));
            timer.start();
        } else {
            textView.setText("获取验证码");
            textView.setEnabled(true);
            textView.setTextColor(x.app().getResources().getColor(R.color.clr_main));
        }
    }

    public void start() {
        timer = new Timer(INTERVAL_TIME * 1000);
        timer.start();
    }

    public void stop() {
        saveStopTime();
        timer.cancel();
    }

    private class Timer extends CountDownTimer {

        public Timer(long millisInFuture) {
            super(millisInFuture, 100);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            surplusTime = millisUntilFinished;
            textView.setText("重新获取(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            textView.setText("重新获取");
            textView.setEnabled(true);
            textView.setTextColor(x.app().getResources().getColor(R.color.clr_main));
        }
    }
}
