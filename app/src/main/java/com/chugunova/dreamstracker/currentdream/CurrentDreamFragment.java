package com.chugunova.dreamstracker.currentdream;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.chugunova.dreamstracker.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_DATE;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_DURATION;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_NAME;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_TEXT;

public class CurrentDreamFragment extends Fragment {

    private CurrentDreamPresenter mPresenter;
    public static String ARG_USERNAME = "arg_username";
    private String dreamDate, dreamName, dreamText;
    Double dreamDuration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadDataFromArgument();
        mPresenter = new CurrentDreamPresenter();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_dream, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dreamDateTv = view.findViewById(R.id.date_dream);
        TextView dreamNameTv = view.findViewById(R.id.name_dream);
        TextView dreamDurationTv = view.findViewById(R.id.dream_duration);
        TextView dreamTextTv = view.findViewById(R.id.text_dream);

        dreamDateTv.setText(dreamDate);
        dreamNameTv.setText(dreamName);
        dreamDurationTv.setText(String.format("%s%s%s", dreamDuration, getString(R.string.empty), getString(R.string.hours)));
        dreamTextTv.setText(dreamText);
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
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    private void loadDataFromArgument() {
        assert getArguments() != null;
        dreamDate = getArguments().getString(ARG_DREAM_DATE);
        dreamName = getArguments() != null ? getArguments().getString(ARG_DREAM_NAME) : null;
        dreamDuration = getArguments().getDouble(ARG_DREAM_DURATION);
        dreamText = getArguments().getString(ARG_DREAM_TEXT);
    }
}