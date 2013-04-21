package hu.medtech.tictactoe.datastorage;

import hu.medtech.tictactoe.Score;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ScoreDbLoader {

	private Context ctx;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase mDb;

	public ScoreDbLoader(Context ctx) {
		this.ctx = ctx;
	}

	// megnyitas
	public void open() throws SQLException {
		dbHelper = new DatabaseHelper(ctx, DatabaseConstants.DATABASE_NAME);
		mDb = dbHelper.getWritableDatabase();
		dbHelper.onCreate(mDb);
	}

	// bezaras
	public void close() {
		dbHelper.close();
	}

	// uj elem beszurasa
	public long createScore(Score score) {
		ContentValues values = new ContentValues();
		values.put(DatabaseConstants.ScoreDB.KEY_NAME, score.getNev());
		values.put(DatabaseConstants.ScoreDB.KEY_TIME, score.getIdo());
		values.put(DatabaseConstants.ScoreDB.KEY_PONT, score.getPont());

		return mDb.insert(DatabaseConstants.ScoreDB.DATABASE_TABLE, null,
				values);
	}

	// osszes elem torlese
	public void deleteAll() {
		mDb.delete(DatabaseConstants.ScoreDB.DATABASE_TABLE, null, null);
	}

	// egy elem torlese
	public boolean deleteScore(long rowId) {
		return mDb.delete(DatabaseConstants.ScoreDB.DATABASE_TABLE,
				DatabaseConstants.ScoreDB.KEY_ROWID + "=" + rowId, null) > 0;
	}

	// frissites
	public boolean updateProduct(long rowId, Score newScore) {
		ContentValues values = new ContentValues();
		values.put(DatabaseConstants.ScoreDB.KEY_NAME, newScore.getNev());
		values.put(DatabaseConstants.ScoreDB.KEY_TIME, newScore.getIdo());
		values.put(DatabaseConstants.ScoreDB.KEY_PONT, newScore.getPont());

		return mDb.update(DatabaseConstants.ScoreDB.DATABASE_TABLE, values,
				DatabaseConstants.ScoreDB.KEY_ROWID + "=" + rowId, null) > 0;
	}

	// score lekerese cursor alapjan
	public static Score getScoreByCursor(Cursor c) {
		return new Score(c.getString(c
				.getColumnIndex(DatabaseConstants.ScoreDB.KEY_NAME)),
				c.getString(c
						.getColumnIndex(DatabaseConstants.ScoreDB.KEY_TIME)),
				c.getString(c
						.getColumnIndex(DatabaseConstants.ScoreDB.KEY_PONT)));
	}

	// egy score lekerese
	public Score fetchScore(long rowId) {
		Cursor c = mDb.query(DatabaseConstants.ScoreDB.DATABASE_TABLE,
				new String[] { DatabaseConstants.ScoreDB.KEY_ROWID,
						DatabaseConstants.ScoreDB.KEY_NAME,
						DatabaseConstants.ScoreDB.KEY_TIME,
						DatabaseConstants.ScoreDB.KEY_PONT

				}, DatabaseConstants.ScoreDB.KEY_ROWID + "=" + rowId, null,
				null, null, DatabaseConstants.ScoreDB.KEY_NAME);
		if (c.moveToFirst())
			return getScoreByCursor(c);
		return null;
	}

	// minden score lekerese
	public Cursor fetchAll() {
		return mDb.query(DatabaseConstants.ScoreDB.DATABASE_TABLE,
				new String[] { DatabaseConstants.ScoreDB.KEY_ROWID,
						DatabaseConstants.ScoreDB.KEY_NAME,
						DatabaseConstants.ScoreDB.KEY_TIME,
						DatabaseConstants.ScoreDB.KEY_PONT }, null, null, null,
				null, DatabaseConstants.ScoreDB.KEY_PONT + " DESC"); // csokkeno
																		// sorrend
	}
}
