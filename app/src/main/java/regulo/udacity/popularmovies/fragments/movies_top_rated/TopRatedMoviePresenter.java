package regulo.udacity.popularmovies.fragments.movies_top_rated;


import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.restclient.IMovieRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class TopRatedMoviePresenter implements ITopRatedMovieContract.UserActionsListener{

    private final WeakReference<ITopRatedMovieContract.View> mView;
    private final IMovieRepository mMovieRepository;

    public TopRatedMoviePresenter(@NonNull final ITopRatedMovieContract.View view, @NonNull IMovieRepository movieRepository) {
        this.mMovieRepository = checkNotNull(movieRepository, "movieRepository cannot be null");
        this.mView = checkNotNull(new WeakReference<>(view), "view cannot be null");
    }

    @Override
    public void getTopRatedMovies(int page) {
        mMovieRepository.getTopRated(page , new IMovieRepository.LoadMoviesCallback(){
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                if(mView.get() != null) mView.get().onLoadedSuccess(movies);
            }

            @Override
            public void onMoviesFailure() {
                if(mView.get() != null) mView.get().onLoadedFailure();
            }
        });
    }
}
