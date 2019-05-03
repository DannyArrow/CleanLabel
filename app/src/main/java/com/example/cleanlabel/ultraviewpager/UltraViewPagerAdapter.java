
package com.example.cleanlabel.ultraviewpager;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;


public class UltraViewPagerAdapter extends PagerAdapter {
    public interface UltraViewPagerCenterListener {
        void center();

        void resetPosition();
    }

    private static final int INFINITE_RATIO = 400;

    private PagerAdapter adapter;
    private boolean enableLoop;
    private float multiScrRatio = Float.NaN;
    private boolean hasCentered;
    private int scrWidth;
    private int infiniteRatio;
    private UltraViewPagerCenterListener centerListener;

    private SparseArray viewArray = new SparseArray();

    public UltraViewPagerAdapter(PagerAdapter adapters) {
       adapter = adapters;
        infiniteRatio = INFINITE_RATIO;
    }

    @Override
    public int getCount() {
        if (!enableLoop) {
            return adapter.getCount();
        }
        if (adapter.getCount() == 0) {
            return 0;
        }
        return adapter.getCount() * infiniteRatio;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = position;

        if (enableLoop && adapter.getCount() != 0) {
            realPosition = position % adapter.getCount();
        }

        Object item = adapter.instantiateItem(container, realPosition);

        View childView = null;
        if (item instanceof View)
            childView = (View) item;
        if (item instanceof RecyclerView.ViewHolder)
            childView = ((RecyclerView.ViewHolder) item).itemView;

        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = container.getChildAt(i);
            if (isViewFromObject(child, item)) {
                viewArray.put(realPosition, child);
                break;
            }
        }

        if (isEnableMultiScr()) {
            if (scrWidth == 0) {
                scrWidth = container.getResources().getDisplayMetrics().widthPixels;
            }
            RelativeLayout relativeLayout = new RelativeLayout(container.getContext());

            if (childView.getLayoutParams() != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        (int) (scrWidth * multiScrRatio),
                        ViewGroup.LayoutParams.MATCH_PARENT);

                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                childView.setLayoutParams(layoutParams);
            }

            container.removeView(childView);
            relativeLayout.addView(childView);

            container.addView(relativeLayout);
            return relativeLayout;
        }

        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int realPosition = position;


        if (enableLoop && adapter.getCount() != 0)
            realPosition = position % adapter.getCount();

        if (isEnableMultiScr() && object instanceof RelativeLayout) {
            View child = ((RelativeLayout) object).getChildAt(0);
            ((RelativeLayout) object).removeAllViews();
            adapter.destroyItem(container, realPosition, child);
        } else {
            adapter.destroyItem(container, realPosition, object);
        }

        viewArray.remove(realPosition);
    }

    public View getViewAtPosition(int position) {
        return (View) viewArray.get(position);
    }

    @Override
    public void finishUpdate(ViewGroup container) {

        if (!hasCentered) {
            if (adapter.getCount() > 0 && getCount() > adapter.getCount()) {
                centerListener.center();
            }
        }
        hasCentered = true;
        adapter.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return adapter.isViewFromObject(view, object);
    }

    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        adapter.restoreState(bundle, classLoader);
    }

    @Override
    public Parcelable saveState() {
        return adapter.saveState();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        adapter.startUpdate(container);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int virtualPosition = position % adapter.getCount();
        return adapter.getPageTitle(virtualPosition);
    }

    @Override
    public float getPageWidth(int position) {
        return adapter.getPageWidth(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        adapter.setPrimaryItem(container, position, object);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        adapter.unregisterDataSetObserver(observer);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        adapter.registerDataSetObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return adapter.getItemPosition(object);
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }

    public int getRealCount() {
        return adapter.getCount();
    }

    public void setEnableLoop(boolean status) {
        enableLoop = status;
        notifyDataSetChanged();
        if (!status) {
            centerListener.resetPosition();
        } else {

        }
    }

    public boolean isEnableLoop() {
        return enableLoop;
    }

    public void setMultiScrRatio(float ratio) {
        multiScrRatio = ratio;
    }

    public boolean isEnableMultiScr() {
        return !Float.isNaN(multiScrRatio) && multiScrRatio < 1f;
    }

    public void setCenterListener(UltraViewPagerCenterListener listener) {
        centerListener = listener;
    }

    public void setInfiniteRatio(int infiniteRatios) {
       infiniteRatio = infiniteRatios;
    }
}