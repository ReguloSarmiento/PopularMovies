package regulo.udacity.popularmovies.listeners;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import regulo.udacity.popularmovies.adapters.GridMovieAdapter;
import regulo.udacity.popularmovies.restclient.IRestClient;
import regulo.udacity.popularmovies.restclient.RestUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    /***********************************************************************************************
     RecyclerViewScrollListener is in charge of paginating the list of movies by calling the API when
     the user scrolls down and reaches the bottom of the RecyclerView(List).
     ***********************************************************************************************/

    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private boolean loading = true;
    private int page = RestUtils.FIRST_PAGE;
    private final GridMovieAdapter mAdapter;
    private final IRestClient mRestClient;
    private int previousTotal = 0;
    private final int VISIBLE_THRESHOLD = 6;
    private final LinearLayoutManager mLayoutManager;

    public RecyclerViewScrollListener(LinearLayoutManager mLayoutManager, GridMovieAdapter mAdapter) {
        super();
        this.mRestClient = RestUtils.createRestClient();
        this.mLayoutManager = mLayoutManager;
        this.mAdapter = mAdapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = mLayoutManager.getItemCount();
        visibleItemCount = mLayoutManager.getChildCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if (loading && (totalItemCount > previousTotal)){
            loading = false;
            previousTotal = totalItemCount;
            page++;
        }

        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            loadMoreItems(page);
            loading = true;
        }
    }

    private void loadMoreItems(final int page){
        mRestClient.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                response -> {
                    mAdapter.addNewItems(response.getResults());
                },

                Throwable -> {
                    Log.d(RecyclerViewScrollListener.class.getName(), "Can't load more movies!");
                }
            );
    }
}
