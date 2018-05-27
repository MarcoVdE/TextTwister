package com.google.android.instantapps.samples.hello.feature;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class TextTwisterWordDao_Impl implements TextTwisterWordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTextTwisterWord;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTextTwisterWord;

  public TextTwisterWordDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTextTwisterWord = new EntityInsertionAdapter<TextTwisterWord>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TextTwisterWord`(`guessWord`,`guessChars`,`wordsList`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TextTwisterWord value) {
        if (value.getGuessWord() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getGuessWord());
        }
        if (value.getGuessChars() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGuessChars());
        }
        final String _tmp;
        _tmp = Converters.fromArrayLisr(value.getWordsList());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
      }
    };
    this.__deletionAdapterOfTextTwisterWord = new EntityDeletionOrUpdateAdapter<TextTwisterWord>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TextTwisterWord` WHERE `guessWord` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TextTwisterWord value) {
        if (value.getGuessWord() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getGuessWord());
        }
      }
    };
  }

  @Override
  public void insertAll(TextTwisterWord... textTwisterWords) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTextTwisterWord.insert(textTwisterWords);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(TextTwisterWord textTwisterWord) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTextTwisterWord.handle(textTwisterWord);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<TextTwisterWord> getAll() {
    final String _sql = "SELECT * FROM TextTwisterWord";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfGuessWord = _cursor.getColumnIndexOrThrow("guessWord");
      final int _cursorIndexOfGuessChars = _cursor.getColumnIndexOrThrow("guessChars");
      final int _cursorIndexOfWordsList = _cursor.getColumnIndexOrThrow("wordsList");
      final List<TextTwisterWord> _result = new ArrayList<TextTwisterWord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TextTwisterWord _item;
        _item = new TextTwisterWord();
        final String _tmpGuessWord;
        _tmpGuessWord = _cursor.getString(_cursorIndexOfGuessWord);
        _item.setGuessWord(_tmpGuessWord);
        final String _tmpGuessChars;
        _tmpGuessChars = _cursor.getString(_cursorIndexOfGuessChars);
        _item.setGuessChars(_tmpGuessChars);
        final List<String> _tmpWordsList;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfWordsList);
        _tmpWordsList = Converters.fromString(_tmp);
        _item.setWordsList(_tmpWordsList);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TextTwisterWord> loadAllByGuessChars(int[] guessChars) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM TextTwisterWord WHERE guessChars IN (");
    final int _inputSize = guessChars.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int _item : guessChars) {
      _statement.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfGuessWord = _cursor.getColumnIndexOrThrow("guessWord");
      final int _cursorIndexOfGuessChars = _cursor.getColumnIndexOrThrow("guessChars");
      final int _cursorIndexOfWordsList = _cursor.getColumnIndexOrThrow("wordsList");
      final List<TextTwisterWord> _result = new ArrayList<TextTwisterWord>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TextTwisterWord _item_1;
        _item_1 = new TextTwisterWord();
        final String _tmpGuessWord;
        _tmpGuessWord = _cursor.getString(_cursorIndexOfGuessWord);
        _item_1.setGuessWord(_tmpGuessWord);
        final String _tmpGuessChars;
        _tmpGuessChars = _cursor.getString(_cursorIndexOfGuessChars);
        _item_1.setGuessChars(_tmpGuessChars);
        final List<String> _tmpWordsList;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfWordsList);
        _tmpWordsList = Converters.fromString(_tmp);
        _item_1.setWordsList(_tmpWordsList);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TextTwisterWord findByName(String guessChars, List<String> wordsList) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM TextTwisterWord WHERE guessChars LIKE ");
    _stringBuilder.append("?");
    _stringBuilder.append(" AND wordsList LIKE ");
    final int _inputSize = wordsList.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(" LIMIT 1");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 1 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    if (guessChars == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, guessChars);
    }
    _argIndex = 2;
    for (String _item : wordsList) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfGuessWord = _cursor.getColumnIndexOrThrow("guessWord");
      final int _cursorIndexOfGuessChars = _cursor.getColumnIndexOrThrow("guessChars");
      final int _cursorIndexOfWordsList = _cursor.getColumnIndexOrThrow("wordsList");
      final TextTwisterWord _result;
      if(_cursor.moveToFirst()) {
        _result = new TextTwisterWord();
        final String _tmpGuessWord;
        _tmpGuessWord = _cursor.getString(_cursorIndexOfGuessWord);
        _result.setGuessWord(_tmpGuessWord);
        final String _tmpGuessChars;
        _tmpGuessChars = _cursor.getString(_cursorIndexOfGuessChars);
        _result.setGuessChars(_tmpGuessChars);
        final List<String> _tmpWordsList;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfWordsList);
        _tmpWordsList = Converters.fromString(_tmp);
        _result.setWordsList(_tmpWordsList);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
