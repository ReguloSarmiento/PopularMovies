package regulo.udacity.popularmovies.restclient;



import regulo.udacity.popularmovies.models.Genres;
import regulo.udacity.popularmovies.models.Response;
import regulo.udacity.popularmovies.models.Review;
import regulo.udacity.popularmovies.models.Trailer;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface IRestClient {

    /**
     * Gets a list of popular movies.
     * @param page  Current page
     * @return an {@link rx.Observable} with a response.
     */
    @GET(RestUtils.POPULAR_MOVIES_URL)
    Observable<Response> getPopularMovies(@Query(RestUtils.PAGE) int page);

    /**
     * Gets a list of the top rated movies
     * @param page number of page to show
     * @return an {@link rx.Observable} with a response.
     */
    @GET(RestUtils.TOP_RATED_MOVIES_URL)
    Observable<Response> getTopRatedMovies(@Query(RestUtils.PAGE) int page);


    /**
     * Gets a list of trailers by giving a movie ID.
     * @param movie_id {@link regulo.udacity.popularmovies.models.Movie}
     * @return an {@link rx.Observable} with a response.
     */
    @GET(RestUtils.TRAILER_URL)
    Observable<Trailer> getTrailer(@Path(RestUtils.MOVIE_ID) int movie_id);

    /**
     * Gets a list of reviews by giving a movie ID.
     * @param movie_id {@link regulo.udacity.popularmovies.models.Movie}
     * @return an {@link rx.Observable} with a response.
     */
    @GET(RestUtils.REVIEW_URL)
    Observable<Review> getReviews(@Path(RestUtils.MOVIE_ID) int movie_id);


    /**
     * Gets a list of genres.
     * @return an {@link rx.Observable} with a response.
     */
    @GET(RestUtils.GENRES_URL)
    Observable<Genres> getGenres();
}
