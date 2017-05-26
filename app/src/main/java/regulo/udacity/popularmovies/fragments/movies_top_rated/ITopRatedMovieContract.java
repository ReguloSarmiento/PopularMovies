package regulo.udacity.popularmovies.fragments.movies_top_rated;

import android.content.Context;

import java.util.List;

import regulo.udacity.popularmovies.models.Movie;


public interface ITopRatedMovieContract {

    interface View {
        /**
         * Displays a RecyclerView with a list of movies.
         * @param movies list of movies
         */
        void onLoadedSuccess(final List<Movie> movies);


        /**
         * Shows a TextView
         */
        void onLoadedFailure();
    }

    interface UserActionsListener  {

        /**
         * Calls the Api to get the list of the top rated movies.
         * @param page number.
         */
        void getTopRatedMovies(final int page);
    }
}
