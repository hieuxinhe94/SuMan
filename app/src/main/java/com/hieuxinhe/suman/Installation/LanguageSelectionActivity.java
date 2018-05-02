package com.hieuxinhe.suman.Installation;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hieuxinhe.suman.Constants.LocateLanguageConstants;
import com.hieuxinhe.suman.R;
import com.hieuxinhe.suman.Utils.ApplicationState;
import com.hieuxinhe.suman.Utils.SystemHelper;

import java.util.Locale;

public class LanguageSelectionActivity extends AppCompatActivity {
    ListView listView;
    private CoordinatorLayout coordinatorLayout;
    private ApplicationState applicationState;
    TextView txtLanguage,txtGuide;
    Button btnSkipSelectLanguage;
    String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        listView = (ListView) findViewById(R.id.listLanguage);
        txtLanguage = findViewById(R.id.txtCurrentLanguage);
        txtGuide = findViewById(R.id.txtGuide);
        btnSkipSelectLanguage = findViewById(R.id.btnSkipSelectLanguage);


        initLaguageData();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        applicationState = new ApplicationState(this);

        // TODO: Wait for select language
        language = Locale.getDefault().getDisplayLanguage();
        txtLanguage.setText("Current Language: " + language);

        listView.setOnItemClickListener(OnItemClick());
        listView.setOnTouchListener(setOnTouchListener());
        btnSkipSelectLanguage.setOnClickListener(OnBtnSkipClick());
    }

    private Button.OnClickListener OnBtnSkipClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(LanguageSelectionActivity.this, "You selected default device language", Toast.LENGTH_LONG ).show();
                applicationState.setLanguageSetup(language);
                launchInstallation();
            }
        };
    }

    private View.OnTouchListener setOnTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        };
    }

    private AdapterView.OnItemClickListener OnItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // ListView Clicked item value
                String itemValue = (String) LocateLanguageConstants.data[i];

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "You selected :" + itemValue, Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(LanguageSelectionActivity.this, LanguageSelectionActivity.class));
                                finish();
                            }
                        });

                // Changing message text color
                snackbar.setActionTextColor(Color.RED);

                // Changing action button text color
                View sbView = snackbar.getView();

                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
                btnSkipSelectLanguage.setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
                txtGuide.setVisibility(View.VISIBLE);
                // TODO : Mapping this text with locate
                applicationState.setLanguageSetup(itemValue);
                applicationState.LOCALE = SystemHelper.ConvertLocateStringToLocate(itemValue);
            }
        };
    }

    private  void launchInstallation()
    {
        startActivity(new Intent(LanguageSelectionActivity.this, InstallationActivity.class));
        finish();
    }

    private void initLaguageData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, LocateLanguageConstants.dataDetail);
        listView.setAdapter(adapter);
    }
}
