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
        return list;
    }
}