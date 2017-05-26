package regulo.udacity.popularmovies.restclient;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.models.Review;
import regulo.udacity.popularmovies.models.Trailer;


/**
 * Main entry point for accessing Movies data.
 */
public interface IMovieRepository {

    interface LoadMoviesCallback {

        void onMoviesLoaded(List<Movie> movies);

        void onMoviesFailure();
    }

    void getPopular(@NonNull final int page, @NonNull LoadMoviesCallback callback);

    void getTopRated(@NonNull final int page, @NonNull LoadMoviesCallback callback);

    void getGenres(@NonNull final Context context);

    interface LoadReviewsCallback {
        void onReviewsLoaded(List<Review.Result> results);
        void onReviewsFailure();
    }

    void getReviews(@NonNull final int movieID, @NonNull LoadReviewsCallback callback);

    interface LoadTrailerCallback {
        void onTrailerLoaded(List<Trailer.result> trailer);
        void onTrailerFailure();
    }

    void getTrailer(@NonNull final int movieID, @NonNull LoadTrailerCallback callback);
}
