package com.example.erudite.databases;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.example.erudite.data.databases.QuestionDao;
import com.example.erudite.model.Questions;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class QuestionDao_Impl implements QuestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Questions> __insertionAdapterOfQuestions;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public QuestionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestions = new EntityInsertionAdapter<Questions>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `questions` (`questionId`,`question`,`answer`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Questions value) {
        stmt.bindLong(1, value.getId());
        if (value.getQuestion() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getQuestion());
        }
        if (value.getAnswer() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAnswer());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM questions";
        return _query;
      }
    };
  }

  @Override
  public void insertQuestion(final Questions question) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestions.insert(question);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Questions> selectAll() {
    final String _sql = "SELECT * FROM questions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "questionId");
      final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
      final int _cursorIndexOfAnswer = CursorUtil.getColumnIndexOrThrow(_cursor, "answer");
      final List<Questions> _result = new ArrayList<Questions>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Questions _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpQuestion;
        if (_cursor.isNull(_cursorIndexOfQuestion)) {
          _tmpQuestion = null;
        } else {
          _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
        }
        final String _tmpAnswer;
        if (_cursor.isNull(_cursorIndexOfAnswer)) {
          _tmpAnswer = null;
        } else {
          _tmpAnswer = _cursor.getString(_cursorIndexOfAnswer);
        }
        _item = new Questions(_tmpId,_tmpQuestion,_tmpAnswer);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCountQuestion() {
    final String _sql = "SELECT COUNT(questionId) FROM questions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Questions getBodyQuestion(final int id) {
    final String _sql = "SELECT questionId, question, answer FROM questions WHERE questionId= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfQuestion = 1;
      final int _cursorIndexOfAnswer = 2;
      final Questions _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpQuestion;
        if (_cursor.isNull(_cursorIndexOfQuestion)) {
          _tmpQuestion = null;
        } else {
          _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
        }
        final String _tmpAnswer;
        if (_cursor.isNull(_cursorIndexOfAnswer)) {
          _tmpAnswer = null;
        } else {
          _tmpAnswer = _cursor.getString(_cursorIndexOfAnswer);
        }
        _result = new Questions(_tmpId,_tmpQuestion,_tmpAnswer);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
