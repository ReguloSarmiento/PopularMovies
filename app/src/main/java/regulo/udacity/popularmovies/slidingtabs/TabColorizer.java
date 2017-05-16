package regulo.udacity.popularmovies.slidingtabs;

import android.content.Context;

import regulo.udacity.popularmovies.R;
import regulo.udacity.popularmovies.utilities.ColorHelper;


public class TabColorizer implements SlidingTabLayout.TabColorizer {

    private final int mIndicatorColor;
    private final int mDividerColor;

    public TabColorizer(final Context context) {
        this.mIndicatorColor = ColorHelper.getColor(context, R.color.tabIndicator);
        this.mDividerColor = ColorHelper.getColor(context, R.color.tabDivider);
    }

    @Override
    public int getIndicatorColor(final int position) {
        return mIndicatorColor;
    }

    @Override
    public int getDividerColor(final int position) {
        return mDividerColor;
    }

}
