package com.chugunova.dreamstracker.maindream;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.chugunova.dreamstracker.MainActivity;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.message.AllMessageFragment;
import com.chugunova.dreamstracker.model.Dream;
import com.chugunova.dreamstracker.newdream.NewDreamFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import static com.chugunova.dreamstracker.login.LoginFragment.ARG_TOKEN;
import static com.chugunova.dreamstracker.login.LoginFragment.ARG_USERNAME;

public class AllDreamsFragment extends Fragment {

    public static String ARG_DREAM_ID = "arg_dream_id";
    public static String ARG_DREAM_DATE = "arg_dream_date";
    public static String ARG_DREAM_NAME = "arg_dream_name";
    public static String ARG_DREAM_TEXT = "arg_dream_text";
    public static String ARG_DREAM_DURATION = "arg_dream_duration";
    private String username;

    private String token;

    TextView emptyView;
    public ImageButton fab;
    public ImageButton button_chat;
    private AllDreamsPresenter mPresenter;
    private View mView;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadDataFromArgument();
        mPresenter = new AllDreamsPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity.needShowAlertDialog = true;
        final View view = inflater.inflate(R.layout.content_dream, container, false);

        emptyView = view.findViewById(R.id.empty_view);
        mView = view;
        fab = view.findViewById(R.id.fab);
        button_chat = view.findViewById(R.id.button_chat);
        recyclerView = mView.findViewById(R.id.list);

        mPresenter.onCreateView(token);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewDreamFragment();
            }
        });
        button_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllMessage();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onViewResumed(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_send).setVisible(false);
        menu.findItem(R.id.smile).setVisible(false);
        menu.findItem(R.id.action_registration).setVisible(false);
        menu.findItem(R.id.delete).setVisible(false);
    }

    public void showAllDreams(List<Dream> dreams, String token) {
        DataAdapterDreams adapter = new DataAdapterDreams(getContext(), dreams, token, username);
        recyclerView.setAdapter(adapter);
    }

    private void showNewDreamFragment() {
        Bundle argument = new Bundle();
        argument.putString(ARG_TOKEN, token);
        argument.putString(ARG_USERNAME, username);

        AppCompatActivity activity = (AppCompatActivity)mView.getContext();

        NewDreamFragment newDreamFragment = new NewDreamFragment();
        newDreamFragment.setArguments(argument);

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, newDreamFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showAllMessage() {
        Bundle argument = new Bundle();
        argument.putString(ARG_TOKEN, token);
        argument.putString(ARG_USERNAME, username);

        AppCompatActivity activity = (AppCompatActivity)mView.getContext();

        AllMessageFragment allMessageFragment = new AllMessageFragment();
        allMessageFragment.setArguments(argument);

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, allMessageFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void loadDataFromArgument() {
        assert getArguments() != null;
        token = getArguments().getString(ARG_TOKEN);
        username = getArguments().getString(ARG_USERNAME);
    }

    public void showTextNoData() {
        TextView textView = mView.findViewById(R.id.empty_view);
        textView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }
}