package regulo.udacity.popularmovies.restclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import regulo.udacity.popularmovies.utilities.SharedPrefsHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load Movies from the API.
 */

public class InMemoryMoviesRepository implements IMovieRepository{

    private final IRestClient mRestClient;

    public InMemoryMoviesRepository(@NonNull IRestClient mRestClient) {
        this.mRestClient = checkNotNull(mRestClient, "RestClient cannot be null!");
    }

    @Override
    public void getPopular(@NonNull int page, @NonNull LoadMoviesCallback callback) {
        mRestClient.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                response -> {
                    /* if successful, notifies to the presenter so that it can get a list of movies.*/
                    callback.onMoviesLoaded(response.getResults());
                },

                Throwable -> {
                    //if fails, gets the error message from the server and then notifies to the presenter.
                    callback.onMoviesFailure();
                }
            );
    }

    @Override
    public void getTopRated(@NonNull int page, @NonNull LoadMoviesCallback callback) {
        mRestClient.getTopRatedMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                response -> callback.onMoviesLoaded(response.getResults()),

                Throwable -> callback.onMoviesFailure()
            );
    }

    @Override
    public void getGenres(@NonNull Context context) {
        mRestClient.getGenres()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            response -> SharedPrefsHelper.saveListOfGenres(context, response.getGenres()),

            Throwable -> Log.d(InMemoryMoviesRepository.class.getName(), Throwable.getMessage())
        );
    }

    @Override
    public void getReviews(@NonNull int movieID, @NonNull LoadReviewsCallback callback) {
        mRestClient.getReviews(movieID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            response -> callback.onReviewsLoaded(response.getResults()),

            Throwable -> callback.onReviewsFailure()
        );
    }

    @Override
    public void getTrailer(@NonNull int movieID, @NonNull LoadTrailerCallback callback) {
        mRestClient.getTrailer(movieID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            response -> callback.onTrailerLoaded(response.getResults()),

            Throwable -> callback.onTrailerFailure()
        );
    }
}
