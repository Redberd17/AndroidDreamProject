package com.chugunova.dreamstracker.currentdream;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.AdviceDuration;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import static com.chugunova.dreamstracker.login.LoginFragment.ARG_TOKEN;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_DATE;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_DURATION;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_NAME;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_TEXT;

public class CurrentDreamFragment extends Fragment {

    private CurrentDreamPresenter mPresenter;
    public static String ARG_USERNAME = "arg_username";
    private String dreamDate, dreamName, dreamText;
    Double dreamDuration;
    MenuItem item;
    AdviceDuration adviceDuration;
    private String token;

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
        mPresenter.onCreateView(dreamDuration, token);
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
        item = menu.findItem(R.id.smile);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.smile) {
            showAdviceDuration();
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
        dreamDate = getArguments().getString(ARG_DREAM_DATE);
        dreamName = getArguments() != null ? getArguments().getString(ARG_DREAM_NAME) : null;
        dreamDuration = getArguments().getDouble(ARG_DREAM_DURATION);
        dreamText = getArguments().getString(ARG_DREAM_TEXT);
        token = getArguments().getString(ARG_TOKEN);
    }

    public void getAdviceDuration(AdviceDuration adviceDuration) {
        this.adviceDuration = adviceDuration;
        configSmile(adviceDuration);
    }

    private void configSmile(AdviceDuration adviceDuration) {
        switch (adviceDuration.getAdviceDurGrade()) {
            case 2: {
                item.setIcon(R.drawable.bad_dream);
                break;
            }
            case 3: {
                item.setIcon(R.drawable.normal_dream);
                break;
            }
            case 4: {
                item.setIcon(R.drawable.good_dream);
                break;
            }
            case 5: {
                item.setIcon(R.drawable.perfect_dream);
                break;
            }
        }
    }

    private void showAdviceDuration() {
        final AlertDialog.Builder ad = new AlertDialog.Builder(requireContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        ad.setView(customLayout);
        TextView text = customLayout.findViewById(R.id.textCustomDialog);
        TextView title = customLayout.findViewById(R.id.titleCustomDialog);
        Button button = customLayout.findViewById(R.id.custom_dialog_button);
        title.setText(adviceDuration.getAdviceDurName());
        text.setText(adviceDuration.getAdviceDurText());
        final AlertDialog dialog = ad.create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}