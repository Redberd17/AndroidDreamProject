package com.chugunova.dreamstracker.registration;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.User;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.*;

public class RegistrationPresenter {

    private RegistrationFragment mView;

    public void onViewResumed(RegistrationFragment view) {
        mView = view;
    }

    public void onRegistrationButtonPressed(String username, String password) {
        User user = new User(username, password);
        ConfigRetrofit.getInstance()
                .registrationUser(user)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                        if (response.isSuccessful()) {
                            mView.showLoginFragment();
                            mView.showToast(mView.requireContext().getString(R.string.user_was_added));
                        } else {
                            mView.showToast(mView.requireContext().getString(R.string.error_registration));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        mView.showToast(mView.requireContext().getString(R.string.no_connection_to_server));
                        t.printStackTrace();
                    }
                });
    }
}
