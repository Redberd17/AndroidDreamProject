package com.chugunova.dreamstracker.newdream;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.chugunova.dreamstracker.MainActivity;
import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.maindream.AllDreamsFragment;
import com.chugunova.dreamstracker.model.Dream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static com.chugunova.dreamstracker.login.LoginFragment.ARG_TOKEN;

public class NewDreamFragment extends Fragment {

    public static String ARG_USERNAME = "arg_username";
    private TextView dreamName, dreamDuration, dreamText;
    private NewDreamPresenter mPresenter;
    private String userName;
    private String token;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadDataFromArgument();
        mPresenter = new NewDreamPresenter();
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
        MainActivity.needShowAlertDialog = false;
        dreamName = view.findViewById(R.id.name_new_dream);
        dreamDuration = view.findViewById(R.id.duration_new_dream);
        dreamText = view.findViewById(R.id.text_new_dream);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onViewResumed(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.smile).setVisible(false);
        menu.findItem(R.id.action_registration).setVisible(false);
        menu.findItem(R.id.delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_send) {
            if (dreamName.getText().toString().isEmpty() || dreamText.getText().toString().isEmpty() || dreamDuration.getText().toString().isEmpty()) {
                showToast(getString(R.string.data_incorrect));
                return false;
            }
            Dream dream = new Dream(dreamName.getText().toString(), dreamText.getText().toString(), Double.parseDouble(dreamDuration.getText().toString()));
            mPresenter.onButtonSendPressed(token, dream);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAllDreamsFragment() {
        Bundle argument = new Bundle();
        argument.putString(ARG_TOKEN, token);
        argument.putString(ARG_USERNAME, userName);
        AppCompatActivity activity = (AppCompatActivity)requireContext();
        AllDreamsFragment allDreamsFragment = new AllDreamsFragment();
        allDreamsFragment.setArguments(argument);
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, allDreamsFragment);
        fragmentTransaction.commit();
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    private void loadDataFromArgument() {
        assert getArguments() != null;
        userName = getArguments().getString(ARG_USERNAME);
        token = getArguments().getString(ARG_TOKEN);
    }
}