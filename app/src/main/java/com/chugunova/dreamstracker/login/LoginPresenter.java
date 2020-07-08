package com.chugunova.dreamstracker.login;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.User;

import androidx.annotation.NonNull;
import retrofit2.*;

public class LoginPresenter {

    private LoginFragment mView;

    public void onViewResumed(LoginFragment view) {
        mView = view;
    }

    public void onLoginButtonPressed(String username) {
        ConfigRetrofit.getInstance()
                .getUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {

                        if (response.isSuccessful()) {
                            mView.showMainFragment();
                        } else {
                            mView.showToast(mView.requireContext().getString(R.string.username_incorrect));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        mView.showToast(mView.requireContext().getString(R.string.no_connection_to_server));
                        t.printStackTrace();
                    }
                });
    }
}
