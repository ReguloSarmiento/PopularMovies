package regulo.udacity.popularmovies.restclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import regulo.udacity.popularmovies.utilities.SharedPrefsHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Concrete implementation to load Movies from the API.
 */

public class InMemoryMoviesRepository implements IMovieRepository{

    private final IRestClient mRestClient;

    public InMemoryMoviesRepository(IRestClient mRestClient) {
        this.mRestClient = mRestClient;
    }

    @Override
    public void getMovies(@NonNull int page, @NonNull LoadMoviesCallback callback) {
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
    public void getGenres(@NonNull Context context) {
        mRestClient.getGenres()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            response -> SharedPrefsHelper.saveListOfGenres(context, response.getGenres()),

            Throwable -> Log.d(InMemoryMoviesRepository.class.getName(), Throwable.getMessage())
        );
    }
}
