package regulo.udacity.popularmovies.fragments.movies_top_rated;


import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import regulo.udacity.popularmovies.models.Movie;

public class TopRatedMoviePresenter implements ITopRatedMovieContract.Presenter,
        ITopRatedMovieContract.InteractorFinishedListener{

    private final TopRatedMovieInteractor mInteractor;
    private final WeakReference<ITopRatedMovieContract.View> mView;

    public TopRatedMoviePresenter(@NonNull final ITopRatedMovieContract.View view) {
        this.mInteractor = new TopRatedMovieInteractor(this);
        this.mView = new WeakReference<>(view);
    }

    @Override
    public void makeRequest(int page) {
       mInteractor.loadTopRatedMovies(page);
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
