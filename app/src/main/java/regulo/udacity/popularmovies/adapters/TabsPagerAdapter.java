package regulo.udacity.popularmovies.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import regulo.udacity.popularmovies.constants.TabsID;
import regulo.udacity.popularmovies.fragments.movies_favorites.FavoritesMovieFragment;
import regulo.udacity.popularmovies.fragments.movies_popular.PopularMovieFragment;
import regulo.udacity.popularmovies.fragments.movies_top_rated.TopRatedMovieFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private static final int NUMBER_OF_TABS = 3;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        final TabsID tabsID = TabsID.getTabs(position);
        switch (tabsID){
            case POPULAR:
                return new PopularMovieFragment();
            case TOP_RATED:
                return new TopRatedMovieFragment();
            case FAVORITES:
                return new FavoritesMovieFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final TabsID tabsID = TabsID.getTabs(position);
        switch (tabsID){
            case POPULAR:
                return TabsID.POPULAR.getTabTitle();
            case TOP_RATED:
                return TabsID.TOP_RATED.getTabTitle();
            case FAVORITES:
                return TabsID.FAVORITES.getTabTitle();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }
}
