package com.hieuxinhe.suman;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hieuxinhe.suman.Utils.ApplicationState;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    private ApplicationState applicationState;
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (applicationState.isFirstTimeLaunch()) {
            applicationState.setFirstTimeLaunch(false);
            launchInstallationScreen();
            finish();
        }

        // CHECK IF SYSTEM SUPPORTED SPEECH TO TEXT WITH 12 FAMOUS LANGUAGES

        if (applicationState.LOCALE != Locale.ENGLISH) {
            if (isOnline()) {
                //TODO: ST to record and call api to speech to text
                Toast.makeText(getApplicationContext(),
                        "TODO: to record and call api to speech to text",
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // : FW to SearchIssueActivity
                launchSearchIssueActivityScreen();
            }
        }

        promptSpeechInput();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    @Override
    public void initData() {
        applicationState = new ApplicationState(this);
    }



    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    Toast.makeText(getApplicationContext(),
                            "You said: " + result.get(0),
                            Toast.LENGTH_SHORT).show();

                    // TODO: Forward to module 02
                }
                break;
            }

        }
    }

}