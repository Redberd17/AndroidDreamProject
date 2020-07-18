package com.chugunova.dreamstracker.registration;

import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import com.chugunova.dreamstracker.MainActivity;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.login.LoginFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RegistrationFragment extends Fragment {

    private RegistrationPresenter mPresenter;
    private EditText editTextLogin;
    private EditText editTextPassword;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new RegistrationPresenter();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        MainActivity.needShowAlertDialog = false;
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextLogin = view.findViewById(R.id.username_reg);
        editTextPassword = view.findViewById(R.id.password_reg);

        view.findViewById(R.id.registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextLogin.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()) {
                    showToast(getString(R.string.fields_is_empty));
                } else {
                    mPresenter.onRegistrationButtonPressed(editTextLogin.getText().toString(), editTextPassword.getText().toString());
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onViewResumed(this);
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void showLoginFragment() {
        AppCompatActivity activity = (AppCompatActivity)requireContext();

        LoginFragment loginFragment = new LoginFragment();

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, loginFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}