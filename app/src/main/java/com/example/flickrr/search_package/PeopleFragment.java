package com.example.flickrr.search_package;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrr.ImagePostAdapter;
import com.example.flickrr.PostData;
import com.example.flickrr.R;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.uploader.Uploader;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class PeopleFragment extends Fragment {
    private RecyclerView rcvPeople;
    private PeopleAdapter peopleAdapter;
    private Flickr flickr;
    private final String API_KEY = "54045897f37e9365525445205542d2c5";
    private final String API_SECRET = "8edc1811276afa8b";
    private SharedPreferences sharedPreferences;
    public PeopleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_search, container, false);
        loadData();

        rcvPeople = view.findViewById(R.id.rcv_people);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        rcvPeople.setLayoutManager(linearLayoutManager);

        sharedPreferences = getContext().getSharedPreferences(getContext().getPackageName(), Context.MODE_PRIVATE);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        rcvPeople.addItemDecoration(itemDecoration);
        return view;
    }

    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

    private void loadData() {
        AsyncTask<Void, Void, Void> getData = new AsyncTask<Void, Void, Void>() {
            private List<People>  res;

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    res = getListPeople();
                } catch (FlickrException e) {
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    Log.d("load data", errors.toString());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // Create the adapter and set it on the RecyclerView
                peopleAdapter = new PeopleAdapter(res);
                rcvPeople.setAdapter(peopleAdapter);
            }
        };
        getData.execute();
    }
    private List<People> getListPeople() throws FlickrException {
        List<People> list = new ArrayList<>();
        flickr = new Flickr(API_KEY, API_SECRET, new REST());



        PeopleInterface peopleInterface = flickr.getPeopleInterface();
        User user = null,user2=null;

        try {
            user = peopleInterface.findByUsername(sharedPreferences.getString("Search",""));

            People userc ;
            userc = new People(R.drawable.img1,user.getUsername(),user.getPhotosCount()+"Photos ");
            userc.setUserid(user.getId());
            userc.setUserv(user);
            Log.d("stffff",userc.toString());
            list.add(userc);



        } catch (FlickrException e) {
            e.printStackTrace();

        }

        try{
            user2 = peopleInterface.findByEmail(sharedPreferences.getString("Search",""));
            People userc2 ;
            userc2 = new People(R.drawable.img1,user2.getUsername(),user2.getPhotosCount()+"  Photos ");
            userc2.setUserid(user2.getId());
            userc2.setUserv(user2);
            Log.d("stffff",userc2.toString());
            list.add(userc2);
            Log.d("stffff",user2.getUsername());
        }
        catch (FlickrException e)
        {
            e.printStackTrace();
        }


        return list;
    }
}