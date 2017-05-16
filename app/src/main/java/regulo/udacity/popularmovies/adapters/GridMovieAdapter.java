package regulo.udacity.popularmovies.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.List;

import regulo.udacity.popularmovies.R;
import regulo.udacity.popularmovies.listeners.RecyclerOnItemClickListener;
import regulo.udacity.popularmovies.models.Movie;

public class GridMovieAdapter extends RecyclerView.Adapter<GridMovieAdapter.MoviesViewHolder>{

    private final List<Movie> mPopularMovies;
    private final RecyclerOnItemClickListener mOnItemClickListener;
    private final Context mContext;

    public GridMovieAdapter(List<Movie> mPopularMovies, RecyclerOnItemClickListener mOnClickListener, Context mContext) {
        this.mPopularMovies = mPopularMovies;
        this.mOnItemClickListener = mOnClickListener;
        this.mContext = mContext;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_view, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.setThumbnail(mPopularMovies.get(position).getPoster_path(), mContext);
    }

    @Override
    public int getItemCount() {
        return (mPopularMovies == null) ? 0 : mPopularMovies.size();
    }

    public void addNewItems(List<Movie> items){
        mPopularMovies.addAll(items);
        notifyDataSetChanged();
    }

    public void updateItems(List<Movie> items){
        mPopularMovies.clear();
        mPopularMovies.addAll(items);
        notifyDataSetChanged();
    }

    public List<Movie> getMovies(){
        return mPopularMovies;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final ImageView mThumbnailImageView;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            mThumbnailImageView = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            itemView.setOnClickListener(this);
        }

        public void setThumbnail(final String imageUrl, final Context context) {
            Picasso.with(context)
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(mThumbnailImageView);
        }

        @Override
        public void onClick(View v) {
           mOnItemClickListener.onItemClick(mPopularMovies.get(getAdapterPosition()));
        }
    }
}
