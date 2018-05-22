package com.google.android.instantapps.samples.hello.feature;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TextTwisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_twister);

        createWordButton();
    }

    private void createWordButton() {
        String myWord = "Apple";
        ArrayList<String> wordCollection = new ArrayList<>();
        LinearLayout linear1 = (LinearLayout)findViewById(R.id.generate_buttons_1);
        LinearLayout linear2 = (LinearLayout)findViewById(R.id.generate_buttons_2);

        for(int i = 0; i < myWord.length(); i++){
            wordCollection.add(myWord.substring(i, i+1));
        }

        for(int i = 0; i < wordCollection.size(); i++) {
            Button letterButton = new Button(this);
            letterButton.setText(wordCollection.get(i));
            if(i > 3){
                linear2.addView(letterButton);
            }else{
                linear1.addView(letterButton);
            }
        }

    }

}
