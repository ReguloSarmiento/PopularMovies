package regulo.udacity.popularmovies.fragments.movies_popular;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.fragments.movies_popular.IPopularMovieContract.View;
import regulo.udacity.popularmovies.restclient.IMovieRepository;

public class PopularMoviePresenter implements IPopularMovieContract.UserActionsListener {

    /******************************************************************************************
     MoviesPresenter retrieves data from the model {@link IMovieRepository } and
     notifies the view {@link  PopularMovieFragment } to display it.
     ******************************************************************************************/

    private final WeakReference<View> mView;
    private final IMovieRepository mMovieRepository;

    public PopularMoviePresenter(@NonNull final View view, @NonNull final IMovieRepository mMovieRepository) {
        this.mMovieRepository = mMovieRepository;
        this.mView = new WeakReference<>(view);
    }

    @Override
    public void makeRequest(int page, Context context) {

         mMovieRepository.getGenres(context);

         mMovieRepository.getMovies(page, new IMovieRepository.LoadMoviesCallback() {
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
