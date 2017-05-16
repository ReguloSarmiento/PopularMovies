package regulo.udacity.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies;

public class FavoritesMovieDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    public FavoritesMovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " +
                FavoritesMovies.TABLE_NAME + " (" +
                FavoritesMovies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoritesMovies.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                FavoritesMovies.COLUMN_TITLE + " TEXT NOT NULL, " +
                FavoritesMovies.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                FavoritesMovies.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                FavoritesMovies.COLUMN_POPULARITY + " REAL NOT NULL, " +
                FavoritesMovies.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                FavoritesMovies.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                FavoritesMovies.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                FavoritesMovies.COLUMN_GENRES + " TEXT NOT NULL" + ");";

        db.execSQL(SQL_CREATE_FAVORITES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS "+ FavoritesMovies.TABLE_NAME);
      onCreate(db);
    }
}
