package com.chugunova.dreamstracker.currentdream;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.model.AdviceDuration;

import androidx.annotation.NonNull;
import retrofit2.*;

public class CurrentDreamPresenter {

    private CurrentDreamFragment mView;

    public void onViewResumed(CurrentDreamFragment view) {
        mView = view;
    }

    public void onCreateView(Double dreamDuration) {
        ConfigRetrofit.getInstance()
                .getAdviceDuration(dreamDuration)
                .enqueue(new Callback<AdviceDuration>() {
                    @Override
                    public void onResponse(@NonNull Call<AdviceDuration> call, @NonNull Response<AdviceDuration> response) {

                        if (response.isSuccessful()) {
                            AdviceDuration adviceDuration = response.body();
                            mView.getAdviceDuration(adviceDuration);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AdviceDuration> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
