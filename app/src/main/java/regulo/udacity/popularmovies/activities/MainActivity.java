package regulo.udacity.popularmovies.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import regulo.udacity.popularmovies.R;


import regulo.udacity.popularmovies.fragments.DetailsFragment;
import regulo.udacity.popularmovies.interfaces.IOnClickListener;
import regulo.udacity.popularmovies.models.Movie;
import regulo.udacity.popularmovies.restclient.RestUtils;

public class MainActivity extends AppCompatActivity implements IOnClickListener{

    private boolean mDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.details_container);
        mDualPane = view != null && view.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onItemSelected(Movie movie) {
        showMovieDetails(movie);
    }

    public void showMovieDetails(final Movie movie) {
        if(mDualPane) {
            DetailsFragment newDetails = DetailsFragment.newInstance(movie);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.details_container, newDetails)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        } else {
            Intent mIntent = new Intent(this, DetailsActivity.class);
            mIntent.putExtra(RestUtils.TAG, movie);
            startActivity(mIntent);
        }
    }
}
