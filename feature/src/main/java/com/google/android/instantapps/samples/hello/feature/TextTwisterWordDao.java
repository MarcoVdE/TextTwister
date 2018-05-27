package com.google.android.instantapps.samples.hello.feature;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TextTwisterWordDao {
    @Query("SELECT * FROM TextTwisterWord")
    List<TextTwisterWord> getAll();

    @Query("SELECT * FROM TextTwisterWord WHERE guessChars IN (:guessChars)")
    List<TextTwisterWord> loadAllByGuessChars(int[] guessChars);

    @Query("SELECT * FROM TextTwisterWord WHERE guessChars LIKE :guessChars AND "
            + "wordsList LIKE :wordsList LIMIT 1")
    TextTwisterWord findByName(String guessChars, List<String> wordsList);

    @Insert
    void insertAll(TextTwisterWord... textTwisterWords);

    @Delete
    void delete(TextTwisterWord textTwisterWord);

}
