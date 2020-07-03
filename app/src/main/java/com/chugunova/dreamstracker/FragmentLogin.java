package com.chugunova.dreamstracker;

import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.users.User;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import retrofit2.*;

public class FragmentLogin extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editText = view.findViewById(R.id.username);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigRetrofit.getInstance()
                        .getUser(editText.getText().toString())
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                if (response.isSuccessful()) {
                                    User user = response.body();

                                    NavHostFragment.findNavController(FragmentLogin.this)
                                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                                } else {
                                    Toast toast = Toast.makeText(requireContext(), "Username incorrect", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast toast = Toast.makeText(requireContext(), "No connection to server", Toast.LENGTH_LONG);
                                toast.show();
                                t.printStackTrace();
                            }
                        });
            }
        });
    }
}