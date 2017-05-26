package regulo.udacity.popularmovies.fragments.movies_popular;


import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;
import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.fragments.movies_popular.IPopularMovieContract.View;
import regulo.udacity.popularmovies.restclient.IMovieRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public class PopularMoviePresenter implements IPopularMovieContract.UserActionsListener {

    /******************************************************************************************
     MoviesPresenter retrieves data from the model {@link IMovieRepository } and
     notifies the view {@link  PopularMovieFragment } to display it.
     ******************************************************************************************/

    private final WeakReference<View> mView;
    private final IMovieRepository mMovieRepository;

    public PopularMoviePresenter(@NonNull final View view, @NonNull final IMovieRepository movieRepository) {
        this.mMovieRepository = checkNotNull(movieRepository, "movieRepository cannot be null!");
        this.mView = checkNotNull(new WeakReference<>(view), "view cannot be null");
    }

    @Override
    public void getPopularMovies(int page, Context context) {

         mMovieRepository.getGenres(context);

         mMovieRepository.getPopular(page, new IMovieRepository.LoadMoviesCallback() {
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
