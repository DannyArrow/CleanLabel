package com.example.cleanlabel.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cleanlabel.Adapter.PagerAdapter;
import com.example.cleanlabel.R;
import com.example.cleanlabel.ultraviewpager.UltraViewPager;
import com.example.cleanlabel.ultraviewpager.transformer.UltraDepthScaleTransformer;

public class Subscription extends androidx.fragment.app.Fragment {

    ImageView imageView4;
    TextView titles;
    int current_img = 0;
    ImageView selected_image;
    public static UltraViewPager viewPager;
    PagerAdapter myCustomPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.subsribe_activity, container, false);

        selected_image = (ImageView) view.findViewById(R.id.selected_image);
        viewPager = (UltraViewPager) view.findViewById(R.id.viewPager);

        //imageView4 = view.findViewById(R.id.imageView4);



        int w = getResources().getDisplayMetrics().widthPixels;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, w);
        params.addRule(13);
        selected_image.setLayoutParams(params);


        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(w, (w * 751) / 1080);
        params1.addRule(13);
        viewPager.setLayoutParams(params1);
        viewPager.setInfiniteLoop(true);
        viewPager.setMultiScreen(0.65f);
        viewPager.setPageTransformer(false, new UltraDepthScaleTransformer());


        selected_image.setVisibility(View.VISIBLE);

        myCustomPagerAdapter = new PagerAdapter(getContext());
        viewPager.setAdapter(myCustomPagerAdapter);
        viewPager.setCurrentItem(current_img);

//        imageView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });

        return view;
    }
}
