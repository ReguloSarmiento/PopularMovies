package regulo.udacity.popularmovies.fragments.movies_popular;


import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.restclient.IMovieRepository;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class PopularMoviePresenterTest {

    @Mock
    private IPopularMovieContract.View mView;

    @Mock
    private IMovieRepository mRepository;

    @Mock
    private Context mContext;

    private PopularMoviePresenter mPresenter;

    @Captor
    private ArgumentCaptor<IMovieRepository.LoadMoviesCallback> mLoadMoviesCallbackCaptor;

    private static List<Movie> MOVIES = new ArrayList(Arrays.asList(new Movie(01, "title", "overview", "releasedate",
            12, 5, "poster", "another poster", "genres"), new Movie(01, "title", "overview", "releasedate",
            12, 5, "poster", "another poster", "genres")));

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new PopularMoviePresenter(mView, mRepository);

    }

    @Test
    public void shouldLoadPopularMoviesFromRepositoryIntoTheView() {
        mPresenter.makeRequest(anyInt(), mContext);

        verify(mRepository).getMovies(anyInt(), mLoadMoviesCallbackCaptor.capture());
        mLoadMoviesCallbackCaptor.getValue().onMoviesLoaded(MOVIES);

        verify(mView).onLoadedSuccess(MOVIES);
    }

    @Test
    public void shouldShowAMessageWhenTheApiThrowAnError(){
        mPresenter.makeRequest(anyInt(), mContext);

        verify(mRepository).getMovies(anyInt(), mLoadMoviesCallbackCaptor.capture());
        mLoadMoviesCallbackCaptor.getValue().onMoviesFailure();

        verify(mView).onLoadedFailure();
    }

}