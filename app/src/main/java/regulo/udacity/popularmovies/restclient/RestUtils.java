package regulo.udacity.popularmovies.restclient;



import regulo.udacity.popularmovies.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestUtils {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v=";
    private static final String API_KEY = BuildConfig.API_KEY;
    public static final String POPULAR_MOVIES_URL = "/3/movie/popular?api_key="+API_KEY;
    public static final String TOP_RATED_MOVIES_URL = "/3/movie/top_rated?api_key="+API_KEY;
    public static final String GENRES_URL = "/3/genre/movie/list?api_key="+API_KEY;
    public static final String TRAILER_URL = "/3/movie/{movie_id}/videos?api_key="+API_KEY;
    public static final String REVIEW_URL = "/3/movie/{movie_id}/reviews?api_key="+API_KEY;
    public static final String THUMBNAIL_SIZE = "w500";
    public static final String POSTER_SIZE = "w342";
    public static final String POSTER_SIZE_300 = "w185";
    public static final String THUMBNAIL_URL = "https://image.tmdb.org/t/p/"+THUMBNAIL_SIZE;
    public static final String THUMBNAIL_URL_YOUTUBE = "http://img.youtube.com/vi/%s/hqdefault.jpg";
    public static final String THUMBNAIL_URL_YOUTUBE_MD = "http://img.youtube.com/vi/%s/mqdefault.jpg";
    public static final String BACKDROP_URL = "https://image.tmdb.org/t/p/w300";
    public static final String PAGE = "page";
    public static final int FIRST_PAGE = 1;
    public static final String TAG = "movieDetails";
    public static final String MOVIE_ID = "movie_id";


    /**
     * Generates an implementation of the IRestClient interface.
     * @return The service that makes asynchronous HTTP request.
     */
    public static IRestClient createRestClient(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IRestClient.class);
    }

    /**
     * Provides a Youtube video url
     * @param key video key
     * @return {@String}
     */
    public static String createBaseUrlYoutube(final String key) {
        return BASE_URL_YOUTUBE + key;
    }

    /**
     * Provides a Image youtube video url
     * @param key video key
     * @return {@String}
     */
    public static String createThumbnailUrlYoutube(final String key, final String size) {
        return String.format(size, key);
    }
}
