package com.example.cleanlabel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.cleanlabel.R;


public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    LayoutInflater layoutInflater;
    Context mContext;


    public PagerAdapter(Context context) {
      mContext = context;


       layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return  3;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.pageadapter, container, false);
//        RoundedImageView imageView = (RoundedImageView) itemView.findViewById(R.id.igview);
//
//        ImageView bg_image = (ImageView) itemView.findViewById(R.id.bg_image);
//        String url = (String)images.get(position);
//        int w =mContext.getResources().getDisplayMetrics().widthPixels;
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((w * 655) / 1080, (w * 655) / 1080);
//        params.addRule(13);
//        imageView.setLayoutParams(params);
//        RelativeLayout.LayoutParams params12 = new RelativeLayout.LayoutParams((w * 751) / 1080, (w * 751) / 1080);
//        params12.addRule(13);
//        bg_image.setLayoutParams(params12);
//        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams((w * 126) / 1080, (w * 126) / 1080);
//        params2.addRule(11);
//        int ml = (w * 40) / 1080;
//        int mt = (w * 75) / 1080;
//        params2.setMargins(ml, mt, ml, mt);
//
        container.addView(itemView);
        return itemView;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
