package com.chugunova.dreamstracker.newdream;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.maindream.FragmentMainDreams;
import com.chugunova.dreamstracker.model.Dream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import okhttp3.ResponseBody;
import retrofit2.*;

public class FragmentNewDream extends Fragment {

    //private LoginPresenter mPresenter;
    public static String ARG_USERNAME = "arg_username";
    private TextView dreamName, dreamDuration, dreamText;
    private Dream dream;
    private String userName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadDataFromArgument();
        //mPresenter = new LoginPresenter();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.new_dream_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dreamName = view.findViewById(R.id.name_new_dream);
        dreamDuration = view.findViewById(R.id.duration_new_dream);
        dreamText = view.findViewById(R.id.text_new_dream);
    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.onViewResumed(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_send) {
            if (dreamName.getText().toString().isEmpty() || dreamText.getText().toString().isEmpty() || dreamDuration.getText().toString().isEmpty()) {
                showToast("Data incorrect");
                return false;
            }
            dream = new Dream(dreamName.getText().toString(), dreamText.getText().toString(), Double.parseDouble(dreamDuration.getText().toString()));
            ConfigRetrofit.getInstance()
                    .sendDream(userName, dream)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                            if (response.isSuccessful()) {
                                Bundle argument = new Bundle();
                                argument.putString(ARG_USERNAME, userName);
                                AppCompatActivity activity = (AppCompatActivity)requireContext();
                                FragmentMainDreams fragmentMainDreams = new FragmentMainDreams();
                                //fragmentMainDreams.setArguments(argument);

                                FragmentManager fm = activity.getSupportFragmentManager();
                                fm.popBackStack();

                                //FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentMainDreams);
                                //fragmentTransaction.commit();
                            } else {
                                showToast("Плохой запрос");
                            }
                        /*else
                        {

                        }*/
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                            Toast toast = Toast.makeText(getActivity(), "No response from server", Toast.LENGTH_LONG);
                            toast.show();
                            t.printStackTrace();
                        }
                    });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    private void loadDataFromArgument() {
        assert getArguments() != null;
        userName = getArguments().getString(ARG_USERNAME);
    }
}