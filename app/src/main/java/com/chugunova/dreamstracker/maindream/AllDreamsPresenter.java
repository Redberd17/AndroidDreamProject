package com.chugunova.dreamstracker.maindream;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.Dream;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.*;

public class AllDreamsPresenter {

    private AllDreamsFragment mView;

    public void onViewResumed(AllDreamsFragment view) {
        mView = view;
    }

    public void onCreateView(String userName) {
        ConfigRetrofit.getInstance()
                .getDreams(userName)
                .enqueue(new Callback<List<Dream>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Dream>> call, @NonNull Response<List<Dream>> response) {

                        if (response.isSuccessful()) {
                            final List<Dream> dreams = response.body();
                            mView.showAllDreams(dreams);
                        } else {
                            mView.showTextNoData();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Dream>> call, @NonNull Throwable t) {
                        mView.showTextNoData();
                        mView.showToast(mView.requireContext().getString(R.string.no_response_from_server));
                        t.printStackTrace();
                    }
                });
    }
}
