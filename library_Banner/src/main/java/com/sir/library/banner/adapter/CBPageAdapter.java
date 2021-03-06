package com.sir.library.banner.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.sir.library.banner.R;
import com.sir.library.banner.holder.CBViewHolderCreator;
import com.sir.library.banner.holder.Holder;
import com.sir.library.banner.view.CBLoopViewPager;

import java.util.List;

public class CBPageAdapter<T> extends PagerAdapter {

    private final int MULTIPLE_COUNT = 300;

    protected List<T> mData;

    protected CBViewHolderCreator holderCreator;

    private boolean canLoop = true;

    private CBLoopViewPager viewPager;

    public CBPageAdapter(CBViewHolderCreator holderCreator, List<T> data) {
        this.holderCreator = holderCreator;
        this.mData = data;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public void setViewPager(CBLoopViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }
        int realPosition = position % realCount;
        return realPosition;
    }


    @Override
    public int getCount() {
        return canLoop ? getRealCount() * MULTIPLE_COUNT : getRealCount();
    }


    public int getRealCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = toRealPosition(position);
        View view = getView(realPosition, null, container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        int position = viewPager.getCurrentItem();
        if (position == 0) {
            position = viewPager.getFristItem();
        } else if (position == getCount() - 1) {
            position = viewPager.getLastItem();
        }
        try {
            viewPager.setCurrentItem(position, false);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        if (view == null) {
            holder = (Holder) holderCreator.createHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.cb_item_tag, holder);
        } else {
            holder = (Holder<T>) view.getTag(R.id.cb_item_tag);
        }
        if (mData != null && !mData.isEmpty()) {
            holder.UpdateUI(container.getContext(), position, mData.get(position));
        }
        return view;
    }
}
