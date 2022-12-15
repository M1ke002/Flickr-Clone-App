package com.example.flickrr.search_package;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        String title = "";
        switch (position){
            case 0:
                title = "Photos";
                break;
            case 1:
                title = "People";
                break;
            case 2:
                title = "Groups";
                break;
        }
        return title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PhotosFragment();
            case 1:
                return new PeopleFragment();
            case 2:
                return new GroupsFragment();
            default:
                return new PhotosFragment();
        }
    }
}
