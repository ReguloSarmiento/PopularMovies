package regulo.udacity.popularmovies.fragments.movies_popular;


import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;
import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.fragments.movies_popular.IPopularMovieContract.View;

public class PopularMoviePresenter implements IPopularMovieContract.Presenter,
        IPopularMovieContract.InteractorFinishedListener {

    /******************************************************************************************
     MoviesPresenter retrieves data from the model {@link PopularMovieInteractor } and
     notifies the view {@link  PopularMovieFragment } to display it.
     ******************************************************************************************/

    private final PopularMovieInteractor mInteractor;
    private final WeakReference<View> mView;

    public PopularMoviePresenter(@NonNull final View view) {
        this.mInteractor = new PopularMovieInteractor(this);
        this.mView = new WeakReference<>(view);
    }

    @Override
    public void makeRequest(int page, Context context) {
        mInteractor.loadGenres(context);
        mInteractor.loadPopularMovies(page);
    }

    @Override
    public void onNetworkSuccess(List<Movie> movies) {
        if(mView.get() != null) mView.get().onLoadedSuccess(movies);
    }

    @Override
    public void onNetworkFailure(String message) {
        if(mView.get() != null) mView.get().onLoadedFailure(message);
    }
}
