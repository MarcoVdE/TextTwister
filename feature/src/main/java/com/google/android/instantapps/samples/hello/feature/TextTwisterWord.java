package com.google.android.instantapps.samples.hello.feature;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(indices = {@Index(value = {"guessChars"}, unique = true)})
public class TextTwisterWord {
    @PrimaryKey
    private String guessWord;

    @ColumnInfo(name = "guessChars")
    private String guessChars;

    @ColumnInfo(name = "wordsList")
    private List<String> wordsList;

    public String getGuessWord() {
        return guessWord;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }

    public String getGuessChars() {
        return guessChars;
    }

    public void setGuessChars(String guessChars) {
        this.guessChars = guessChars;
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }
}
