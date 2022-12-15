package com.example.flickrr.ui.login;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flickrr.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupStartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupStartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextInputEditText fname, lname, dob;
    private TextView loginTv, signupBottomText;
    private Button nextBtn;
    private ImageButton backBtn;
    private TextWatcher textWatcher;
    private LinearLayout signupBottomLayout;
    private DatePicker datePicker;

    public SignupStartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupStartFragment.
     */

    public static SignupStartFragment newInstance(String param1, String param2) {
        SignupStartFragment fragment = new SignupStartFragment();
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
        return inflater.inflate(R.layout.fragment_signup_start, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fname = view.findViewById(R.id.fname);
        lname = view.findViewById(R.id.lname);
        dob = view.findViewById(R.id.dob);
        nextBtn = view.findViewById(R.id.nextBtn);
        backBtn = view.findViewById(R.id.backBtn);
        signupBottomText = view.findViewById(R.id.signupBottomText);
        signupBottomLayout = view.findViewById(R.id.signupBottomLayout);
        datePicker = view.findViewById(R.id.datePicker);
        loginTv = view.findViewById(R.id.loginLink);

        dob.setShowSoftInputOnFocus(false);

        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    //hide keyboard if in focus
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    setDatePickerVisible(true);
                } else {
                    setDatePickerVisible(false);
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            //set text to field when scroll datePicker
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        });

        setTextWatcher();
        setOnNext();
        setOnLogin();
        setOnBack();
    }

    private void setDatePickerVisible(boolean show) {
        if (show) {
            signupBottomText.setVisibility(View.GONE);
            signupBottomLayout.setVisibility(View.GONE);
            datePicker.setVisibility(View.VISIBLE);
        } else {
            signupBottomText.setVisibility(View.VISIBLE);
            signupBottomLayout.setVisibility(View.VISIBLE);
            datePicker.setVisibility(View.GONE);
        }
    }

    private void setOnBack() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack(null,0);
            }
        });
    }

    private void setOnNext() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fname.getText().toString().equals("") &&
                        !lname.getText().toString().equals("") &&
                        !dob.getText().toString().equals("")) {
                    Fragment signupFragment = new SignupFragment();
                    replaceFragment(signupFragment);
                } else {
                    if(fname.getText().toString().equals("")) fname.setError("sus");
                    if(lname.getText().toString().equals("")) lname.setError("sus");
                    if(dob.getText().toString().equals("")) dob.setError("sus");
                }
            }
        });
    }

    private void setOnLogin() {
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment loginFragment = new LoginFragment();
                replaceFragment(loginFragment);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        if (fname.hasFocus()) fname.clearFocus();
        if (lname.hasFocus()) lname.clearFocus();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.login_fragmentContainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    private void setTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!nextBtn.isEnabled()) {
                    if (!fname.getText().toString().equals("") &&
                            !lname.getText().toString().equals("") &&
                            !dob.getText().toString().equals("")) {
                        nextBtn.setEnabled(true);
                        nextBtn.setAlpha(1f);
                    }
                } else {
                    if (fname.getText().toString().equals("") ||
                            lname.getText().toString().equals("") ||
                            dob.getText().toString().equals("")) {
                        nextBtn.setEnabled(false);
                        nextBtn.setAlpha(0.3f);
                    }
                }
            }
        };
        fname.addTextChangedListener(textWatcher);
        lname.addTextChangedListener(textWatcher);
        dob.addTextChangedListener(textWatcher);
    }
}