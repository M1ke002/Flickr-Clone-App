package com.example.flickrr.ui.login;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flickrr.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartScreenTextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartScreenTextFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String boldText;
    private String normalText;

    public StartScreenTextFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WelcomeTextFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartScreenTextFragment newInstance(String param1, String param2) {
        StartScreenTextFragment fragment = new StartScreenTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boldText = getArguments().getString("boldText");
        normalText = getArguments().getString("normalText");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_screen_text, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvBold = view.findViewById(R.id.welcomeTextBold);
        tvBold.setTypeface(null, Typeface.BOLD);
        tvBold.setText(boldText);
        ((TextView)view.findViewById(R.id.welcomeTextNormal)).setText(normalText);
    }

}