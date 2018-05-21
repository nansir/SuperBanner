package com.sir.app.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sir.library.banner.holder.Holder;


/**
 * Banner加载图片
 * Created by zhuyinan on 2018/04/02.
 * Contact by 445181052@qq.com
 */
public class BannerLoadImage implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //通过layout文件来创建
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        //缓存原图(SOURCE)和处理图(RESULT)
        Glide.with(context)
                .load(data)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
