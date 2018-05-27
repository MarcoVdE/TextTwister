package com.google.android.instantapps.samples.hello.feature;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Database(entities = {TextTwisterWord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    final private static String databaseName = "texttwister-db";

    public abstract TextTwisterWordDao textTwisterWordInterface();


    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, databaseName)
                            // allow queries on the main thread. TODO: Move off of the main thread.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private static TextTwisterWord addTextTwisterWord(final AppDatabase db, TextTwisterWord textTwisterWord) {
        db.textTwisterWordInterface().insertAll(textTwisterWord);
        return textTwisterWord;
    }

    private static ArrayList<String> testWordList() {
        ArrayList<String> guessableOptions = new ArrayList<>();
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
        return guessableOptions;
    }

    private static void populateWithTestData(AppDatabase db) {
        TextTwisterWord textTwisterWord = new TextTwisterWord();
        textTwisterWord.setGuessWord("apple");
        textTwisterWord.setWordsList(testWordList());

        addTextTwisterWord(db, textTwisterWord);
    }
}

