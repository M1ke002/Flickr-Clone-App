package com.example.flickrr;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LikeAndCommentPageAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 2;
    private String[] titles = new String[] {"Likes", "Comments"};

    public LikeAndCommentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT; //num of pages
    }

    @Override
    public Fragment getItem(int page) {
        switch (page) {
            case 0:
                return new LikeFragment();
            case 1:
                return new CommentFragment();
            default:
                Log.d("error","sth wrong view pager");
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
