package com.example.flickrr.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flickrr.MainActivity;
import com.example.flickrr.R;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextInputEditText email, password;
    private MaterialCheckBox checkBox1, checkBox2;
    private Button signupBtn;
    private TextView loginTv;
    private ImageButton backBtn;
    private TextWatcher textWatcher;
    private Context context;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        email = view.findViewById(R.id.emailSignup);
        password = view.findViewById(R.id.passwordSignup);
        checkBox1 = view.findViewById(R.id.checkboxSignup1);
        checkBox2 = view.findViewById(R.id.checkboxSignup2);
        signupBtn = view.findViewById(R.id.signupBtn);
        loginTv = view.findViewById(R.id.loginLink);
        backBtn = view.findViewById(R.id.backBtn);

        setTextWatcher();
        setOnSignup();
        setOnLogin();
        setOnBack();
    }

    private void setOnSignup() {
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (email.getText().toString().equals("")) email.setError("sus");
                    if (password.getText().toString().equals("")) password.setError("sus");
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

    private void setOnBack() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack(null,0);
            }
        });
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
                if (password.getText().hashCode() == editable.hashCode()) { //password text field
                    if (editable.length() >= 12) {
                        checkBox1.setChecked(true);
                    } else {
                        checkBox1.setChecked(false);
                    }
                    if (!editable.toString().startsWith(" ") && !editable.toString().equals("")) {
                        checkBox2.setChecked(true);
                    } else {
                        checkBox2.setChecked(false);
                    }
                }
                if (!signupBtn.isEnabled()) {
                    if (!email.getText().toString().equals("") && checkBox1.isChecked() && checkBox2.isChecked()) {
                        signupBtn.setEnabled(true);
                        signupBtn.setAlpha(1f);
                    }
                } else {
                    if (email.getText().toString().equals("") || !checkBox1.isChecked() || !checkBox2.isChecked()) {
                        signupBtn.setEnabled(false);
                        signupBtn.setAlpha(0.3f);
                    }
                }
            }
        };
        email.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
    }

    private void replaceFragment(Fragment fragment) {
        if (email.hasFocus()) email.clearFocus();
        if (password.hasFocus()) password.clearFocus();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .replace(R.id.login_fragmentContainer, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}