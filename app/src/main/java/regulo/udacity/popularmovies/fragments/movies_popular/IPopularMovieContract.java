package regulo.udacity.popularmovies.fragments.movies_popular;

import android.content.Context;

import java.util.List;

import regulo.udacity.popularmovies.models.Movie;

public interface IPopularMovieContract {

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

    interface UserActionsListener {

        /**
         * Calls the Api to get the list of movies with a page number given and
         * the list of genres as well.
         * @param page number.
         * @param context {@link Context}
         */
        void makeRequest(final int page, final Context context);
    }

}
