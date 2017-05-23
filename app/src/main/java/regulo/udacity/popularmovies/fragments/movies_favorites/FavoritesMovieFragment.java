package regulo.udacity.popularmovies.fragments.movies_favorites;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import regulo.udacity.popularmovies.R;
import regulo.udacity.popularmovies.adapters.GridMovieAdapter;
import regulo.udacity.popularmovies.database.FavoritesMovieContract;
import regulo.udacity.popularmovies.interfaces.IOnClickListener;
import regulo.udacity.popularmovies.listeners.RecyclerOnItemClickListener;
import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.utilities.DeviceHelper;
import regulo.udacity.popularmovies.utilities.NetworkHelper;

import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_BACKDROP;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_DATE;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_GENRES;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_ID;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_OVERVIEW;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_POPULARITY;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_POSTER;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_TITLE;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.INDEX_COLUMN_VOTE;
import static regulo.udacity.popularmovies.database.FavoritesMovieContract.FavoritesMovies.PROJECTION;


public class FavoritesMovieFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, RecyclerOnItemClickListener {

    @BindView(R.id.recyclerview_movies_favorites)
    RecyclerView mRecyclerView;

    @BindView(R.id.pb_progress)
    ProgressBar mProgress;

    private IOnClickListener mCallback;
    public static final int FAVORITES = 100;
    private GridMovieAdapter mGridMovieAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<Movie> mFavoriteMovieList;

    public FavoritesMovieFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (IOnClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + getString(R.string.must_implement));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_movie, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUI();
        getLoaderManager().initLoader(FAVORITES, null, this);
    }

    private void setUI() {
        mGridLayoutManager = new GridLayoutManager(getContext(), DeviceHelper.calculateNoOfColumns(getContext()));
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        NetworkHelper.addNoInternetView(mRecyclerView, getContext());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch (id) {
            case FAVORITES:
                return new CursorLoader(
                        getActivity(),
                        FavoritesMovieContract.FavoritesMovies.CONTENT_URI,
                        PROJECTION,
                        null, null, null);

            default:
                throw new RuntimeException(getString(R.string.loader_not_implemented) + id);
        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mFavoriteMovieList = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                Movie movie = new Movie(
                        Integer.parseInt(data.getString(INDEX_COLUMN_ID)),
                        data.getString(INDEX_COLUMN_TITLE),
                        data.getString(INDEX_COLUMN_OVERVIEW),
                        data.getString(INDEX_COLUMN_DATE),
                        data.getFloat(INDEX_COLUMN_POPULARITY),
                        data.getFloat(INDEX_COLUMN_VOTE),
                        data.getString(INDEX_COLUMN_POSTER),
                        data.getString(INDEX_COLUMN_BACKDROP),
                        data.getString(INDEX_COLUMN_GENRES));
                mFavoriteMovieList.add(movie);
            } while (data.moveToNext());
        }

        populateRecyclerView(mFavoriteMovieList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void populateRecyclerView(List<Movie> movies) {
        mProgress.setVisibility(View.GONE);
        if(mGridMovieAdapter == null) {
            mGridMovieAdapter = new GridMovieAdapter(movies, this , getActivity());
        }else{
            mGridMovieAdapter.updateItems(movies);
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mGridMovieAdapter);
    }


    @Override
    public void onItemClick(Movie movie) {
        mCallback.onItemSelected(movie);
    }
}
