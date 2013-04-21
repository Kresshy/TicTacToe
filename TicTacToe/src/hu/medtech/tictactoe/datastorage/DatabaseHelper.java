package hu.medtech.tictactoe.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context, String name) {
		super(context, name, null, DatabaseConstants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DatabaseConstants.DATABASE_CREATE_ALL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DatabaseConstants.DATABASE_DROP_ALL);
		db.execSQL(DatabaseConstants.DATABASE_CREATE_ALL);
	}

}
