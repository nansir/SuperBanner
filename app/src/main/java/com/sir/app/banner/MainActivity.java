package com.sir.app.banner;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.sir.library.banner.ConvenientBanner;
import com.sir.library.banner.holder.CBViewHolderCreator;
import com.sir.library.banner.listener.OnItemClickListener;
import com.sir.library.banner.trans.DepthPageTransformer;
import com.sir.library.banner.trans.FlipVerticalTransformer;
import com.sir.library.banner.trans.ForegroundToBackgroundTransformer;
import com.sir.library.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.banner_a)
    ConvenientBanner bannerA;
    @BindView(R.id.banner_b)
    ConvenientBanner bannerB;
    @BindView(R.id.banner_c)
    ConvenientBanner bannerC;

    List<String> mBanner = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setSwipeBackEnable(false);
    }

    @Override
    public void doBusiness(Context mContext) {

        mBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526898676240&di=f6371b4324c4dace7d7b59a9d3ef8180&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01592157635bbc0000018c1baf7d9a.jpg%401280w_1l_2o_100sh.jpg");
        mBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527493416&di=8e66a193e80113f4ce80548bd961c52d&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0177ee562dd7446ac7254878232ad7.jpg%402o.jpg");
        mBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526898750274&di=0482f6442824b5b99ba155ad5b0f136d&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01025958635af2a801219c773b64ed.jpg");

        //设置动画
        bannerA.getViewPager().setPageTransformer(true, new ForegroundToBackgroundTransformer());
        bannerA.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        //设置数据
        bannerA.setPages(new CBViewHolderCreator<BannerLoadImage>() {
            @Override
            public BannerLoadImage createHolder() {
                return new BannerLoadImage();
            }
        }, mBanner).setPageIndicator(new int[]{R.mipmap.ic_indicator, R.mipmap.ic_indicator_focused});


        //设置动画
        bannerB.getViewPager().setPageTransformer(true, new FlipVerticalTransformer());
        bannerB.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        //设置数据
        bannerB.setPages(new CBViewHolderCreator<BannerLoadImage>() {
            @Override
            public BannerLoadImage createHolder() {
                return new BannerLoadImage();
            }
        }, mBanner).setPageIndicator(new int[]{R.mipmap.ic_indicator, R.mipmap.ic_indicator_focused});


        //设置动画
        bannerC.getViewPager().setPageTransformer(true, new DepthPageTransformer());
        bannerB.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        //设置数据
        bannerC.setPages(new CBViewHolderCreator<BannerLoadImage>() {
            @Override
            public BannerLoadImage createHolder() {
                return new BannerLoadImage();
            }
        }, mBanner).setPageIndicator(new int[]{R.mipmap.ic_indicator, R.mipmap.ic_indicator_focused});

    }

    @Override
    protected void onPause() {
        super.onPause();
        bannerA.stopTurning();
        bannerB.stopTurning();
        bannerC.stopTurning();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bannerA.startTurning(2000);
        bannerB.startTurning(3000);
        bannerC.startTurning(4000);
    }
}
