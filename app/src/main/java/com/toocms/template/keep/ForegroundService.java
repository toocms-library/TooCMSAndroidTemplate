package com.toocms.template.keep;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.xutils.common.util.LogUtil;

/**
 * @author Zero
 * @date 2016/8/27 14:58
 */
@Deprecated
public class ForegroundService extends Service {

    private final static int SERVICE_ID = 1001;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e("我还活着！！！");
        // do something

        // ============================
        if (Build.VERSION.SDK_INT < 18) {
            //API < 18 ，此方法能有效隐藏Notification上的图标
            startForeground(SERVICE_ID, new Notification());
        } else {
            Intent innerIntent = new Intent(this, KeepInnerService.class);
            startService(innerIntent);
            startForeground(SERVICE_ID, new Notification());
        }
        return Service.START_STICKY;
    }

    public static class KeepInnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
