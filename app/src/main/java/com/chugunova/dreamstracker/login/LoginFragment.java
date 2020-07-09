package com.chugunova.dreamstracker.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.maindream.AllDreamsFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    private LoginPresenter mPresenter;
    public static String ARG_USERNAME = "arg_username";
    private EditText editText;

    SharedPreferences sharedUsername;
    private final String SHARED_USERNAME = "shared_username";
    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new LoginPresenter();
        loadUserName();
        if (username != null && !username.isEmpty()) {
            saveUserName(username);
            mPresenter.onLoginButtonPressed(username);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = view.findViewById(R.id.username);

        view.findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onLoginButtonPressed(editText.getText().toString());
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

    public void showMainFragment() {
        Bundle argument = new Bundle();

        if (username != null && !username.isEmpty()) {
            argument.putString(ARG_USERNAME, username);
            saveUserName(username);
        } else {
            argument.putString(ARG_USERNAME, editText.getText().toString());
            saveUserName(editText.getText().toString());
        }

        AppCompatActivity activity = (AppCompatActivity)requireContext();

        AllDreamsFragment allDreamsFragment = new AllDreamsFragment();

        allDreamsFragment.setArguments(argument);

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, allDreamsFragment);
        fragmentTransaction.addToBackStack("Login");
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void saveUserName(String username) {
        AppCompatActivity activity = (AppCompatActivity)requireContext();
        sharedUsername = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedUsername.edit();
        ed.putString(SHARED_USERNAME, username);
        ed.apply();
    }

    private void loadUserName() {
        AppCompatActivity activity = (AppCompatActivity)requireContext();
        sharedUsername = activity.getPreferences(MODE_PRIVATE);
        username = sharedUsername.getString(SHARED_USERNAME, "");
    }
}