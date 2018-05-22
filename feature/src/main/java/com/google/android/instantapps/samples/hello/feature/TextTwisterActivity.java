package com.google.android.instantapps.samples.hello.feature;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TextTwisterActivity extends AppCompatActivity {

    TextView guessedLetters; //letters that user has guessed.
    ArrayList<Button> guessLetters; //all buttons generated to click for guessable letters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_twister);

        //setting variables to IDs
         guessedLetters = findViewById(R.id.guessed_letters);

        createWordButtons();
        submitGuessActionCreator();
    }

    private void createWordButtons() {
        String myWord = "Apple";
        final ArrayList<String> wordCollection = new ArrayList<>();
        LinearLayout linear1 = findViewById(R.id.generate_buttons_1);
        LinearLayout linear2 = findViewById(R.id.generate_buttons_2);
        guessLetters = new ArrayList<>(); //to store all guessable buttons

        for(int i = 0; i < myWord.length(); i++){
            wordCollection.add(myWord.substring(i, i+1));
        }

        for(int i = 0; i < wordCollection.size(); i++) {
            final Button letterButton = new Button(this);
            final String letterCharacter = wordCollection.get(i);
            letterButton.setText(wordCollection.get(i));
            letterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keepTrackOfClicked(letterCharacter);
                    letterButton.setEnabled(false);
                }
            });
            if(i > 3){ //if over 4 letters, move to next row
                linear2.addView(letterButton);
            }else{
                linear1.addView(letterButton);
            }

            guessLetters.add(letterButton);
        }

    }


    private void keepTrackOfClicked(String buttonClicked) {
        guessedLetters.setText(String.format("%s%s", guessedLetters.getText(), buttonClicked));
    }

    private void submitGuessActionCreator() {
        Button submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(TextTwisterActivity.this);
                if(guessedLetters.getText().toString().equalsIgnoreCase("Apple")) {
                    builder.setMessage("Your guess was correct!")
                            .show();

                }else {
                    builder.setMessage("Your guess was wrong!")
                            .show();
                }

                //clear after empty guess.
                resetGuess();
            }
        });
    }

    private void resetGuess() {
        guessedLetters.setText("");

        //re-enable guessedLetters
        for(Button guessButton: guessLetters) {
            guessButton.setEnabled(true);
        }
    }



}
