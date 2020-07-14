package com.chugunova.dreamstracker.currentdream;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.AdviceDuration;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.*;

public class CurrentDreamPresenter {

    private CurrentDreamFragment mView;

    public void onViewResumed(CurrentDreamFragment view) {
        mView = view;
    }

    public void onCreateView(Double dreamDuration, String token) {
        ConfigRetrofit.getInstance()
                .getAdviceDuration(token, dreamDuration)
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

    public void onDeleteDialogButtonYesPressed(String token, Integer dreamId) {
        ConfigRetrofit.getInstance()
                .deleteUserDreams(token, dreamId)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                        if (response.isSuccessful()) {
                            mView.showAllDreamsFragment();
                            mView.showToast(mView.requireContext().getString(R.string.dream_was_deleted));
                        } else {
                            mView.showAllDreamsFragment();
                            mView.showToast(mView.requireContext().getString(R.string.dream_not_deleted));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        mView.showToast(mView.requireContext().getString(R.string.no_connection_to_server));
                    }
                });
    }
}
