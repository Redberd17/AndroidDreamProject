package com.chugunova.dreamstracker;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;

import com.chugunova.dreamstracker.login.LoginFragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedUsername;
    private final String SHARED_USERNAME = "shared_username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        showLoginFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorToolbar));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            deleteUsername();
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStackImmediate("Login", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(getString(R.string.warning));
        ad.setMessage(getString(R.string.warning_message));
        ad.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                finish();
            }
        });

        ad.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad.show();
    }

    private void showLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.main, loginFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                showAlertDialog();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void deleteUsername() {
        LoginFragment.username = "";
        sharedUsername = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedUsername.edit();
        ed.clear();
        ed.apply();
    }
}