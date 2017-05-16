package regulo.udacity.popularmovies.utilities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import android.view.View;
import android.widget.TextView;

import regulo.udacity.popularmovies.R;
import android.support.design.widget.Snackbar;


public class NetworkHelper {

    /**
     * Checks if the device has internet connection or not.
     * @param context {@link regulo.udacity.popularmovies.activities.MainActivity}
     * @return true if the device is connected to internet, false otherwise.
     */
    private static boolean isInternetConnection(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    /**
     * Alerts the user that there's no internet connection.
     * @param view {@link android.support.v7.widget.RecyclerView}
     * @param context {@link regulo.udacity.popularmovies.activities.MainActivity}
     */
    public static void addNoInternetView(final View view, final Context context){
        final Snackbar mSnackbar = Snackbar.make(view , R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE);
        final TextView mTextview = (TextView) mSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        mTextview.setTextColor(Color.LTGRAY);

        if (!isInternetConnection(context)) {
            if (!mSnackbar.isShown()) {
                mSnackbar.show();
            }
        } else {
            if (mSnackbar.isShown()) {
                mSnackbar.dismiss();
            }
        }
        mSnackbar.setActionTextColor(Color.RED);
        mSnackbar.setAction(R.string.retry, v -> context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)));
    }

}
