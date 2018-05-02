package com.hieuxinhe.suman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hieuxinhe.suman.Installation.LanguageSelectionActivity;
import com.hieuxinhe.suman.Utils.ApplicationState;

import java.util.ArrayList;


public abstract class BaseActivity extends AppCompatActivity {
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());

        if (!hasLeftMenu()) {
            initView();
            initData();

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public boolean hasLeftMenu() {
        // Nothing
        return false;
    }

    abstract public int getContentLayout();

    abstract public void initView();

    abstract public void initData();


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void promptSpeechInput() {
        Toast.makeText(getApplicationContext(),
                "You must speech with " + ApplicationState.LOCALE.getDisplayName() + " language.",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, ApplicationState.LOCALE);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_LONG).show();
            // : FW to SearchIssueActivity
            launchSearchIssueActivityScreen();
        }
    }

    public void launchInstallationScreen() {

        startActivity(new Intent(this, LanguageSelectionActivity.class));
        finish();
    }

    public void launchSearchIssueActivityScreen() {

        startActivity(new Intent(this, SearchListIssueActivity.class));
        finish();
    }

    public void showKeyboard(View target) {
        if (target == null) {
            return;
        }
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(target,
                InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void showAlert(final String title, final String message) {
        if (!isFinishing()) {
            try {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            new AlertDialog.Builder(BaseActivity.this).setTitle(title)
                                    .setMessage(message)
                                    .setNegativeButton(android.R.string.ok, null).create()
                                    .show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void showAlert(@StringRes final int titleResId, @StringRes final int messageResId) {
        if (!isFinishing()) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        new AlertDialog.Builder(BaseActivity.this).setTitle(titleResId)
                                .setMessage(messageResId)
                                .setNegativeButton(android.R.string.ok, null).create()
                                .show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void startActivityWithAnimation(Intent intent) {
        startActivity(intent);

    }


}
