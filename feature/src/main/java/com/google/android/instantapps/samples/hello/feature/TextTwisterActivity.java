package com.google.android.instantapps.samples.hello.feature;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TextTwisterActivity extends AppCompatActivity {

    TextView guessedLetters; //letters that user has guessed.
    ArrayList<Button> guessLetters; //all buttons generated to click for guessable letters
    LinearLayout linear1, linear2;
    ArrayList<String> wordCollection;
    ArrayList<String> guessableOptions;
    String finalWord;
    TextView score;

    TextView countdownTimer;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_twister);

        //setting variables to IDs
         guessedLetters = findViewById(R.id.guessed_letters);


        finalWord = "Apple";
        score = findViewById(R.id.score);
        score.setText(String.format("%s 0", getString(R.string.score)));
        guessableOptions = new ArrayList<>();
        guessableOptions.add("ale");
        guessableOptions.add("alp");
        guessableOptions.add("ape");
        guessableOptions.add("app");
        guessableOptions.add("lap");
        guessableOptions.add("lea");
        guessableOptions.add("pal");
        guessableOptions.add("pap");
        guessableOptions.add("pea");
        guessableOptions.add("pep");
        guessableOptions.add("leap");
        guessableOptions.add("pale");
        guessableOptions.add("palp");
        guessableOptions.add("peal");
        guessableOptions.add("plea");
        guessableOptions.add("appel");
        guessableOptions.add("apple");
        guessableOptions.add("pepla");

        //alert dialog builder:
        builder = new AlertDialog.Builder(TextTwisterActivity.this);

        createWordButtons(finalWord);
        submitGuessActionCreator();
        shuffleButtonActionCreator();
        startCountdownTimer();
    }

    private void startCountdownTimer() {
        new CountDownTimer(20000, 100) {
            public void onTick(long millisUntiFinished) {
                countdownTimer = findViewById(R.id.countdown_timer);
                countdownTimer.setText(String.valueOf(millisUntiFinished));
            }

            @Override
            public void onFinish() {
                countdownTimer.setText("0");
                builder.setMessage("Time over, your score was: " + score.getText())
                       .show();
            }
        }.start();
    }

    private void createWordButtons(String myWord) {
        wordCollection = new ArrayList<>();
        linear1 = findViewById(R.id.generate_buttons_1);
        linear2 = findViewById(R.id.generate_buttons_2);
        guessLetters = new ArrayList<>(); //to store all guessable buttons

        for(int i = 0; i < myWord.length(); i++){
            wordCollection.add(myWord.substring(i, i+1));
        }

        Collections.shuffle(wordCollection, new Random(System.nanoTime()));

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
        guessedLetters.setText(String.format("%s%s", guessedLetters.getText(), buttonClicked.toUpperCase()));
    }

    private void submitGuessActionCreator() {
        Button submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
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

    private void shuffleButtonActionCreator() {
        Button clearButtons = findViewById(R.id.button_shuffle);
        clearButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first destroy the current buttons
                clearLinearViews();
                resetGuess();
                createWordButtons(finalWord);
            }
        });



    }

    private void clearLinearViews() {
        linear1.removeAllViews();
        linear2.removeAllViews();
    }

    private void checkGuess(){


        if(guessableOptions.contains(guessedLetters.getText().toString().toLowerCase())){
            builder.setMessage("Your guess was correct!")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //does nothing
                        }
                    })
                    .show();
            updateScore(guessedLetters.getText().length() * 100);
        }else {
            builder.setMessage("Your guess was wrong! (" + guessedLetters.getText().toString() + ")")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //does nothing as failed
                        }
                    })
                    .show();
        }

        resetGuess();

    }

    private void updateScore(int addition) {
        score.setText(String.format("%s %s", getString(R.string.score), String.valueOf(Integer.valueOf(score.getText().toString().substring(7)) + addition)));
    }


}
