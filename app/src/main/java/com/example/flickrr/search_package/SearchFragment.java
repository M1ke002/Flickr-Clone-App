package com.example.flickrr.search_package;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.flickrr.R;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    public EditText searchbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        search = (EditText) view.findViewById(R.id.search_bar);

        mTabLayout = view.findViewById(R.id.search_tab_layout);
        mViewPager = view.findViewById(R.id.search_view_pager);
        searchbar = view.findViewById(R.id.search_bar);
        sharedPreferences = getContext().getSharedPreferences(getContext().getPackageName(), Context.MODE_PRIVATE);

        searchbar.setText(sharedPreferences.getString("Search",""));
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Search",charSequence.toString());
                editor.apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), 1);
        mViewPager.setAdapter(viewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }
}