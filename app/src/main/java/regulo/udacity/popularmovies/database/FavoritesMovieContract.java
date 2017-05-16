package regulo.udacity.popularmovies.database;

import android.net.Uri;
import android.provider.BaseColumns;


public class FavoritesMovieContract {

    public static final String AUTHORITY = "regulo.udacity.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_FAVORITES = "favorites";

    public static final class FavoritesMovies implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();
        public static final String TABLE_NAME = "favorites";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_GENRES = "genres";

        public static final String[] PROJECTION = new String[]{
                _ID,
                COLUMN_MOVIE_ID,
                COLUMN_TITLE,
                COLUMN_OVERVIEW,
                COLUMN_RELEASE_DATE,
                COLUMN_POPULARITY,
                COLUMN_VOTE_AVERAGE,
                COLUMN_POSTER_PATH,
                COLUMN_BACKDROP_PATH,
                COLUMN_GENRES
        };

        public static final int INDEX_COLUMN_ID = 1;
        public static final int INDEX_COLUMN_TITLE = 2;
        public static final int INDEX_COLUMN_OVERVIEW = 3;
        public static final int INDEX_COLUMN_DATE = 4;
        public static final int INDEX_COLUMN_POPULARITY = 5;
        public static final int INDEX_COLUMN_VOTE = 6;
        public static final int INDEX_COLUMN_POSTER = 7;
        public static final int INDEX_COLUMN_BACKDROP = 8;
        public static final int INDEX_COLUMN_GENRES = 9;
    }
}
