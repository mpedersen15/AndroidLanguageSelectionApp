package com.example.matt3865.languageselection;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String selectedLanguage = "English";
    TextView languageTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageTextView = findViewById(R.id.languageTextView);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        String currentLanguage = sharedPreferences.getString("languageSelection", "English");

        languageTextView.setText(currentLanguage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();

        inflator.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.i("Option selected", item.getTitle().toString());

        final String newLanguage = item.getTitle().toString();

        new AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("Click confirm or cancel.")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveLanguageSelection(newLanguage);

                        languageTextView.setText(newLanguage);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();

        return super.onOptionsItemSelected(item);
    }

    private void saveLanguageSelection (String language) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("languageSelection", language);
        editor.commit();
    }
}
