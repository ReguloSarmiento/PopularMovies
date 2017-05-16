package regulo.udacity.popularmovies.constants;


public enum TabsID {

    POPULAR(Constants.ID_POPULAR_MOVIES_TAB, Constants.TITLE_POPULAR_MOVIES_TAB),
    TOP_RATED(Constants.ID_TOP_RATED_MOVIES_TAB, Constants.TITLE_TOP_RATED_MOVIES_TAB),
    FAVORITES(Constants.ID_FAVORITES_MOVIES_TAB, Constants.TITLE_FAVORITES_MOVIES_TAB);

    private final int tabID;
    private final String tabTitle;

    TabsID(final int id, final String title){
        this.tabID = id;
        this.tabTitle = title;
    }

    public int getTabID() {
        return tabID;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public static TabsID getTabs(final int index){
        switch (index){
            case Constants.ID_POPULAR_MOVIES_TAB:
                return POPULAR;
            case Constants.ID_TOP_RATED_MOVIES_TAB:
                return TOP_RATED;
            case Constants.ID_FAVORITES_MOVIES_TAB:
                return FAVORITES;
            default:
                return null;
        }
    }

    private static class Constants{
        public static final int ID_POPULAR_MOVIES_TAB = 0;
        public static final int ID_TOP_RATED_MOVIES_TAB = 1;
        public static final int ID_FAVORITES_MOVIES_TAB = 2;
        public static final String TITLE_POPULAR_MOVIES_TAB = "Popular";
        public static final String TITLE_TOP_RATED_MOVIES_TAB = "Top Rated";
        public static final String TITLE_FAVORITES_MOVIES_TAB = "Favorites";
    }
}
