package com.example.hangmanpoging2;

/**
 * Created by Tessa on 21-1-14.
 */
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.view.View.OnKeyListener;
import android.widget.Toast;

public class Game extends ActionBarActivity {

    TextView word;
    TextView guessed;
    TextView chancesView;
    EditText input;
    Button SendInput;
    Button NewGame;
    Button Back;

    // text variable holds String representation of the hidden word
    public String hiddenWord;

    // a String for the guessed letters by user
    String guessed_letters = "";

    //how many chances the users gets
    int chances;

    //how many mistakes the users make (for the High score)
    int mistakes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        //to simplify it at first, the user has 8 chances
        chances = getIntent().getIntExtra("chances", 8);
        if (chances == 0) {
            Toast.makeText(getApplicationContext(), "You can't have zero chances, now you've one chance", Toast.LENGTH_LONG).show();
            chances = chances + 1;
        }
        chancesView = (TextView) findViewById(R.id.chancesView);
        chancesView.setText(String.valueOf(chances));

        //mistakes
        mistakes = 0;

        //load a word from the dictionary
        hiddenWord= loadWord();

        //encode the word
        word = (TextView)findViewById(R.id.word);
        word.setText(encode(hiddenWord));

        //shows the guessed letters from the users
        guessed = (TextView)findViewById(R.id.guessed);
        guessed.setText(guessed_letters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
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

    private String loadWord() {

        Random rgenerator = new Random();
        String result = "BOAR";
        Resources res = getResources();
        String [] words = res.getStringArray(R.array.words_small);
        int pickWord = rgenerator.nextInt(words.length);
        result = words[pickWord];
        return result;
    }

    private String encode(String hiddenWord) {
        String encode = "";
        for (int i=0; i < hiddenWord.length() ;i++) {
            encode +=("_ ");
        }
        return encode;
    }

    /** Called when the user clicks the Send button */
    public void SendInput(View view) {

        // users input
        input= (EditText)findViewById(R.id.input);
        String nInput = input.getText().toString();

        //if the input is empty
        if (nInput.length() == 0) {
            //clear the input
            Toast.makeText(getApplicationContext(), "You didn't type a letter, please try again", Toast.LENGTH_LONG).show();
            input.setText("");
        }

        else if (guessed_letters.indexOf(nInput.toUpperCase()) >= 0) {
            //clear the input
            Toast.makeText(getApplicationContext(), "You already guessed this letter, try another one", Toast.LENGTH_LONG).show();
            input.setText("");
        }

        else {
            //make the input in Uppercase
            String newInput = nInput.toUpperCase();

            //add string to guessed letters
            input.setText(""); //clear input

            guessed_letters += newInput;
            guessed.setText(guessed_letters);

            //checks if the letter is in the word & if the users has won
            word.setText(compareLetters(hiddenWord, newInput));

            //if the letter isn't in the hiddenWord: chances -1
            if(hiddenWord.indexOf(newInput) == -1) {
                chances = chances - 1;
                chancesView.setText(String.valueOf(chances));

                //increment the mistakes
                mistakes = mistakes + 1;
            }

            //the user loose the game if the chances are zero
            if (chances == 0) {
                Toast.makeText(getApplicationContext(), "The word was: " + hiddenWord, Toast.LENGTH_LONG).show();
                setContentView(R.layout.gameover);
            }
        }
    }

    private String compareLetters (String hiddenWord,String newInput ) {
        String compare = "";
        for (int i=0; i < hiddenWord.length() ;i++) {
            if (hiddenWord.charAt(i) == newInput.charAt(0)) {
                // display letter
                compare += hiddenWord.charAt(i) + " ";
            }
            else if (guessed_letters.indexOf(hiddenWord.charAt(i)) != -1) {
                //checks that the users already put in
                compare += hiddenWord.charAt(i) + " ";
            }
            else {
                //place a placeholder if the user didn't guessed the letter
                compare += "_ ";
            }
        }
        //if the user has won, their would not be any _ in the string compare
        if(! compare.contains("_ ")) {
            //add scores to database
            MySQLiteHelper db = new MySQLiteHelper(this);
            db.addScores(new Scores(mistakes));

            Toast.makeText(getApplicationContext(), "The word was: " + hiddenWord + ", and you've made "
                    + mistakes + " mistakes", Toast.LENGTH_LONG).show();

            //go to the winning page
            setContentView(R.layout.win);
        }
        return compare;
    }

    //Called when the user clicks on the back button button
    public void GoBack(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks on the new game button
    public void NewGame(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks on the new game button
    public void GoHighScore(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayHighScores.class);
        startActivity(intent);
    }
}

