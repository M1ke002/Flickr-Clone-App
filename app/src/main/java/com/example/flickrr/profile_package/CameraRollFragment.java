package com.example.flickrr.profile_package;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.flickrr.R;

public class CameraRollFragment extends Fragment {
    Button btn;

    public CameraRollFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera_profile, container, false);
        btn = (Button) view.findViewById(R.id.button);

        btn.setOnClickListener(view1 -> Toast.makeText(getContext(), "There is no photo in your gallery", Toast.LENGTH_SHORT).show());

        return view;
    }
}