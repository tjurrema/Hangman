package com.example.hangmanpoging2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.view.MenuInflater;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the rules button */
    public void sendRules(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayRulesActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the start game button */
    public void sendStart(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the High Scores button */
    public void sendScores(View view) {

        Intent intent = new Intent(this, DisplayHighScores.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Quit button */
    public void sendQuit(View view) {
        // Do something in response to button
                finish();

    }

}

