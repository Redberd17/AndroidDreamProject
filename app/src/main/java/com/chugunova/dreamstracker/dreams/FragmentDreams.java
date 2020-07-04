package com.chugunova.dreamstracker.dreams;

import android.os.Bundle;
import android.view.*;
import android.widget.Toast;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.model.Dream;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.*;

import static com.chugunova.dreamstracker.login.FragmentLogin.ARG_USERNAME;

public class FragmentDreams extends Fragment {

    private String userName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadDataFromArgument();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        final View view = inflater.inflate(R.layout.content_dream, container, false);

        ConfigRetrofit.getInstance()
                .getDreams(userName)
                .enqueue(new Callback<List<Dream>>() {
                    @Override
                    public void onResponse(Call<List<Dream>> call, Response<List<Dream>> response) {

                        if (response.isSuccessful()) {
                            final List<Dream> dreams = response.body();
                            RecyclerView recyclerView = view.findViewById(R.id.list);
                            DataAdapterDreams adapter = new DataAdapterDreams(getContext(), dreams);
                            recyclerView.setAdapter(adapter);
                        }
                        /*else
                        {

                        }*/
                    }

                    @Override
                    public void onFailure(Call<List<Dream>> call, Throwable t) {
                        Toast toast = Toast.makeText(getActivity(), "No response from server", Toast.LENGTH_LONG);
                        toast.show();
                        t.printStackTrace();
                    }
                });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadDataFromArgument() {
        assert getArguments() != null;
        userName = getArguments().getString(ARG_USERNAME);
    }
}