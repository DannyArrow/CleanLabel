package com.example.cleanlabel.Fragments;
import com.example.cleanlabel.Fragments.Onboarding.Onboarding_one;
import com.example.cleanlabel.Fragments.Onboarding.Onboarding_three;
import com.example.cleanlabel.Fragments.Onboarding.Onboarding_two;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {


    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Onboarding_one();
            case 1:
                return new Onboarding_two();
            case 2:
                return new Onboarding_three();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
