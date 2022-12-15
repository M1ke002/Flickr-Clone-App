package com.example.flickrr.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class StartScreenPageAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 4;
    private final String[][] texts = new String[][] {
            {"Powerful", "Save all of your photos and videos in one place"},
            {"Keep your memories safe", "Your uploaded photos stay private until you choose to share them"},
            {"Organization simplified", "Search, edit, and organize in seconds"},
            {"Sharing made easy", "Share with friends, family, and the world"}
    };
    public StartScreenPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new StartScreenTextFragment();
        Bundle args = new Bundle();
        args.putString("boldText",texts[position][0]);
        args.putString("normalText",texts[position][1]);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
