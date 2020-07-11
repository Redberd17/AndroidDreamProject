package com.chugunova.dreamstracker.login;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.User;
import com.chugunova.dreamstracker.model.UserSecurity;

import androidx.annotation.NonNull;
import retrofit2.*;

public class LoginPresenter {

    private LoginFragment mView;

    public void onViewResumed(LoginFragment view) {
        mView = view;
    }

    public void onLoginButtonPressed(String username, String password) {
        User user = new User(username, password);
        ConfigRetrofit.getInstance()
                .authorizationUser(user)
                .enqueue(new Callback<UserSecurity>() {
                    @Override
                    public void onResponse(@NonNull Call<UserSecurity> call, @NonNull Response<UserSecurity> response) {

                        if (response.isSuccessful()) {
                            UserSecurity userSecurity = response.body();
                            mView.showMainFragment(userSecurity);
                        } else {
                            mView.showToast(mView.requireContext().getString(R.string.username_incorrect));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserSecurity> call, @NonNull Throwable t) {
                        mView.showToast(mView.requireContext().getString(R.string.no_connection_to_server));
                        t.printStackTrace();
                    }
                });
    }
}
