package regulo.udacity.popularmovies.restclient;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import regulo.udacity.popularmovies.models.Movie;

/**
 * Main entry point for accessing Movies data.
 */
public interface IMovieRepository {

    interface LoadMoviesCallback {

        void onMoviesLoaded(List<Movie> movies);

        void onMoviesFailure();
    }

    void getMovies(@NonNull final int page, @NonNull LoadMoviesCallback callback);

    void getGenres(@NonNull final Context context);
}
