package com.example.cleanlabel.ultraviewpager;

import android.graphics.Bitmap;


public interface IUltraIndicatorBuilder {

    IUltraIndicatorBuilder setFocusColor(int focusColor);


    IUltraIndicatorBuilder setNormalColor(int normalColor);


    IUltraIndicatorBuilder setStrokeColor(int strokeColor);


    IUltraIndicatorBuilder setStrokeWidth(int strokeWidth);


    IUltraIndicatorBuilder setIndicatorPadding(int indicatorPadding);


    IUltraIndicatorBuilder setRadius(int radius);


    IUltraIndicatorBuilder setOrientation(UltraViewPager.Orientation orientation);


    IUltraIndicatorBuilder setGravity(int gravity);

    IUltraIndicatorBuilder setFocusResId(int focusResId);


    IUltraIndicatorBuilder setNormalResId(int normalResId);


    IUltraIndicatorBuilder setFocusIcon(Bitmap bitmap);


    IUltraIndicatorBuilder setNormalIcon(Bitmap bitmap);


    IUltraIndicatorBuilder setMargin(int left, int top, int right, int bottom);

    void build();
}
