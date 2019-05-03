package com.example.cleanlabel.ultraviewpager;

import android.graphics.Bitmap;
import android.util.SparseIntArray;


interface IUltraViewPagerFeature {

    IUltraIndicatorBuilder initIndicator();


    IUltraIndicatorBuilder initIndicator(int focusColor, int normalColor, int radiusInPixel, int gravity);



    IUltraIndicatorBuilder initIndicator(int focusColor, int normalColor, int strokeColor, int strokeWidth, int radiusInPixel, int gravity);


    IUltraIndicatorBuilder initIndicator(int focusResId, int normalResId, int gravity);


    IUltraIndicatorBuilder initIndicator(Bitmap focusBitmap, Bitmap normalBitmap, int gravity);


    void disableIndicator();


    void setAutoScroll(int intervalInMillis);


    void setAutoScroll(int intervalInMillis, SparseIntArray intervalArray);


    void disableAutoScroll();


    void setInfiniteLoop(boolean enable);


    void setMaxWidth(int width);


    void setMaxHeight(int height);


    void setRatio(float ratio);


    void setScrollMode(UltraViewPager.ScrollMode scrollMode);


    void disableScrollDirection(UltraViewPager.ScrollDirection direction);



    boolean scrollLastPage();


    boolean scrollNextPage();


    void setMultiScreen(float ratio);


    void setAutoMeasureHeight(boolean status);


    void setItemRatio(double ratio);

    void setHGap(int pixel);


    void setItemMargin(int left, int top, int right, int bottom);


    void setScrollMargin(int left, int right);


    void setInfiniteRatio(int infiniteRatio);
}
