package com.chugunova.dreamstracker.login;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.model.User;

import androidx.annotation.NonNull;
import retrofit2.*;

public class LoginPresenter {

    private FragmentLogin mView;

    public void onViewResumed(FragmentLogin view) {
        mView = view;
    }

    public void onLoginButtonPressed(String username) {
        ConfigRetrofit.getInstance()
                .getUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {

                        if (response.isSuccessful()) {
                            User user = response.body();
                            mView.showMainFragment();
                        } else {
                            mView.showToast("Username incorrect");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        mView.showToast("No connection to server");
                        t.printStackTrace();
                    }
                });
    }
}
