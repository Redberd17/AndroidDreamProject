package com.chugunova.dreamstracker.maindream;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.chugunova.dreamstracker.API.ConfigRetrofit;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.Dream;
import com.chugunova.dreamstracker.newdream.FragmentNewDream;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.*;

import static com.chugunova.dreamstracker.login.FragmentLogin.ARG_USERNAME;

public class FragmentMainDreams extends Fragment {

    public static String ARG_DREAM_DATE = "arg_dream_date";
    public static String ARG_DREAM_NAME = "arg_dream_name";
    public static String ARG_DREAM_TEXT = "arg_dream_text";
    public static String ARG_DREAM_DURATION = "arg_dream_duration";


    private String userName;

    TextView emptyView;
    public static FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadDataFromArgument();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_dream, container, false);

        emptyView = view.findViewById(R.id.empty_view);

        fab = view.findViewById(R.id.fab);

        ConfigRetrofit.getInstance()
                .getDreams(userName)
                .enqueue(new Callback<List<Dream>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Dream>> call, @NonNull Response<List<Dream>> response) {

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
                    public void onFailure(@NonNull Call<List<Dream>> call, @NonNull Throwable t) {
                        Toast toast = Toast.makeText(getActivity(), "No response from server", Toast.LENGTH_LONG);
                        toast.show();
                        t.printStackTrace();
                    }
                });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle argument = new Bundle();
                argument.putString(ARG_USERNAME, userName);
                AppCompatActivity activity = (AppCompatActivity)view.getContext();

                FragmentNewDream fragmentNewDream = new FragmentNewDream();

                fragmentNewDream.setArguments(argument);

                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentNewDream);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_send).setVisible(false);
    }

    private void loadDataFromArgument() {
        assert getArguments() != null;
        userName = getArguments().getString(ARG_USERNAME);
    }
}