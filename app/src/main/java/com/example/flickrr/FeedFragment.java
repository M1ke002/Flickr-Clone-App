package com.example.flickrr;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.Photo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    private Flickr flickr;
    private RecyclerView recyclerView;
    private ImagePostAdapter adapter;
    // Initialize the Flickr object with your API key and secret
    private final String API_KEY = "5eba4641389babebdd82bb8be36c278b";
    private final String API_SECRET = "629fc7a760570a91";

    // Page size for the infinite scroll
    private static final int PAGE_SIZE = 4;
    // Current page
    private int currentPage = 1;
    // Flag to track if a load is in progress
    private boolean isLoading = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load the first page of data
        loadData();

        recyclerView = view.findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Add a scroll listener to the RecyclerView to detect when the user has scrolled to the end
        // of the list and start loading more data
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        loadMoreItems();
                    }
                }
            }
        });
    }

    private void loadData() {
        AsyncTask<Void, Void, Void> getData = new AsyncTask<Void, Void, Void>() {
            private List<PostData> res;
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    res = getNewsFeedData();
                } catch (FlickrException e) {
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    Log.d("load data",errors.toString());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // Create the adapter and set it on the RecyclerView
                adapter = new ImagePostAdapter(res);
                recyclerView.setAdapter(adapter);
            }
        };

        getData.execute();
    }

    private void loadMoreItems() {
        isLoading = true;
        currentPage++;

        AsyncTask<Void, Void, Void> getData = new AsyncTask <Void, Void, Void>() {
            private List<PostData> res;
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    res = getNewsFeedData();
                } catch (FlickrException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // Add the new data to the adapter and update the RecyclerView
                adapter.addData(res);
                adapter.notifyDataSetChanged();

                isLoading = false;
            }
        };

        getData.execute();
    }

    private List<PostData> getNewsFeedData() throws FlickrException {
        List<PostData> postData = new ArrayList<>();
        flickr = new Flickr(API_KEY, API_SECRET, new REST());

        PhotosInterface photosInterface = flickr.getPhotosInterface();
        PhotoList<Photo> photos = photosInterface.getRecent(null, 4, currentPage);

        Log.d("photos",""+photos.size());

        photos.forEach(photo -> {
            // Get username
            String userId = photo.getOwner().getId();
            PeopleInterface peopleInterface = flickr.getPeopleInterface();
            String username = "";
            try {
                username = peopleInterface.getInfo(userId).getUsername();
            } catch (FlickrException e) {
                e.printStackTrace();
            }

            Log.d("post data",username+ " "+String.valueOf(photo.getStats().getFavorites())+" "+ photo.getTitle()+ " "+photo.getMediumUrl());

            postData.add(new PostData(
                    username,
                    String.valueOf(photo.getStats().getFavorites()),
                    photo.getTitle(),
                    photo.getMediumUrl()
            ));
        });
        return postData;
    }
}