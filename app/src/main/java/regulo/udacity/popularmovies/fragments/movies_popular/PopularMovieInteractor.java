package regulo.udacity.popularmovies.fragments.movies_popular;


import android.content.Context;
import android.util.Log;

import java.util.List;

import regulo.udacity.popularmovies.fragments.DetailsFragment;
import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.restclient.IRestClient;
import regulo.udacity.popularmovies.restclient.RestUtils;
import regulo.udacity.popularmovies.utilities.SharedPrefsHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopularMovieInteractor {

    /***********************************************************************************************
     - This is the model that gets the data from the Api and provides it to the presenter
     {@link PopularMoviePresenter} to display it on the view.
     ***********************************************************************************************/

    private final IPopularMovieContract.InteractorFinishedListener mInteractorListener;
    private final IRestClient mRestClient;

    public PopularMovieInteractor(IPopularMovieContract.InteractorFinishedListener mInteractorListener) {
        this.mInteractorListener = mInteractorListener;
        this.mRestClient = RestUtils.createRestClient();
    }

    public void loadPopularMovies(final int page){
        mRestClient.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            /* if successful, notifies to the presenter so that it can get a list of movies.*/
                            mInteractorListener.onNetworkSuccess(response.getResults());
                        },

                        Throwable -> {
                            //if fails, gets the error message from the server and then notifies to the presenter.
                            mInteractorListener.onNetworkFailure(Throwable.getMessage());
                        }
                );

    }

    public void loadGenres(final Context context) {
        mRestClient.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            SharedPrefsHelper.saveListOfGenres(context, response.getGenres());
                        },

                        Throwable -> {
                            Log.d(DetailsFragment.class.getName(), Throwable.getMessage());
                        }
                );

    }
}
