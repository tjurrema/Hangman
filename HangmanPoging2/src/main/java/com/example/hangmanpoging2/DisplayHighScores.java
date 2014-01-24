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
import android.widget.TextView;

import java.util.List;

public class DisplayHighScores extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_high_scores);

        MySQLiteHelper db = new MySQLiteHelper(this);

        // get Top ten scores
        List<Scores> list = db.getTopTenScores();
        int [] Views =  { R.id.score1, R.id.score2, R.id.score3, R.id.score4, R.id.score5, R.id.score6,
                R.id.score7, R.id.score8, R.id.score9, R.id.score10};

        for ( int i=0; i < list.size(); i ++) {
            Scores first = list.get(i);
            Integer score = first.getScores();
            TextView View = (TextView) findViewById(Views[i]);
            View.setText(String.valueOf(score));

        }
        // get top ten scores
        db.getTopTenScores();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_high_scores, menu);
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

}
