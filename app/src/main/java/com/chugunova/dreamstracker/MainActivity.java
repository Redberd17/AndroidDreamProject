package com.chugunova.dreamstracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;

import com.chugunova.dreamstracker.login.FragmentLogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ad = new AlertDialog.Builder(this);
        ad.setTitle("Warning");
        ad.setMessage("Do you want to close the app?");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                finish();
            }
        });

        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });

        FragmentLogin fragmentLogin = new FragmentLogin();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentLogin);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            /*FragmentLogin fragmentLogin = new FragmentLogin();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.main, fragmentLogin);
            fragmentTransaction.commit();*/

            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStackImmediate("Login", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                ad.show();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}