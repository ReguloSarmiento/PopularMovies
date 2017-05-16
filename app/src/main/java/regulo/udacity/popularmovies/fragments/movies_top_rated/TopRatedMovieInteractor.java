package regulo.udacity.popularmovies.fragments.movies_top_rated;


import regulo.udacity.popularmovies.restclient.IRestClient;
import regulo.udacity.popularmovies.restclient.RestUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopRatedMovieInteractor {

    private final ITopRatedMovieContract.InteractorFinishedListener mInteractorListener;
    private final IRestClient mRestClient;

    public TopRatedMovieInteractor(ITopRatedMovieContract.InteractorFinishedListener mInteractorListener) {
        this.mInteractorListener = mInteractorListener;
        this.mRestClient = RestUtils.createRestClient();
    }

    public void loadTopRatedMovies(final int page){
        mRestClient.getTopRatedMovies(page)
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
}
