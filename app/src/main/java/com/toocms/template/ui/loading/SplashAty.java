package com.toocms.template.ui.loading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.toocms.template.ui.loading.advert.AdvertAty;

/**
 * Loading页
 *
 * @author Zero
 * @date 2016/8/26 12:27
 */
public class SplashAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(SplashAty.this, AdvertAty.class);
        startActivity(intent);
        finish();
    }
}
