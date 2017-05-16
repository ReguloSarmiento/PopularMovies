package regulo.udacity.popularmovies.fragments;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import regulo.udacity.popularmovies.R;
import regulo.udacity.popularmovies.adapters.TabsPagerAdapter;
import regulo.udacity.popularmovies.slidingtabs.SlidingTabLayout;
import regulo.udacity.popularmovies.slidingtabs.TabColorizer;


public class TabsFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    SlidingTabLayout mSlidingTabLayout;

    private Unbinder mUnbinder;

    public TabsFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setUpToolbar();
        setUpTabsSettings();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = getResources();
        boolean showEmptyDetails = res.getBoolean(R.bool.show_empty_details);
        int orientation = res.getConfiguration().orientation;
        Fragment fragment = getFragmentManager().findFragmentById(R.id.details_container);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE && fragment == null && showEmptyDetails) {
            EmptyFragment emptyFragment = new EmptyFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.details_container, emptyFragment).commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void setUpToolbar(){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        if(activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setHomeButtonEnabled(false);
        }
    }

    private void setUpTabsSettings(){
        final TabsPagerAdapter mTabsPagerAdapter = new TabsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mTabsPagerAdapter);
        mSlidingTabLayout.setCustomTabColorizer(new TabColorizer(getActivity()));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

}
