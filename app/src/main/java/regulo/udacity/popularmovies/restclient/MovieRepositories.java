package regulo.udacity.popularmovies.restclient;

import android.support.annotation.NonNull;



public class MovieRepositories {

    private static IMovieRepository mRepository = null;

    public synchronized static IMovieRepository getInMemoryRepoInstance(@NonNull IRestClient restClient) {
        if (null == mRepository) {
            mRepository = new InMemoryMoviesRepository(restClient);
        }
        return mRepository;
    }
}
