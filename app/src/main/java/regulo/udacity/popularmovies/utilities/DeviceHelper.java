package regulo.udacity.popularmovies.utilities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

public class DeviceHelper {

    /**
     * Checks the device orientation
     * @param context {@link regulo.udacity.popularmovies.activities.MainActivity}
     * @return true if the device orientation is landscape or false otherwise.
     */
    public static boolean isDeviceOrientationLandscape(final Context context){
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    /**
     * Gets device's Android version.
     * @return Android version
     */
    public static int getAndroidVersion(){
        return Build.VERSION.SDK_INT;
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }
}
