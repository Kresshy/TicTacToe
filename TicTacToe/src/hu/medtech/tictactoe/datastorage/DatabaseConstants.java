package hu.medtech.tictactoe.datastorage;

public class DatabaseConstants {

	// fajlnev, amiben az adatbazis lesz
	public static final String DATABASE_NAME = "scores.db";
	// verzioszam
	public static final int DATABASE_VERSION = 1;
	// osszes belso osztaly DATABASE_CREATE szkriptje osszefuzve
	public static String DATABASE_CREATE_ALL = ScoreDB.DATABASE_CREATE;
	// osszes belso osztaly DATABASE_DROP szkriptje osszefuzve
	public static String DATABASE_DROP_ALL = ScoreDB.DATABASE_DROP;

	// Score osztaly DB konstansai
	public static class ScoreDB {
		// tabla neve
		public static final String DATABASE_TABLE = "highscores_table";
		// oszlopnevek
		public static final String KEY_ROWID = "_id";
		public static final String KEY_NAME = "nev";
		public static final String KEY_TIME = "time";
		public static final String KEY_PONT = "pont";

		// sema letrehozo szkript
		public static final String DATABASE_CREATE = "create table if not exists "
				+ DATABASE_TABLE
				+ " ( "
				+ KEY_ROWID
				+ " integer primary key autoincrement, "
				+ KEY_NAME
				+ " text not null, "
				+ KEY_TIME
				+ " text, "
				+ KEY_PONT
				+ " text " + "); ";
		// sema torlo szkript
		public static final String DATABASE_DROP = "drop table if exists "
				+ DATABASE_TABLE + "; ";
	}
}
