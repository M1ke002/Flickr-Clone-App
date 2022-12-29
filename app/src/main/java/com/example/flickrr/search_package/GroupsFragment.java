package com.example.flickrr.search_package;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrr.R;

import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment {
    private RecyclerView rcvGroups;
    private GroupsAdapter groupsAdapter;

    public GroupsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups_search, container, false);

        rcvGroups = view.findViewById(R.id.rcv_groups);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        rcvGroups.setLayoutManager(linearLayoutManager);

        groupsAdapter = new GroupsAdapter(getListGroups());
        rcvGroups.setAdapter(groupsAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        rcvGroups.addItemDecoration(itemDecoration);
        return view;
    }

    private List<Groups> getListGroups(){
        List<Groups> list = new ArrayList<>();
        list.add(new Groups(R.drawable.img10, "The Boys", "500 Members", "96K Photos"));
        list.add(new Groups(R.drawable.img9, "Me and friends", "314 Members", "753 Photos"));
        list.add(new Groups(R.drawable.img8, "Girl generation", "628 Members", "31K Photos"));
        list.add(new Groups(R.drawable.img7, "Learn to rock", "10 Members", "84K Photos"));
        list.add(new Groups(R.drawable.img6, "NSND", "50 Members", "98K Photos"));
        list.add(new Groups(R.drawable.img5, "Mountain", "136 Members", "72K Photos"));
        list.add(new Groups(R.drawable.img4, "Baro", "1K Members", "33K Photos"));
        list.add(new Groups(R.drawable.img3, "IT community", "24 Members", "613 Photos"));
        list.add(new Groups(R.drawable.img2, "Bros", "7 Members", "31 Photos"));
        list.add(new Groups(R.drawable.img1, "Sis", "360 Members", "4K Photos"));
        return list;
    }
}