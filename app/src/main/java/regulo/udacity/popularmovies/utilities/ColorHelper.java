package regulo.udacity.popularmovies.utilities;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import regulo.udacity.popularmovies.constants.Constans;

public class ColorHelper {

    /**
     * Helper function that gets a colour resource.
     * @param context Context of the view.
     * @param resID   Colour resource.
     * @return a colour {@link android.graphics.Color}.
     */
    public static final int getColor(final Context context, final int resID) {
      //  Preconditions.checkNotNull(context, "Context cannot be null");
        if (DeviceHelper.getAndroidVersion() >= Constans.ANDROID_VERSION) {
            return ContextCompat.getColor(context, resID);
        } else {
            return context.getResources().getColor(resID);
        }
    }

}
