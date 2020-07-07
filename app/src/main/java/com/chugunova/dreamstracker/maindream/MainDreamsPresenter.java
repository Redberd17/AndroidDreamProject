package com.chugunova.dreamstracker.maindream;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.model.User;

import androidx.annotation.NonNull;
import retrofit2.*;

public class MainDreamsPresenter {

    private FragmentMainDreams mView;

    public void onViewResumed(FragmentMainDreams view) {
        mView = view;
    }

    public void onCreateView(String username) {
        ConfigRetrofit.getInstance()
                .getUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {

                        if (response.isSuccessful()) {
                            User user = response.body();
                            //mView.showMainFragment();

                        } else {
                            //mView.showToast("Username incorrect");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        //mView.showToast("No connection to server");
                        t.printStackTrace();
                    }
                });
    }
}
