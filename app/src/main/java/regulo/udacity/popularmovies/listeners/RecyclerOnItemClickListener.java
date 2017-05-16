package regulo.udacity.popularmovies.listeners;


import regulo.udacity.popularmovies.models.Movie;

public interface RecyclerOnItemClickListener {

    /**
     * Called when an item is clicked.
     * @param movie  Position of the movie that was clicked.
     */
     void onItemClick(final Movie movie);
}
