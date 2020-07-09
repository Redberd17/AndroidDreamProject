package com.chugunova.dreamstracker.newdream;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.Dream;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.*;

public class NewDreamPresenter {

    private NewDreamFragment mView;

    public void onViewResumed(NewDreamFragment view) {
        mView = view;
    }

    public void onButtonSendPressed(String userName, Dream dream) {
        ConfigRetrofit.getInstance()
                .sendDream(userName, dream)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                        if (response.isSuccessful()) {
                            mView.showAllDreamsFragment();
                        } else {
                            mView.showToast(mView.requireContext().getString(R.string.dream_not_saved));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        mView.showToast(mView.requireContext().getString(R.string.no_response_from_server));
                        t.printStackTrace();
                    }
                });
    }
}