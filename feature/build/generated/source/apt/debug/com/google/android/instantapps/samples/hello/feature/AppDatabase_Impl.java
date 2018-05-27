package com.google.android.instantapps.samples.hello.feature;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile TextTwisterWordDao _textTwisterWordDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TextTwisterWord` (`guessWord` TEXT NOT NULL, `guessChars` TEXT, `wordsList` TEXT, PRIMARY KEY(`guessWord`))");
        _db.execSQL("CREATE UNIQUE INDEX `index_TextTwisterWord_guessChars` ON `TextTwisterWord` (`guessChars`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"fb424805fe61c559fcadc25969b4caf5\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `TextTwisterWord`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTextTwisterWord = new HashMap<String, TableInfo.Column>(3);
        _columnsTextTwisterWord.put("guessWord", new TableInfo.Column("guessWord", "TEXT", true, 1));
        _columnsTextTwisterWord.put("guessChars", new TableInfo.Column("guessChars", "TEXT", false, 0));
        _columnsTextTwisterWord.put("wordsList", new TableInfo.Column("wordsList", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTextTwisterWord = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTextTwisterWord = new HashSet<TableInfo.Index>(1);
        _indicesTextTwisterWord.add(new TableInfo.Index("index_TextTwisterWord_guessChars", true, Arrays.asList("guessChars")));
        final TableInfo _infoTextTwisterWord = new TableInfo("TextTwisterWord", _columnsTextTwisterWord, _foreignKeysTextTwisterWord, _indicesTextTwisterWord);
        final TableInfo _existingTextTwisterWord = TableInfo.read(_db, "TextTwisterWord");
        if (! _infoTextTwisterWord.equals(_existingTextTwisterWord)) {
          throw new IllegalStateException("Migration didn't properly handle TextTwisterWord(com.google.android.instantapps.samples.hello.feature.TextTwisterWord).\n"
                  + " Expected:\n" + _infoTextTwisterWord + "\n"
                  + " Found:\n" + _existingTextTwisterWord);
        }
      }
    }, "fb424805fe61c559fcadc25969b4caf5", "000562e2435e6243b5ed8951e889f127");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "TextTwisterWord");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `TextTwisterWord`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TextTwisterWordDao textTwisterWordInterface() {
    if (_textTwisterWordDao != null) {
      return _textTwisterWordDao;
    } else {
      synchronized(this) {
        if(_textTwisterWordDao == null) {
          _textTwisterWordDao = new TextTwisterWordDao_Impl(this);
        }
        return _textTwisterWordDao;
      }
    }
  }
}
