package com.chugunova.dreamstracker.currentdream;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.chugunova.dreamstracker.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.chugunova.dreamstracker.maindream.FragmentMainDreams.ARG_DREAM_DATE;
import static com.chugunova.dreamstracker.maindream.FragmentMainDreams.ARG_DREAM_DURATION;
import static com.chugunova.dreamstracker.maindream.FragmentMainDreams.ARG_DREAM_NAME;
import static com.chugunova.dreamstracker.maindream.FragmentMainDreams.ARG_DREAM_TEXT;

public class FragmentDream extends Fragment {

    //private LoginPresenter mPresenter;
    public static String ARG_USERNAME = "arg_username";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //mPresenter = new LoginPresenter();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_dream, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dreamDateTv = view.findViewById(R.id.date_dream);
        TextView dreamNameTv = view.findViewById(R.id.name_dream);
        TextView dreamDurationTv = view.findViewById(R.id.dream_duration);
        TextView dreamTextTv = view.findViewById(R.id.text_dream);

        assert getArguments() != null;
        String dreamDate = getArguments().getString(ARG_DREAM_DATE);
        String dreamName = getArguments().getString(ARG_DREAM_NAME);
        Double dreamDuration = getArguments().getDouble(ARG_DREAM_DURATION);
        String dreamText = getArguments().getString(ARG_DREAM_TEXT);

        dreamDateTv.setText(dreamDate);
        dreamNameTv.setText(dreamName);
        dreamDurationTv.setText(dreamDuration + " ч");
        dreamTextTv.setText(dreamText);
    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.onViewResumed(this);
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

    public void showMainFragment() {
       /* Bundle argument = new Bundle();
        argument.putString(ARG_USERNAME, editText.getText().toString());

        AppCompatActivity activity = (AppCompatActivity)requireContext();
        FragmentMainDreams fragmentMainDreams = new FragmentMainDreams();
        fragmentMainDreams.setArguments(argument);
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentMainDreams);
        fragmentTransaction.commit();*/
    }
}