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

public class PeopleFragment extends Fragment {
    private RecyclerView rcvPeople;
    private PeopleAdapter peopleAdapter;

    public PeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_search, container, false);

        rcvPeople = view.findViewById(R.id.rcv_people);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        rcvPeople.setLayoutManager(linearLayoutManager);

        peopleAdapter = new PeopleAdapter(getListPeople());
        rcvPeople.setAdapter(peopleAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        rcvPeople.addItemDecoration(itemDecoration);
        return view;
    }

    private List<People> getListPeople(){
        List<People> list = new ArrayList<>();
        list.add(new People(R.drawable.img1, "Alice", "Hanoi"));
        list.add(new People(R.drawable.img2, "Bob", "London"));
        list.add(new People(R.drawable.img3, "Caitlyn", "Paris"));
        list.add(new People(R.drawable.img4, "Duo", "Ohio"));
        list.add(new People(R.drawable.img5, "Emily", "Vancouver"));
        list.add(new People(R.drawable.img6, "Fofana", "Tokyo"));
        list.add(new People(R.drawable.img7, "Granger", "Osaka"));
        list.add(new People(R.drawable.img8, "Hugo", "Beijing"));
        list.add(new People(R.drawable.img9, "Iva", "New York"));
        list.add(new People(R.drawable.img10, "Anna", "Las Vegas"));
        return list;
    }
}