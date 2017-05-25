package regulo.udacity.popularmovies.fragments.movies_popular;


import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PopularMoviePresenterTest {

    @Mock
    private IPopularMovieContract.View mView;

    @Mock
    private Context mContext;

    private PopularMoviePresenter mPresenter;

    private final int PAGE = 1;

//    private static List<Movie> MOVIES = Lists.newArrayList(new Movie(01, "title", "overview", "releasedate",
//            12, 5, "poster", "another poster", "genres"), new Movie(01, "title", "overview", "releasedate",
//            12, 5, "poster", "another poster", "genres"));
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void shouldLoadPopularMoviesFromRepositoryIntoTheView(){

    }

}