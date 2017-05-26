package regulo.udacity.popularmovies.activities;

import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import regulo.udacity.popularmovies.R;
import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.models.Review;
import regulo.udacity.popularmovies.models.Trailer;
import regulo.udacity.popularmovies.restclient.IMovieRepository;
import regulo.udacity.popularmovies.restclient.MovieRepositories;
import regulo.udacity.popularmovies.restclient.RestUtils;

import static regulo.udacity.popularmovies.restclient.RestUtils.THUMBNAIL_URL_YOUTUBE;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.iv_movie_backdrop)
    ImageView mBackdropImageView;

    @BindView(R.id.iv_poster_movie)
    ImageView mPosterImageView;

    @BindView(R.id.tv_genres_movie)
    TextView mGenresTextView;

    @BindView(R.id.tv_release_date_movie)
    TextView mReleaseDateTextView;

    @BindView(R.id.tv_vote_average_movie)
    TextView mVoteAverageTextView;

    @BindView(R.id.tv_overview_movie)
    TextView mOverViewTextView;

    @BindView(R.id.layout_trailers)
    LinearLayout mListTrailers;

    @BindView(R.id.layout_reviews)
    LinearLayout mListReviews;

    @BindView(R.id.fab)
    FloatingActionButton mFavorite;

    private Movie mMovie;
    private final int FAVORITE_MOVIE_ID = 101;
    private boolean isFavorite;
    private IMovieRepository mRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        mRepository = MovieRepositories.getInMemoryRepoInstance(RestUtils.createRestClient());
        getMovieDetails();
        setCellValues();
        setUpToolbar();
        setFloatingActionButton();

        getSupportLoaderManager().initLoader(FAVORITE_MOVIE_ID, null, this);
        isFavorite = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(RestUtils.TAG, mMovie);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMovie = savedInstanceState.getParcelable(RestUtils.TAG);
        setCellValues();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle((mMovie != null) ? mMovie.getTitle() : getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setFloatingActionButton() {
        mFavorite.setOnClickListener(v -> {

            if (!isFavorite) {
                ContentValues values = new ContentValues();
                values.put(FavoritesMovies.COLUMN_MOVIE_ID, mMovie.getId());
                values.put(FavoritesMovies.COLUMN_TITLE, mMovie.getTitle());
                values.put(FavoritesMovies.COLUMN_OVERVIEW, mMovie.getOverview());
                values.put(FavoritesMovies.COLUMN_RELEASE_DATE, mMovie.getRelease_date());
                values.put(FavoritesMovies.COLUMN_POPULARITY, mMovie.getPopularity());
                values.put(FavoritesMovies.COLUMN_VOTE_AVERAGE, mMovie.getVote_average());
                values.put(FavoritesMovies.COLUMN_POSTER_PATH, mMovie.getPoster());
                values.put(FavoritesMovies.COLUMN_BACKDROP_PATH, mMovie.getBackdrop());
                values.put(FavoritesMovies.COLUMN_GENRES, mMovie.getGenres(this));

                getContentResolver().insert(FavoritesMovies.CONTENT_URI, values);

                Snackbar.make(v, mMovie.getTitle() + getString(R.string.added_favorites), Snackbar.LENGTH_LONG).show();

            } else {
                getContentResolver().delete(FavoritesMovies.CONTENT_URI.buildUpon()
                        .appendPath(String.valueOf(mMovie.getId()))
                        .build(), null, null);
                Snackbar.make(v, mMovie.getTitle() + getString(R.string.deleted), Snackbar.LENGTH_LONG).show();
            }

            isFavorite = false;

        });
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getMovieDetails() {
        mMovie = getIntent().getParcelableExtra(RestUtils.TAG);
    }

    private void setCellValues() {
        if (mMovie != null) {
            setBackDropImage(mMovie.getBackdrop_path());
            setPosterImage(mMovie.getPoster_path().replace(RestUtils.THUMBNAIL_SIZE, RestUtils.POSTER_SIZE));
            setGenres(mMovie.getGenres(this));
            setReleaseDate(mMovie.getRelease_date());
            setVoteAverage(mMovie.getVote_average());
            setOverView(mMovie.getOverview());
            setMovieTrailers(mMovie.getId());
            setMovieReviews(mMovie.getId());
        }
    }

    private void setBackDropImage(final String url) {
        Picasso.with(getApplicationContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mBackdropImageView);
    }

    private void setPosterImage(final String url) {
        Picasso.with(getApplicationContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mPosterImageView);
    }

    private void setGenres(final String genres) {
        mGenresTextView.setText(genres);
    }

    private void setReleaseDate(final String releaseDate) {
        mReleaseDateTextView.setText(releaseDate);
    }

    private void setVoteAverage(final float popularity) {
        mVoteAverageTextView.setText(String.valueOf(popularity) + "/10");
    }

    private void setOverView(final String overView) {
        mOverViewTextView.setText(overView);
    }

    private void setYoutubeImageDefault(String url, ImageView view) {
        Picasso.with(getApplicationContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(view);
    }

    /**
     * Creates a LinearLayout view that displays a preview image for the movie trailer.
     * @return {@link LinearLayout}
     */
    private LinearLayout movieTrailer(Trailer.result trailer) {
        String trailerUrl = RestUtils.createBaseUrlYoutube(trailer.getKey());
        String imageDefault = RestUtils.createThumbnailUrlYoutube(trailer.getKey(), THUMBNAIL_URL_YOUTUBE);

        LinearLayout mLinearlayout = new LinearLayout(getApplicationContext());
        mLinearlayout.setOrientation(LinearLayout.VERTICAL);
        int padding = getResources().getDimensionPixelOffset(R.dimen.size_10_margin);
        mLinearlayout.setPadding(0, padding, padding, padding);

        ImageView mImage = new ImageView(getApplicationContext());
        setYoutubeImageDefault(imageDefault, mImage);
        mLinearlayout.addView(mImage);

        mLinearlayout.setOnClickListener(v -> {
            Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
            if (mIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                startActivity(mIntent);
            }
        });

        return mLinearlayout;
    }


    /**
     * Creates a LinearLayout view that displays a TextView with a movie result.
     * @param result {@link Review}
     * @return {@link LinearLayout}
     */
    private LinearLayout movieReview(Review.Result result) {

        LinearLayout mReviewLinearLayout = new LinearLayout(getApplicationContext());
        mReviewLinearLayout.setOrientation(LinearLayout.VERTICAL);
        int padding = getResources().getDimensionPixelOffset(R.dimen.size_5_margin);

        TextView authorTv = new TextView(getApplicationContext());
        authorTv.setText(fromHtml(String.format(getResources().getString(R.string.review_author), result.getAuthor())));
        authorTv.setPadding(0, padding, 0, padding);
        mReviewLinearLayout.addView(authorTv);

        TextView contentTv = new TextView(getApplicationContext());
        contentTv.setText(result.getContent());
        mReviewLinearLayout.addView(contentTv);

        View divider = new View(getApplicationContext());
        divider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3));
        divider.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        mReviewLinearLayout.addView(divider);

        return mReviewLinearLayout;
    }

    private void setMovieTrailers(int movieID) {
        mRepository.getTrailer(movieID, new IMovieRepository.LoadTrailerCallback() {
            @Override
            public void onTrailerLoaded(List<Trailer.result> trailers) {
                for (Trailer.result trailer : trailers) {
                    LinearLayout linearLayout = movieTrailer(trailer);
                    mListTrailers.addView(linearLayout);
                }
            }

            @Override
            public void onTrailerFailure() {
                Log.d(DetailsActivity.class.getSimpleName(), getString(R.string.error_loading_trailer));
            }
        });
    }


    private void setMovieReviews(int movieID) {
        mRepository.getReviews(movieID, new IMovieRepository.LoadReviewsCallback() {
            @Override
            public void onReviewsLoaded(List<Review.Result> results) {
                for (Review.Result result : results) {
                    LinearLayout linearLayout = movieReview(result);
                    mListReviews.addView(linearLayout);
                }
            }

            @Override
            public void onReviewsFailure() {
                Log.d(DetailsActivity.class.getSimpleName(), getString(R.string.error_loading_reviews));
            }
        });
    }

    @SuppressWarnings("deprecation")
    private static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case FAVORITE_MOVIE_ID:
                return new CursorLoader(
                        this,
                        FavoritesMovies.CONTENT_URI,
                        null,
                        FavoritesMovies.COLUMN_MOVIE_ID + "=?",
                        new String[]{String.valueOf(mMovie.getId())},
                        null);
            default:
                throw new RuntimeException(getString(R.string.loader_not_implemented) + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        boolean cursorHasValidData = false;
        if (data != null && data.moveToFirst()) {
            cursorHasValidData = true;
        }

        if (!cursorHasValidData) {
            return;
        }

        switch (loader.getId()) {
            case FAVORITE_MOVIE_ID:
                if (data.getCount() > 0) {
                    isFavorite = true;
                } else {
                    isFavorite = false;
                }
                break;

            default:
                throw new RuntimeException(getString(R.string.loader_not_implemented) + loader.getId());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
