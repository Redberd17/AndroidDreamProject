package com.chugunova.dreamstracker.login;

import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.dreams.FragmentMainDreams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentLogin extends Fragment {

    private LoginPresenter mPresenter;
    public static String ARG_USERNAME = "arg_username";
    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new LoginPresenter();
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
        argument.putString(ARG_USERNAME, editText.getText().toString());

        AppCompatActivity activity = (AppCompatActivity)requireContext();
        FragmentMainDreams fragmentMainDreams = new FragmentMainDreams();
        fragmentMainDreams.setArguments(argument);
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentMainDreams);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}