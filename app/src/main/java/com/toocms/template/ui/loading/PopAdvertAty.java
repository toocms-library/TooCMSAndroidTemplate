package com.toocms.template.ui.loading;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lzy.okgo.model.HttpParams;
import com.toocms.frame.image.ImageLoader;
import com.toocms.frame.web.ApiListener;
import com.toocms.frame.web.ApiTool;
import com.toocms.frame.web.modle.TooCMSResponse;
import com.toocms.template.R;
import com.toocms.template.modle.advert.Advert;
import com.zhy.autolayout.AutoLayoutActivity;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 首页弹出的广告页
 *
 * @author Zero
 * @date 2016/8/29 10:12
 */
public class PopAdvertAty extends AutoLayoutActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_pop_advert);
        findViewById(R.id.pop_advert_frame).setOnClickListener(this);
        findViewById(R.id.pop_advert_close).setOnClickListener(this);
        findViewById(R.id.pop_advert_image).setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.pop_advert_image);
        // 获取广告图片并显示
        HttpParams params = new HttpParams();
        new ApiTool<TooCMSResponse<Advert>>().getApi("http://ttt.toocms.com/Index/ad", params, new ApiListener<TooCMSResponse<Advert>>() {
            @Override
            public void onComplete(TooCMSResponse<Advert> data, Call call, Response response) {
                ImageLoader.loadUrl2Image(PopAdvertAty.this, data.getData().getUrl(), imageView, R.drawable.layer_loading);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_advert_frame:
            case R.id.pop_advert_close:
                finish();
                break;
            case R.id.pop_advert_image:

                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
    }
}
